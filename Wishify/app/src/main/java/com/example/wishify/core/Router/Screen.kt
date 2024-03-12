package com.example.wishify.core.Router

sealed class Screen(val route: String) {

    data object HomeScreen : Screen(AppRoutes.homeScreen)
    data object AddWishScreen : Screen(AppRoutes.addWishScreen)

}


object AppRoutes {
    const val homeScreen: String = "homeScreen/"
    const val addWishScreen: String = "addWishScreen/"
}