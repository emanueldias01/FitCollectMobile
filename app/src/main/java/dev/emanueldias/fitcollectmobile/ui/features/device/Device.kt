package dev.emanueldias.fitcollectmobile.ui.features.device

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.emanueldias.fitcollectmobile.ui.components.DeviceComponent
import dev.emanueldias.fitcollectmobile.ui.components.Loading
import dev.emanueldias.fitcollectmobile.ui.theme.AndroidGreen
import dev.emanueldias.fitcollectmobile.ui.theme.FitCollectMobileTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeviceScreen(
    viewModel: DeviceViewModel = viewModel(),
) {

    val uiState = viewModel.uiState.collectAsState().value

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
                        DeviceComponent(name = item.name)
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

@Preview
@Composable
private fun DeviceScreenPreview() {
    FitCollectMobileTheme() {
        DeviceScreen()
    }
}