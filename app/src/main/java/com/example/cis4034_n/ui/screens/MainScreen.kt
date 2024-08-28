package com.example.cis4034_n.ui.screens

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.cis4034_n.ui.composables.TextFieldNormal
import com.example.cis4034_n.ui.composables.TopBarCompose
import com.example.cis4034_n.ui.navigations.NavigationScreens

@Composable
fun MainScreen(navHostController: NavHostController) {

    val context = LocalContext.current
    var batteryPercentage by remember { mutableIntStateOf(0) }

    val batteryReceiver = remember {
        object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                val level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
                val scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
                batteryPercentage = (level / scale.toFloat() * 100).toInt()

            }
        }
    }

    DisposableEffect(Unit) {
        val intentFilter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        context.registerReceiver(batteryReceiver, intentFilter)

        onDispose {
            context.unregisterReceiver(batteryReceiver)
        }
    }

    Column {
        TopBarCompose(title = "Selection Option", navHostController = navHostController)

        Row(
            modifier = Modifier
                .padding(15.dp)
                .background(
                    color = MaterialTheme.colorScheme.primaryContainer,
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(15.dp)
        ) {
            TextFieldNormal(text = "Battery Percentage: $batteryPercentage%")
        }

        Row(modifier = Modifier
            .padding(15.dp)
            .background(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = RoundedCornerShape(10.dp)
            )
            .clickable {
                navHostController.navigate(NavigationScreens.CameraScreen.route)
            }
            .padding(15.dp)) {
            TextFieldNormal(text = "Camera")
        }

        Row(modifier = Modifier
            .padding(15.dp)
            .background(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = RoundedCornerShape(10.dp)
            )
            .clickable {
                navHostController.navigate(NavigationScreens.LocationScreen.route)
            }
            .padding(15.dp)) {
            TextFieldNormal(text = "Location")
        }

        Row(modifier = Modifier
            .padding(15.dp)
            .background(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = RoundedCornerShape(10.dp)
            )
            .clickable {
                navHostController.navigate(NavigationScreens.SelectionScreen.route)
            }
            .padding(15.dp)) {
            TextFieldNormal(text = "Web API")
        }

    }
}