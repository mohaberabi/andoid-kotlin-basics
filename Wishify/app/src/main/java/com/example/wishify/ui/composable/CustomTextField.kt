package com.example.wishify.ui.composable

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    label: String = "",
    value: String = "",
    onValueChanged: (String) -> Unit = {},
) {

    OutlinedTextField(
        value = value,
        modifier = Modifier.padding(16.dp),
        label = { Text(text = label) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        onValueChange = onValueChanged,
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = Color.Black,
            unfocusedBorderColor = Color.DarkGray,
            errorBorderColor = Color.Red,

            )
    )
}

@Preview(showBackground = true)
@Composable
fun ViewField() {
    CustomTextField(label = "Label")
}