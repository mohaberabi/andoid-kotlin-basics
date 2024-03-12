package com.example.recipeapp.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.recipeapp.data.model.Category
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable
fun CategoriesBuilder(categories: List<Category>, onPressed: (Category) -> Unit) {


    LazyVerticalGrid(columns = GridCells.Fixed(2), content = {

        items(categories) { cat ->
            CategoryCard(category = cat, onPressed)
        }

    })
}

@Composable
fun CategoryCard(category: Category, onPressed: (Category) -> Unit) {

    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()
            .clickable {
                onPressed(category)
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = rememberAsyncImagePainter(model = category.strCategoryThumb),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(1f)
        )
        Text(
            text = category.strCategory,
            style = TextStyle(fontWeight = FontWeight.Bold),
        )

    }
}