package com.example.shppi

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun ShoppiApp() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {

        val itemName = remember {
            mutableStateOf("")
        }
        val itemQty = remember {
            mutableStateOf("")
        }

        val showDialog = remember {
            mutableStateOf(false)
        }

        var items = remember {
            mutableStateOf<MutableList<ShopItem>>(mutableListOf<ShopItem>())
        }



        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PrimaryButton(onClick = { showDialog.value = true }, label = "Add Item", enabled = true)

            ItemsLazyColumn(items = items.value)
        }
        if (showDialog.value) {

            AlertDialog(
                onDismissRequest = {
                    showDialog.value = false
                },
                confirmButton = {},
                title = { Text(text = "Add Item") }, text = {
                    Column {


                        OutlinedTextField(
                            value = itemName.value,
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(),
                            onValueChange = {
                                itemName.value = it
                            },
                            label = { Text(text = "Name ") })

                        OutlinedTextField(
                            value = itemQty.value,
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(),
                            onValueChange = {
                                itemQty.value = it
                            },
                            label = { Text(text = "Quantity ") })


                        Row(horizontalArrangement = Arrangement.Center) {
                            PrimaryButton(
                                onClick = {
                                    showDialog.value = false
                                }, label = "Cancel",
                                enabled = true
                            )


                            Spacer(modifier = Modifier.width(16.dp))
                            PrimaryButton(
                                onClick = {
                                    val item = ShopItem(
                                        name = itemName.value,
                                        qty = itemQty.value.toDouble(),
                                        id = Random.nextInt().toString()
                                    )
                                    items.value.add(item)
                                    showDialog.value = false
                                    itemName.value = ""
                                    itemQty.value = ""
                                },
                                label = "Save",
                                enabled =
                                itemName.value.isNotBlank() && itemQty.value.isNotBlank()
                            )
                        }
                    }
                })
        }
    }
}