package com.example.recipeapp.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.recipeapp.core.router.Screen
import com.example.recipeapp.data.model.Category
import com.example.recipeapp.ui.screens.CategoryDetailsScreen
import com.example.recipeapp.ui.screens.HomeScreen
import com.example.recipeapp.ui.screens.ProfileScreen
import com.example.recipeapp.ui.screens.RecipeScreen
import com.example.recipeapp.ui.viewmodels.CategoriesViewModel

@Composable
fun RecipeApp(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route,
        builder = {

            composable(route = Screen.HomeScreen.route) {

                HomeScreen(navController = navController)
            }
            composable(Screen.RecipeScreen.route) {
                RecipeScreen(navController = navController)
            }
            composable("${Screen.profileScreen.route}{name}") {
                val name = it.arguments?.getString("name") ?: "No Name Passed "
                ProfileScreen(name)
            }
            composable(Screen.categorydetailsScreen.route) {

                val category =
                    navController.previousBackStackEntry?.savedStateHandle?.get<Category>("cat")
                        ?: Category("", "", "", "")
                navController.navigate(Screen.categorydetailsScreen.route)
                CategoryDetailsScreen(category = category)
            }
        })
}

