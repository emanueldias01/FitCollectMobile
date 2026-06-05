package dev.emanueldias.fitcollectmobile.ui.navigation

import kotlinx.serialization.Serializable

sealed interface Screen {

    @Serializable
    data object Initial : Screen

    @Serializable
    data object Devices : Screen

}