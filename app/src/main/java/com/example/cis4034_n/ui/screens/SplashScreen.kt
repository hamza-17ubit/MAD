package com.example.cis4034_n.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.cis4034_n.ui.composables.TextFieldTitle
import com.example.cis4034_n.ui.navigations.NavigationScreens
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController,) {
    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        TextFieldTitle(text = "Splash Screen")
    }

    LaunchedEffect(key1 = true) {
        delay(3000) // 3 second delay
        navController.navigate(NavigationScreens.MainScreen.route) {
            popUpTo(NavigationScreens.SplashScreen.route) { inclusive = true }
        }
    }
}