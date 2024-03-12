package com.example.locatify

import android.content.Context
import androidx.core.content.ContextCompat
import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.os.Looper
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.model.LatLng
import java.util.Locale

class LocationUtil(val context: Context) {
    private val _fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    fun hasLocationPermission(context: Context): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    @SuppressLint("MissingPermission")
    fun requestLocationUpdates(viewModel: LocationViewModel) {
        val locationCallback = object : LocationCallback() {
            override fun onLocationResult(location: LocationResult) {
                super.onLocationResult(location)
                location.lastLocation?.let {
                    val location = LocationData(lat = it.latitude, lng = it.longitude)
                    viewModel.updateLocation(location)
                }
            }


        }
        val locationRequest =
            LocationRequest.Builder(
                Priority.PRIORITY_HIGH_ACCURACY,
                1000
            ).build()
        _fusedLocationClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.getMainLooper()
        )
    }

    fun reverseGeoCode(location: LocationData): String {
        val geocoder = Geocoder(context, Locale.getDefault())
        var cordinates = LatLng(location.lat, location.lng)
        val addresss: MutableList<Address>? =
            geocoder.getFromLocation(cordinates.latitude, cordinates.longitude, 1)

        if (addresss == null) {
            return "CouldNot Get Loaction"
        } else {
            return addresss.first().countryName
        }
    }
}