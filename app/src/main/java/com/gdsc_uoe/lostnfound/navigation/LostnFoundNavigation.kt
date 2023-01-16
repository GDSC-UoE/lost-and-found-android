package com.gdsc_uoe.lostnfound.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gdsc_uoe.lostnfound.screens.chatsScreen.ChatsScreen
import com.gdsc_uoe.lostnfound.screens.postScreen.PostScreen
import com.gdsc_uoe.lostnfound.screens.profileScreen.ProfileScreen
import com.gdsc_uoe.lostnfound.screens.searchScreen.SearchScreen
import com.gdsc_uoe.lostnfound.screens.splash.SplashScreen

@Composable
fun LostnFoundNavigation(navController: NavHostController) {

    NavHost(navController = navController,
        startDestination = LostnFoundScreens.SplashScreen.name) {
        composable(LostnFoundScreens.SplashScreen.name) {
            SplashScreen(navController = navController)
        }
        composable(LostnFoundScreens.SearchScreen.name) {
            SearchScreen(navController = navController)
        }
        composable(LostnFoundScreens.PostScreen.name) {
            PostScreen(navController = navController)
        }
        composable(LostnFoundScreens.ChatsScreen.name) {
            ChatsScreen(navController = navController)
        }
        composable(LostnFoundScreens.ProfileScreen.name) {
            ProfileScreen(navController = navController)
        }
    }


}