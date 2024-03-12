package com.example.recipeapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import com.example.recipeapp.core.router.Routes

@Composable
fun HomeScreen(
    navController: NavController
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(text = "Welcome to RecipeApp")

        Button(onClick = {

            navController.navigate(Routes.categoriesScreen)


        }) {
            Text(text = "Get Started")
        }
        Button(onClick = {
            navController.navigate(Routes.profileScreen)

        }) {
            Text(text = "Profile")
        }
    }
}