package com.example.mvvmapp2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mvvmapp2.ui.theme.MVVMAPP2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: CounterViewModel = viewModel()
            MVVMAPP2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {

                        CounterApp(viewModel = viewModel)
                    }
                }
            }
        }
    }
}

@Composable
fun CounterApp(viewModel: CounterViewModel) {

    Text(text = "${viewModel.count.value}")
    Row {
        Button(onClick = { viewModel.increment() }) {
            Text(text = "+")
        }
        Button(onClick = {
            viewModel.decrement()
        }) {
            Text(text = "-")
        }
    }
}