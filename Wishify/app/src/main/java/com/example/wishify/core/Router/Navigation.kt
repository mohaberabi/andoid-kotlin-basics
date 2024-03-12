package com.example.wishify.core.Router

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.wishify.ui.screen.AddWishScreen
import com.example.wishify.ui.screen.HomeView
import com.example.wishify.ui.viewmodels.WishViewModel

@Composable
fun Navigation(
    navHostController: NavHostController = rememberNavController(),
    wishViewModel: WishViewModel,
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.HomeScreen.route,
        builder = {
            composable(Screen.HomeScreen.route) {
                HomeView(navHostController)
            }
            composable(Screen.AddWishScreen.route) {
                AddWishScreen(
                    navHostController = navHostController,
                    wishViewModel = wishViewModel,
                    id = 0L
                )

            }
        },
    )
}