package com.example.recipeapp.core.router

sealed class Screen(val route: String) {

    object RecipeScreen : Screen(Routes.categoriesScreen)
    object HomeScreen : Screen(Routes.homeScreen)
    object profileScreen : Screen(Routes.profileScreen)
    object categorydetailsScreen : Screen(Routes.categoryDetailScreen)

}