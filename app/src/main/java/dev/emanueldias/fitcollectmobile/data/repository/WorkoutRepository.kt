package dev.emanueldias.fitcollectmobile.data.repository

import android.content.Context
import dev.emanueldias.fitcollectmobile.data.model.WorkoutData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

class WorkoutRepository private constructor(context: Context) {
    private val workoutsDir = File(context.filesDir, "workouts").apply { mkdirs() }
    
    private val _workouts = MutableStateFlow<List<WorkoutData>>(emptyList())
    val workouts: StateFlow<List<WorkoutData>> = _workouts.asStateFlow()

    private val json = Json { ignoreUnknownKeys = true; prettyPrint = true }

    init {
        loadWorkouts()
    }

    fun loadWorkouts() {
        val files = workoutsDir.listFiles { _, name -> name.endsWith(".json") } ?: emptyArray()
        val list = files.mapNotNull { file ->
            try {
                json.decodeFromString<WorkoutData>(file.readText())
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }.sortedByDescending { it.startTime }
        _workouts.value = list
    }

    suspend fun saveWorkout(workout: WorkoutData) = withContext(Dispatchers.IO) {
        try {
            val file = File(workoutsDir, "${workout.startTime}.json")
            file.writeText(json.encodeToString(workout))
            loadWorkouts() // Refresh state
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getWorkoutById(startTime: Long): WorkoutData? {
        return _workouts.value.find { it.startTime == startTime }
    }

    companion object {
        @Volatile
        private var INSTANCE: WorkoutRepository? = null

        fun getInstance(context: Context): WorkoutRepository {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: WorkoutRepository(context).also { INSTANCE = it }
            }
        }
    }
}
