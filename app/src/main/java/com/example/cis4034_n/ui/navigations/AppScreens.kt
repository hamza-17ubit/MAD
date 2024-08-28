package com.example.cis4034_n.ui.navigations

enum class AppScreens {
    SplashScreen,
    MainScreen,
    CameraScreen,
    LocationScreen,
    SelectionScreen,
    QuestionScreen,
    ResultScreen,
}

sealed class NavigationScreens(val route: String) {
    data object SplashScreen: NavigationScreens(AppScreens.SplashScreen.name)
    data object MainScreen: NavigationScreens(AppScreens.MainScreen.name)
    data object CameraScreen: NavigationScreens(AppScreens.CameraScreen.name)
    data object LocationScreen: NavigationScreens(AppScreens.LocationScreen.name)
    data object SelectionScreen: NavigationScreens(AppScreens.SelectionScreen.name)
    data object QuestionScreen: NavigationScreens(AppScreens.QuestionScreen.name)
    data object ResultScreen: NavigationScreens(AppScreens.ResultScreen.name)
}