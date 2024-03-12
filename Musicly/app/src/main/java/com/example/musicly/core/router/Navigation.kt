package com.example.musicly.core.router

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable

fun Navigation(navController: NavHostController) {


    NavHost(
        navController = navController,
        startDestination = Screen.DrawerScreen.Account.route,
        builder = {
            composable(Screen.DrawerScreen.Account.route) {
                
            }
        },
    )
}