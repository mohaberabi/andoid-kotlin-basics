package com.example.recipeapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

@Composable

fun ProfileScreen(name: String) {
    
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Welcome ${name}")
    }
}