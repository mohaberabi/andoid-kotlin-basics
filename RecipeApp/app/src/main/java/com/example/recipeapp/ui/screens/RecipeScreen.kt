package com.example.recipeapp.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.recipeapp.core.router.Routes
import com.example.recipeapp.core.router.Screen
import com.example.recipeapp.ui.composable.CategoriesBuilder
import com.example.recipeapp.ui.viewmodels.CategoriesViewModel

@Composable

fun RecipeScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    val categoriesViewModel: CategoriesViewModel = viewModel()
    val categoryState by categoriesViewModel.categoriesState

    Box(modifier = Modifier.fillMaxSize()) {
        
        when {
            categoryState.loading -> {
                CircularProgressIndicator(modifier.align(Alignment.Center))
            }

            categoryState.error.isNotBlank() -> {
                Text(text = categoryState.error, style = TextStyle(color = Color.Red))
            }

            else -> {
                CategoriesBuilder(categories = categoryState.categories, onPressed = {
                    navController.currentBackStackEntry?.savedStateHandle?.set("cat", it)
                    navController.navigate(Screen.categorydetailsScreen.route)
                })
            }
        }

    }


}
