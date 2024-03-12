package com.example.unitconvertor

import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconvertor.ui.theme.UnitConvertorTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConvertorTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConvertor()
                }
            }
        }
    }
}

//@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun UnitConvertor(onValueChanged: (String) -> Unit = {}, value: String = "Type Value...") {


    val fromExpaned = remember {
        mutableStateOf(false)
    }

    val toExpand = remember {
        mutableStateOf(false)
    }
    val inputValue = remember {
        mutableStateOf("")
    }
    val result = remember {
        mutableStateOf("")
    }

    val toUnit = remember {
        mutableStateOf("metres")
    }

    val fromUnit = remember {
        mutableStateOf("cm")
    }


    val factor = remember {
        mutableStateOf<Double>(0.01)
    }

    fun convert() {

        val input = inputValue.value.toDoubleOrNull() ?: 0.0


        val finalResult = input * factor.value * 100
        result.value = ((finalResult).roundToInt() / 100).toString()

    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {

        OutlinedTextField(value = inputValue.value, onValueChange = {


            inputValue.value = it

            convert()
        })

        Spacer(modifier = Modifier.height(36.dp))

        Row {


            Box(contentAlignment = Alignment.Center) {
                Button(onClick = {
                    fromExpaned.value = !fromExpaned.value
                }) {
                    Text(text = "Select")
                    Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "")
                }


            }
            Spacer(modifier = Modifier.width(16.dp))
            DropdownMenu(expanded = fromExpaned.value, onDismissRequest = {
                fromExpaned.value = false
            }) {
                DropdownMenuItem(text = { Text(text = "cm") }, onClick = {
                    fromUnit.value = "cm"
                    factor.value = 0.01
                    fromExpaned.value = false

                })
                DropdownMenuItem(text = { Text(text = "metres") }, onClick = {
                    fromUnit.value = "metres"
                    factor.value = 0.01
                    fromExpaned.value = false

                })
                DropdownMenuItem(text = { Text(text = "feet") }, onClick = {
                    fromUnit.value = "metres"
                    factor.value = 0.01
                    fromExpaned.value = false

                })
                DropdownMenuItem(text = { Text(text = "mlm") }, onClick = {
                    fromUnit.value = "metres"
                    factor.value = 0.01
                    fromExpaned.value = false

                })
            }


            Box(contentAlignment = Alignment.Center) {
                Button(onClick = {
                    toExpand.value = !toExpand.value

                }) {
                    Text(text = "Select")
                    Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "")
                }
                DropdownMenu(expanded = toExpand.value, onDismissRequest = {
                    fromExpaned.value = false

                }) {
                    DropdownMenuItem(text = { Text(text = "cm") }, onClick = {})
                    DropdownMenuItem(text = { Text(text = "metres") }, onClick = {})
                    DropdownMenuItem(text = { Text(text = "feet") }, onClick = {})
                    DropdownMenuItem(text = { Text(text = "mlm") }, onClick = {})
                }


            }
        }

        Spacer(modifier = Modifier.height(36.dp))
        Text(text = "Result :${result.value}", style = TextStyle(fontSize = 24.sp))

    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    UnitConvertor()
}

@Composable
fun StateTest() {
    val treasurFound = remember {
        mutableStateOf<Int>(0)
    }
    var direction by remember {
        mutableStateOf<String>("North")
    }
    Column {

        Text(text = "Treasures ${treasurFound.value}")
        Text(text = "Diretion ${direction}")



        Button(onClick = {


            treasurFound.value = 10
        }) {
            Text(text = "Increment")
        }
        Button(onClick = {


            direction = "East"
        }) {
            Text(text = "Change Direction")
        }
    }
}

