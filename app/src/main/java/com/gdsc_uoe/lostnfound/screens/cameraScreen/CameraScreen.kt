package com.gdsc_uoe.lostnfound.screens.cameraScreen


import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.gdsc_uoe.lostnfound.screens.cameraScreen.camera.*


@Composable
fun CameraScreen(navController: NavController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Camera(modifier = Modifier.fillMaxSize())
    }
}

var EMPTY_IMG_URI: Uri = Uri.parse("file://lostnfound/null")