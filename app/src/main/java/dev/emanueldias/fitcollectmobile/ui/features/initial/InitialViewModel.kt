package dev.emanueldias.fitcollectmobile.ui.features.initial

import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class InitialUiState(
    var logoVisibility: Boolean = false,
    var nameAppVisibility: Boolean = false,
    var textVisibility: Boolean = false,
    var buttonVisibility: Boolean = false
)

class InitialViewModel(): ViewModel() {
    val _uiState = MutableStateFlow(InitialUiState())
    val uiState = _uiState.asStateFlow()

    fun activeLogoVisibility() {
        _uiState.update { it.copy(logoVisibility = true) }
    }

    fun activeNameAppVisibility() {
        _uiState.update { it.copy(nameAppVisibility = true) }
    }

    fun activeTextVisibility() {
        _uiState.update { it.copy(textVisibility = true) }
    }

    fun activeButtonVisibility() {
        _uiState.update { it.copy(buttonVisibility = true) }
    }

}