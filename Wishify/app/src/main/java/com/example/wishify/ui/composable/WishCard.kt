package com.example.wishify.ui.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.wishify.data.model.WishItem


@Composable
fun WishCard(
    wish: WishItem,
    onItemPress: () -> Unit = {}
) {

    Card(

        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onItemPress() },


        ) {

        Column(
            modifier =
            Modifier.padding(16.dp)
        ) {
            Text(text = wish.name, fontWeight = FontWeight.ExtraBold)
            Text(text = wish.describtion, fontWeight = FontWeight.Normal)
        }
    }
}