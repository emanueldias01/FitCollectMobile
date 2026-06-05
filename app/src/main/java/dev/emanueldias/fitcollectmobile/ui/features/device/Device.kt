package dev.emanueldias.fitcollectmobile.ui.features.device

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.emanueldias.fitcollectmobile.ui.theme.AndroidGreen
import dev.emanueldias.fitcollectmobile.ui.theme.FitCollectMobileTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeviceScreen(modifier: Modifier = Modifier) {
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
        Column(
            modifier = Modifier.padding(innerPadding)
                .fillMaxSize()
        ) {

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