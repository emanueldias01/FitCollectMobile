package dev.emanueldias.fitcollectmobile.service

import android.util.Log
import com.google.android.gms.wearable.DataEvent
import com.google.android.gms.wearable.DataEventBuffer
import com.google.android.gms.wearable.DataMapItem
import com.google.android.gms.wearable.WearableListenerService
import dev.emanueldias.fitcollectmobile.data.model.WorkoutData
import dev.emanueldias.fitcollectmobile.data.repository.WorkoutRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

class WearDataListenerService : WearableListenerService() {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private val json = Json { ignoreUnknownKeys = true }

    override fun onDataChanged(dataEvents: DataEventBuffer) {
        Log.d("WearDataListenerService", "onDataChanged chamado com ${dataEvents.count} eventos")
        
        for (event in dataEvents) {
            if (event.type == DataEvent.TYPE_CHANGED) {
                val path = event.dataItem.uri.path ?: continue
                Log.d("WearDataListenerService", "Item de dados mudou: $path")
                
                if (path.startsWith("/workout/")) {
                    try {
                        val dataMap = DataMapItem.fromDataItem(event.dataItem).dataMap
                        val jsonString = dataMap.getString("workout_json")
                        if (jsonString != null) {
                            Log.d("WearDataListenerService", "JSON recebido: $jsonString")
                            val workoutData = json.decodeFromString<WorkoutData>(jsonString)
                            
                            scope.launch {
                                WorkoutRepository.getInstance(applicationContext).saveWorkout(workoutData)
                                Log.d("WearDataListenerService", "Treino salvo com sucesso no Mobile")
                            }
                        }
                    } catch (e: Exception) {
                        Log.e("WearDataListenerService", "Erro ao processar dados de treino", e)
                    }
                }
            }
        }
    }
}
