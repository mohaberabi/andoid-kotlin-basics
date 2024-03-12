package com.example.locatify

import android.Manifest
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.locatify.ui.theme.LocatifyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: LocationViewModel = viewModel()
            LocatifyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LocatifyApp(viewModel)
                }
            }
        }
    }


}

@Composable
fun LocatifyApp(viewModel: LocationViewModel) {
    val context = LocalContext.current
    val locationUtil = LocationUtil(context = context)

    LocationView(locationUtil = locationUtil, context = context, locationViewModel = viewModel)
}

@Composable
fun LocationView(
    locationUtil: LocationUtil,
    context: Context,
    locationViewModel: LocationViewModel
) {

    val location = locationViewModel.location.value
    val requestPermssionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = { permissionMap ->

            if (permissionMap[Manifest.permission.ACCESS_COARSE_LOCATION] == true
                && permissionMap[Manifest.permission.ACCESS_FINE_LOCATION] == true
            ) {
                locationUtil.requestLocationUpdates(viewModel = locationViewModel)
            } else {
                val rationalRequired = ActivityCompat.shouldShowRequestPermissionRationale(
                    context as MainActivity,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) || ActivityCompat.shouldShowRequestPermissionRationale(
                    context as MainActivity,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )

                if (rationalRequired) {
                    Toast.makeText(context, "We Need Location Idiot", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(
                        context,
                        "Open The App Settings to enable the locaition permsission",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

        },
    )
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (location != null) {


            val address = locationUtil.reverseGeoCode(location)
            Text(text = address)
        } else {
            Text(text = "Please Enable Location Services")
        }
        Button(onClick = {
            if (locationUtil.hasLocationPermission(context)) {
                locationUtil.requestLocationUpdates(viewModel = locationViewModel)

            } else {
                requestPermssionLauncher.launch(
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    )
                )
            }
        }) {
            Text(text = "Enable")
        }
    }
}