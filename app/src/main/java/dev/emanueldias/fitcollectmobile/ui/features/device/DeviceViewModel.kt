package dev.emanueldias.fitcollectmobile.ui.features.device

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.wearable.CapabilityClient
import com.google.android.gms.wearable.Wearable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

data class Device(
    val name: String,
)

data class DeviceUiState(
    val isScanning: Boolean = false,
    val devices: List<Device> = emptyList(),
    val hasError: Boolean = false,
    val messageError: String? = null
)

class DeviceViewModel(application: Application): AndroidViewModel(application) {

    private val _uiState = MutableStateFlow(DeviceUiState())
    val uiState: StateFlow<DeviceUiState> = _uiState.asStateFlow()

    private val capabilityClient = Wearable.getCapabilityClient(application)
    private val messageClient = Wearable.getMessageClient(application)

    init{
        searchDevices()
    }

    fun searchDevices() {
        viewModelScope.launch {
            _uiState.update { it.copy(isScanning = true) }
            try {
                val capatibilyInfo = capabilityClient.getCapability(
                    "fitcollect_smartwatch",
                    CapabilityClient.FILTER_REACHABLE
                ).await()

                val devices = capatibilyInfo.nodes.map {
                    Device(it.displayName)
                }

                _uiState.update { it.copy(devices = devices) }

            } catch (ex: Exception) {
                _uiState.update { it.copy(hasError = true, messageError = ex.message) }
            } finally {
                _uiState.update { it.copy(isScanning = false) }
            }
        }
    }
}
