package com.example.wishify.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.wishify.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    title: String,
    onBackPressed: () -> Unit = {},

    showNavIcon: Boolean = true,
) {

    TopAppBar(
        title = {
            Text(
                text = title,
                color = Color.White,
                modifier = Modifier
                    .padding(8.dp)
                    .heightIn(max = 24.dp)
            )
        },
        colors = topAppBarColors(
            containerColor = appBarColor
        ),

        navigationIcon = {
            if (showNavIcon) {
                AppBarNavIcon(onBackPressed = onBackPressed)

            } else {
                Spacer(modifier = Modifier)
            }
        }

    )
}

val appBarColor = Color(R.color.appBarColor)

@Composable
fun AppBarNavIcon(
    onBackPressed: () -> Unit = {}

) {
    IconButton(onClick = onBackPressed) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            tint = Color.White,
            contentDescription = null
        )
    }
}