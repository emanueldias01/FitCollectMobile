package dev.emanueldias.fitcollectmobile.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.emanueldias.fitcollectmobile.R
import dev.emanueldias.fitcollectmobile.ui.theme.FitCollectMobileTheme

@Composable
fun DeviceComponent(
    name: String,
) {

    Card(
        modifier = Modifier.fillMaxWidth(),

    ) {
        Row(
            modifier = Modifier.padding(20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Icon(painterResource(R.drawable.outline_watch_24), contentDescription = null)

            Text(name)
        }
    }
}

@Preview
@Composable
private fun DevicePreview() {
    FitCollectMobileTheme() {
        DeviceComponent("Watch6")
    }

}