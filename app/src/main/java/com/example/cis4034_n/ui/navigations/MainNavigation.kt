package com.example.cis4034_n.ui.navigations

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.cis4034_n.ui.screens.CameraScreen
import com.example.cis4034_n.ui.screens.LocationScreen
import com.example.cis4034_n.ui.screens.MainScreen
import com.example.cis4034_n.ui.screens.QuestionsScreen
import com.example.cis4034_n.ui.screens.ResultScreen
import com.example.cis4034_n.ui.screens.SelectionScreen
import com.example.cis4034_n.ui.screens.SplashScreen
import com.example.cis4034_n.ui.viewModels.SelectionViewModel

@Composable
fun MainNavigation(context: Context, navController: NavHostController = rememberNavController()) {

    val viewModel: SelectionViewModel = hiltViewModel<SelectionViewModel>()

    Scaffold { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = AppScreens.SplashScreen.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(NavigationScreens.SplashScreen.route) {
                SplashScreen(navController)
            }
            composable(NavigationScreens.MainScreen.route) {
                MainScreen(navController)
            }
            composable(NavigationScreens.CameraScreen.route) {
                CameraScreen(navController)
            }
            composable(NavigationScreens.LocationScreen.route) {
                LocationScreen(navController)
            }
            composable(NavigationScreens.SelectionScreen.route) {
                SelectionScreen(navController, context, viewModel)
            }
            composable(NavigationScreens.QuestionScreen.route) {
                QuestionsScreen(navController, context, viewModel)
            }
            composable(NavigationScreens.ResultScreen.route) {
                ResultScreen(context, navHostController = navController, viewModel)
            }
//            composable(NavigationScreens.ResultScreen.route, listOf(navArgument(name = "score") {
//                type = NavType.StringType
//            }))
//            {
//                val result = it.arguments?.getString("score") ?: ""
//                Log.d("ResultScreenLogM", "ResultScreen: $result")
//                ResultScreen(context, navHostController = navController, result)
//            }
        }
    }
}