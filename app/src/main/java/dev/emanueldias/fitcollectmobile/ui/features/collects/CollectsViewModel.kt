package dev.emanueldias.fitcollectmobile.ui.features.collects

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import dev.emanueldias.fitcollectmobile.data.model.WorkoutData
import dev.emanueldias.fitcollectmobile.data.repository.WorkoutRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class CollectsViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = WorkoutRepository.getInstance(application)

    val workouts: StateFlow<List<WorkoutData>> = repository.workouts
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun refresh() {
        repository.loadWorkouts()
    }
}
