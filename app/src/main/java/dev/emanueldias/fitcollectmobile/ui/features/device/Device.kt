package dev.emanueldias.fitcollectmobile.ui.features.device

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.emanueldias.fitcollectmobile.ui.components.DeviceComponent
import dev.emanueldias.fitcollectmobile.ui.components.Loading
import dev.emanueldias.fitcollectmobile.ui.theme.AndroidGreen
import dev.emanueldias.fitcollectmobile.ui.theme.FitCollectMobileTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeviceScreen(
    viewModel: DeviceViewModel = viewModel(),
    onClickGoToCollects: () -> Unit = {}
) {

    DisposableEffect(Unit) {
        viewModel.registerListener()
        onDispose {
            viewModel.removeListener()
        }
    }

    val uiState = viewModel.uiState.collectAsState().value

    if(uiState.isConnecting) {
        Dialog(
           onDismissRequest = {}
        ) {
            Card() {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Conectando...")
                    Loading()
                }
            }
        }
    }

    if (uiState.hasError) {
        Dialog(
            onDismissRequest = {}
        ) {
            Card() {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Erro: ${uiState.messageError}")
                }
            }
        }
    }



    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("FitCollect") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = AndroidGreen,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                )
            )
        }
    ) { innerPadding ->
        if(uiState.connectedDeviceId != null) {
            Column(
                modifier = Modifier.padding(innerPadding).fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Conectado com dispositivo: ${uiState.connectedDeviceId}")

                Spacer(modifier = Modifier.height(50.dp))

                Button(
                    onClick = onClickGoToCollects
                ) {
                    Text("Ir para coletas")
                }
            }
        } else {
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {

                if (uiState.devices.isNotEmpty()) {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        contentPadding = androidx.compose.foundation.layout.PaddingValues(16.dp)
                    ) {
                        items(uiState.devices.size) { index ->
                            val item = uiState.devices[index]
                            DeviceComponent(
                                name = item.name,
                                modifier = Modifier.clickable {
                                    viewModel.sendRequestToConnect(item.id)
                                }
                            )
                        }
                    }
                } else if (!uiState.isScanning) {
                    Text(
                        text = "Nenhum dispositivo encontrado.",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }

                if (uiState.isScanning) {
                    Loading()
                }
            }
        }

    }
}

@Preview
@Composable
private fun DeviceScreenPreview() {
    FitCollectMobileTheme() {
        DeviceScreen()
    }
}