package dev.emanueldias.fitcollectmobile.ui.navigation

import kotlinx.serialization.Serializable

sealed interface Screen {

    @Serializable
    data object Initial : Screen

    @Serializable
    data object Devices : Screen

    @Serializable
    data object Collects: Screen

    @Serializable
    data class WorkoutDetail(val startTime: Long) : Screen
}