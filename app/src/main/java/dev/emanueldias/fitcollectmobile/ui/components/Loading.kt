package dev.emanueldias.fitcollectmobile.ui.components

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.emanueldias.fitcollectmobile.ui.theme.FitCollectMobileTheme

@Composable
fun Loading(modifier: Modifier = Modifier) {
    CircularProgressIndicator(
        modifier = modifier
    )
}

@Preview
@Composable
private fun LoadingPreview() {
    FitCollectMobileTheme() {
        Loading()
    }
}
