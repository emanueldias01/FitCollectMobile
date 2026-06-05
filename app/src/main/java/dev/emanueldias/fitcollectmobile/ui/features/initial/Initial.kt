package dev.emanueldias.fitcollectmobile.ui.features.initial

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.emanueldias.fitcollectmobile.R
import dev.emanueldias.fitcollectmobile.ui.theme.AndroidGreen
import dev.emanueldias.fitcollectmobile.ui.theme.AndroidGreenDark
import dev.emanueldias.fitcollectmobile.ui.theme.AndroidWhite
import dev.emanueldias.fitcollectmobile.ui.theme.FitCollectMobileTheme

@Composable
fun InitialScreen(
    onButtonClickSearchDevices : () -> Unit
) {

    Scaffold() { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {

            Column(
                modifier = Modifier.padding(50.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.outline_fitness_center_24),
                    contentDescription = "logo",
                    modifier = Modifier.size(130.dp)
                )

                Spacer(
                    modifier = Modifier.size(20.dp)
                )

                Text(
                    text = "FitCollect",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = AndroidGreenDark
                )
            }

            Text(
                "Colete seus dados através de um WearOS",
                fontSize = 15.sp,
                fontWeight = FontWeight.W500,
                color = AndroidGreenDark
            )


            Spacer(modifier = Modifier.size(100.dp))


            ElevatedButton(
                onClick = onButtonClickSearchDevices,
                colors = ButtonColors(
                    containerColor = AndroidGreen,
                    contentColor = AndroidWhite,
                    disabledContainerColor = AndroidGreenDark,
                    disabledContentColor = AndroidWhite
                )
            ) {

                Text("Buscar dispositivo")

            }
        }
    }
}

@Preview
@Composable
private fun InitialScreenPreview() {
    FitCollectMobileTheme() {
        InitialScreen(
            onButtonClickSearchDevices = {}
        )
    }
}