package com.example.wishify.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.wishify.core.Router.Screen
import com.example.wishify.data.model.WishItem
import com.example.wishify.ui.composable.AppBar
import com.example.wishify.ui.composable.WishCard


@Composable

fun HomeView(navController: NavController) {

    Scaffold(topBar = {
        AppBar(
            title = "Wishify",
            showNavIcon = false
        )
    }, floatingActionButton = {
        Fab() {
            navController.navigate(Screen.AddWishScreen.route)
        }
    }) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
//            items(TestData.wishList) { wish: WishItem ->
//                WishCard(wish = wish)
//            }
        }
    }
}

@Composable
fun Fab(onTap: () -> Unit = {}) {
    FloatingActionButton(
        onClick = onTap,
        containerColor = Color.Black,
        contentColor = Color.White,
        modifier = Modifier.padding(20.dp)
    ) {
        Icon(imageVector = Icons.Default.Add, contentDescription = null)
    }
}