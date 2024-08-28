package com.example.cis4034_n.ui.screens

import android.content.Context
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcherOwner
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.navigation.NavHostController
import com.example.cis4034_n.ui.composables.TextFieldTitle
import com.example.cis4034_n.ui.composables.TopBarCompose
import com.example.cis4034_n.ui.navigations.AppScreens
import com.example.cis4034_n.ui.navigations.NavigationScreens
import com.example.cis4034_n.ui.viewModels.SelectionViewModel

@Composable
fun ResultScreen(context: Context, navHostController: NavHostController, viewModel: SelectionViewModel) {

    val currentOnBackPressed by rememberUpdatedState {
        navHostController.navigate(AppScreens.MainScreen.name) {
            popUpTo(NavigationScreens.MainScreen.route) { inclusive = true }
        }
    }
    DisposableEffect(context) {
        val callback = object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
               currentOnBackPressed()
            }
        }
        (context as? OnBackPressedDispatcherOwner)?.onBackPressedDispatcher?.addCallback(callback)

        onDispose { callback.remove() }
    }

    Column {
        TopBarCompose(title = "Results", showBackBtn = true,
            onBackClicked = { currentOnBackPressed() }, navHostController = navHostController)

        TextFieldTitle(viewModel.score.toString())
    }
    
}