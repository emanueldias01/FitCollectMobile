package dev.emanueldias.fitcollectmobile.ui.features.initial

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import kotlinx.coroutines.delay

@Composable
fun InitialScreen(
    onButtonClickSearchDevices : () -> Unit
) {

    var logoVisibility by remember{ mutableStateOf(false) }
    var nameAppVisibility by remember { mutableStateOf(false) }
    var textVisibility by remember { mutableStateOf(false) }
    var buttonVisibility by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(500)
        logoVisibility = true
        delay(500)
        nameAppVisibility = true
        delay(500)
        textVisibility = true
        delay(500)
        buttonVisibility = true
    }

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
                AnimatedVisibility(
                    visible = logoVisibility,
                    enter = fadeIn(animationSpec = tween(600)) + slideInVertically(initialOffsetY = { it / 2 })
                ) {
                    Image(
                        painter = painterResource(R.drawable.outline_fitness_center_24),
                        contentDescription = "logo",
                        modifier = Modifier.size(100.dp)
                    )

                    Spacer(
                        modifier = Modifier.size(20.dp)
                    )
                }



                AnimatedVisibility(
                    visible = nameAppVisibility,
                    enter = fadeIn(animationSpec = tween(600)) + slideInVertically(initialOffsetY = { it / 2 })
                ) {
                    Text(
                        text = "FitCollect",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = AndroidGreenDark,
                        modifier = Modifier.padding(30.dp)
                    )
                }
            }

            AnimatedVisibility(
                visible = textVisibility,
                enter = fadeIn(animationSpec = tween(600)) + slideInVertically(initialOffsetY = { it / 2 })
            ) {
                Text(
                    "Colete seus dados através de um WearOS",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.W500,
                    color = AndroidGreenDark
                )

                Spacer(modifier = Modifier.size(100.dp))
            }




            AnimatedVisibility(
                visible = buttonVisibility,
                enter = fadeIn(animationSpec = tween(600)) + slideInVertically(initialOffsetY = { it / 2 })
            ) {
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