package dev.emanueldias.fitcollectmobile.ui.features.collects

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
import dev.emanueldias.fitcollectmobile.ui.theme.AndroidGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CollectsScreen() {

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
            modifier = Modifier.padding(innerPadding).fillMaxSize()
        ) {
            Text("Coletas")
        }

    }
}
