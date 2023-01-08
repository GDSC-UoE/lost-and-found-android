package com.gdsc_uoe.lostnfound.screens.splash

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.gdsc_uoe.lostnfound.navigation.LostnFoundScreens
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {

    LaunchedEffect(key1 = true, block = {
        // Delay 1500 millisec before launching search screen
        delay(2000)
        navController.navigate(LostnFoundScreens.SearchScreen.name)
    })

    Surface(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier.padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {

            Text(text = "Splashscreen")

        }

    }

}