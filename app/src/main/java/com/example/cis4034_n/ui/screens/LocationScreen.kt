package com.example.cis4034_n.ui.screens

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import com.example.cis4034_n.ui.composables.TextFieldNormal
import com.example.cis4034_n.ui.composables.TopBarCompose
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine

@Composable
fun LocationScreen(navHostController: NavHostController) {
    var hasLocationPermission by remember { mutableStateOf(false) }
    var currentLocation by remember { mutableStateOf<Location?>(null) }

    LocationPermission { granted ->
        hasLocationPermission = granted
    }

    Column {
        TopBarCompose(title = "Location", showBackBtn = true, navHostController = navHostController)

        if (hasLocationPermission) {
            val context = LocalContext.current
            GetCurrentLocation(context) { location ->
                currentLocation = location
            }

            currentLocation?.let { location ->
                TextFieldNormal(text = " Location: ${location.latitude}, ${location.longitude}")
            } ?: TextFieldNormal(text = "Getting location...")
        } else {
            TextFieldNormal(text = "Location permission not granted")
        }
    }
}

@Composable
fun LocationPermission(
    onPermissionResult: (Boolean) -> Unit
) {
    val context = LocalContext.current

    val permissions = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION)
    val isPermissionGranted = remember {
        ContextCompat.checkSelfPermission(
            context, Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    if (isPermissionGranted) {
        onPermissionResult(true)
    } else {
        ActivityCompat.requestPermissions(
            context as ComponentActivity, permissions, 0
        )
    }
}

@Composable
fun GetCurrentLocation(
    context: Context, onLocationReceived: (Location) -> Unit
) {
    val fusedLocationClient = remember { LocationServices.getFusedLocationProviderClient(context) }
    var location by remember { mutableStateOf<Location?>(null) }

    LaunchedEffect(Unit) {
        location = getLastKnownLocation(fusedLocationClient)
        location?.let { onLocationReceived(it) }
    }
}

@OptIn(ExperimentalCoroutinesApi::class)
@SuppressLint("MissingPermission")
suspend fun getLastKnownLocation(fusedLocationClient: FusedLocationProviderClient): Location? {
    return suspendCancellableCoroutine { continuation ->
        fusedLocationClient.lastLocation.addOnCompleteListener { task: Task<Location> ->
            if (task.isSuccessful && task.result != null) {
                continuation.resume(task.result) {}
            } else {
                continuation.resume(null) {}
            }
        }
    }
}