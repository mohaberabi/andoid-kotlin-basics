package com.example.shppi

import android.provider.CalendarContract.Colors
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp


@Composable
fun ItemsLazyColumn(items: (List<ShopItem>)) {
    LazyColumn(

        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        items(items) { item ->

            if (item.isEdit) {
                ItemEditor(item = item, onEditDone = { name, qty ->
                    items.map {
                        it.copy(
                            name = name,
                            qty = qty,
                            isEdit = false
                        )
                    }

                    val newItem = items.find { it.id == item.id }
                    newItem?.let {
                        it.name = name
                        it.qty = qty
                    }

                })
            } else {
                ShopItem(item = item, onEdit = {
//                    items = items.map { it.copy(isEdit = it.id == item.id) }
                }, onDelete = {})

            }
        }
    }
}

@Composable
fun PrimaryButton(onClick: () -> Unit, label: String = "Add Item ", enabled: Boolean = false) {
    Button(
        onClick = onClick,
        enabled = enabled, shape = RectangleShape,
    ) {
        Text(text = label)
    }
}


@Composable
fun ShopItem(
    item: ShopItem,
    onDelete: () -> Unit,
    onEdit: () -> Unit,
) {

    Row {

        Spacer(modifier = Modifier.width(10.dp))
        Column {
            Text(text = item.name)
            Text(text = item.qty.toString())
            Row(horizontalArrangement = Arrangement.Center) {
                IconButton(onClick = onEdit) {
                    Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit item ")
                }
                Spacer(modifier = Modifier.width(16.dp))
                IconButton(onClick = onDelete) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete item ")
                }
            }
        }
    }
}

@Composable
fun ItemEditor(
    item: ShopItem,
    onEditDone: (String, Double) -> Unit
) {
    val name = remember {
        mutableStateOf(item.name)
    }
    val qty = remember {
        mutableStateOf(item.qty.toString())
    }

    val isEdit = remember {
        mutableStateOf(item.isEdit)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {

        Column {
            BasicTextField(value = name.value,
                singleLine = true,
                modifier = Modifier.wrapContentSize(),
                onValueChange = {
                    name.value = it
                }
            )
            BasicTextField(value = name.value,
                modifier = Modifier.wrapContentSize(),
                singleLine = true,
                onValueChange = {
                    qty.value = it
                }
            )
        }


        PrimaryButton(onClick = {
            onEditDone(
                name.value,
                qty.value.toDouble()
            )
            isEdit.value = false
        }, enabled = true, label = "Save")
    }
}
