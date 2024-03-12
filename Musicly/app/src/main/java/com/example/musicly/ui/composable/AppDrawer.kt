package com.example.musicly.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.musicly.core.router.Screen


@Composable

fun DrawerItem(
    selected: Boolean,
    item: Screen.DrawerScreen,
    onItemPress: () -> Unit
) {
    val background = if (selected) Color.Gray else Color.Transparent
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 8.dp)
        .background(background)
        .clickable {
            onItemPress
        }) {
        Icon(
            painter = painterResource(id = item.icon),
            contentDescription = null,
            modifier = Modifier.padding(
                end = 8.dp,
                top = 4.dp
            )

        )
        Text(
            text = item.title,
            style = MaterialTheme.typography.headlineMedium,
        )

    }
}


val drawerItems =
    listOf<Screen.DrawerScreen>(
        Screen.DrawerScreen.Account,
        Screen.DrawerScreen.AddAccount,
        Screen.DrawerScreen.Subscribiton

    )





