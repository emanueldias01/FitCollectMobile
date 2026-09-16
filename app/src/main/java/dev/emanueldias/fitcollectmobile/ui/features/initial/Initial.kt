package dev.emanueldias.fitcollectmobile.ui.features.initial

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.emanueldias.fitcollectmobile.R
import dev.emanueldias.fitcollectmobile.ui.theme.FitCollectMobileTheme
import kotlinx.coroutines.delay

@Composable
fun InitialScreen(
    viewModel: InitialViewModel = viewModel(),
    onButtonClickSearchDevices: () -> Unit,
) {
    val uiState = viewModel.uiState.collectAsState().value

    LaunchedEffect(Unit) {
        delay(300)
        viewModel.activeLogoVisibility()
        delay(250)
        viewModel.activeNameAppVisibility()
        delay(250)
        viewModel.activeTextVisibility()
        delay(250)
        viewModel.activeButtonVisibility()
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            // Center Logo & Title Info
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.weight(1f)
            ) {
                AnimatedVisibility(
                    visible = uiState.logoVisibility,
                    enter = fadeIn(animationSpec = tween(500)) + slideInVertically(initialOffsetY = { it / 3 })
                ) {
                    Surface(
                        modifier = Modifier.size(112.dp),
                        shape = RoundedCornerShape(28.dp),
                        color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Image(
                                painter = painterResource(R.drawable.outline_fitness_center_24),
                                contentDescription = "logo",
                                modifier = Modifier.size(56.dp),
                                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                AnimatedVisibility(
                    visible = uiState.nameAppVisibility,
                    enter = fadeIn(animationSpec = tween(500)) + slideInVertically(initialOffsetY = { it / 3 })
                ) {
                    Text(
                        text = "FitCollect",
                        fontSize = 36.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colorScheme.onBackground,
                        letterSpacing = (-1).sp
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                AnimatedVisibility(
                    visible = uiState.textVisibility,
                    enter = fadeIn(animationSpec = tween(500)) + slideInVertically(initialOffsetY = { it / 3 })
                ) {
                    Text(
                        text = "Sincronize e gerencie suas coletas físicas diretamente do seu smartwatch WearOS.",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 24.dp),
                        lineHeight = 24.sp
                    )
                }
            }

            // Bottom CTA Button
            AnimatedVisibility(
                visible = uiState.buttonVisibility,
                enter = fadeIn(animationSpec = tween(500)) + slideInVertically(initialOffsetY = { it / 3 }),
                modifier = Modifier.padding(bottom = 48.dp)
            ) {
                Button(
                    onClick = onButtonClickSearchDevices,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(28.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp)
                ) {
                    Text(
                        text = "Buscar Dispositivo",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun InitialScreenPreview() {
    FitCollectMobileTheme {
        InitialScreen(
            onButtonClickSearchDevices = {}
        )
    }
}
