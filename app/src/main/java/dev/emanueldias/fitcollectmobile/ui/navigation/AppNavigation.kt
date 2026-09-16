package dev.emanueldias.fitcollectmobile.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.emanueldias.fitcollectmobile.ui.features.collects.CollectsScreen
import dev.emanueldias.fitcollectmobile.ui.features.collects.WorkoutDetailScreen
import dev.emanueldias.fitcollectmobile.ui.features.device.DeviceScreen
import dev.emanueldias.fitcollectmobile.ui.features.initial.InitialScreen
import androidx.navigation.toRoute

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Initial
    ) {
        composable<Screen.Initial> {
            InitialScreen(
                onButtonClickSearchDevices = {
                    navController.navigate(Screen.Devices)
                }
            )
        }

        composable<Screen.Devices> {
            DeviceScreen(
                onClickGoToCollects = {
                    navController.navigate(Screen.Collects)
                }
            )
        }

        composable<Screen.Collects> {
            CollectsScreen(
                onWorkoutClick = { startTime ->
                    navController.navigate(Screen.WorkoutDetail(startTime))
                }
            )
        }

        composable<Screen.WorkoutDetail> { backStackEntry ->
            val args = backStackEntry.toRoute<Screen.WorkoutDetail>()
            WorkoutDetailScreen(
                startTime = args.startTime,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}