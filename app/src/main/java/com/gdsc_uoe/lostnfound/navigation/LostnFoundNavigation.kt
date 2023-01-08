package com.gdsc_uoe.lostnfound.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gdsc_uoe.lostnfound.screens.splash.SplashScreen

@Composable
fun LostnFoundNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController,
        startDestination = LostnFoundScreens.SplashScreen.name) {
        composable(LostnFoundScreens.SplashScreen.name) {
            SplashScreen(navController = navController)
        }
    }



}