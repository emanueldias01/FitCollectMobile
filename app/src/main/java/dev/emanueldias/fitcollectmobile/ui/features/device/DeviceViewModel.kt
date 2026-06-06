package dev.emanueldias.fitcollectmobile.ui.features.device

import android.app.Application
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.wearable.CapabilityClient
import com.google.android.gms.wearable.MessageClient
import com.google.android.gms.wearable.MessageEvent
import com.google.android.gms.wearable.Wearable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

data class Device(
    val id: String,
    val name: String,
)

data class DeviceUiState(
    val isScanning: Boolean = false,
    val devices: List<Device> = emptyList(),
    val isConnecting: Boolean = false,
    val connectedDeviceId: String? = null,
    val hasError: Boolean = false,
    val messageError: String? = null
)

class DeviceViewModel(application: Application): AndroidViewModel(application), MessageClient.OnMessageReceivedListener {

    private val _uiState = MutableStateFlow(DeviceUiState())
    val uiState: StateFlow<DeviceUiState> = _uiState.asStateFlow()

    private val capabilityClient = Wearable.getCapabilityClient(application)
    private val messageClient = Wearable.getMessageClient(application)

    init {
        searchDevices()
    }

    fun registerListener() {
        messageClient.addListener(this)
    }

    fun removeListener() {
        messageClient.removeListener(this)
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
                    Device(
                        id = it.id,
                        name = it.displayName
                    )
                }

                _uiState.update { it.copy(devices = devices) }

            } catch (ex: Exception) {
                _uiState.update { it.copy(hasError = true, messageError = ex.message) }
            } finally {
                _uiState.update { it.copy(isScanning = false) }
            }
        }
    }

    fun sendRequestToConnect(deviceId: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isConnecting = true) }
            try {
                val mobileName = Build.MODEL

                messageClient.sendMessage(
                    deviceId,
                    "/connection_request",
                    mobileName.toByteArray()
                ).await()

            } catch (ex: Exception) {
                _uiState.update { it.copy(
                    hasError = true,
                    messageError = ex.message,
                    isConnecting = false
                )}
            }
        }
    }

    override fun onMessageReceived(event: MessageEvent) {
        if (event.path == "/connection_accept") {
            val resposta = String(event.data)

            if (resposta == "ACK") {
                _uiState.update {
                    it.copy(
                        isConnecting = false,
                        connectedDeviceId = event.sourceNodeId
                    )
                }
            }
        }
    }
}