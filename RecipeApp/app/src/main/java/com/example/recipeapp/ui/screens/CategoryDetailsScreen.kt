package com.example.recipeapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.recipeapp.core.router.Routes
import com.example.recipeapp.data.model.Category
import okhttp3.Route


@Composable
fun CategoryDetailsScreen(category: Category) {


    Column(

        modifier = Modifier
            .fillMaxSize()
            .clickable {

            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = category.strCategoryThumb),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )
        Text(
            modifier = Modifier.padding(16.dp),
            text = category.strCategory,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
        )
        Text(
            modifier = Modifier
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            text = category.strCategoryDescription,
            textAlign = TextAlign.Justify,
            style = TextStyle(
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp
            )
        )
    }
}