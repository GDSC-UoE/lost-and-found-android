package com.gdsc_uoe.lostnfound.screens.cameraScreen

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import coil.compose.rememberImagePainter

@Composable
fun CameraScreen(navController: NavController) {
    Surface(modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background) {

    }
}

@Composable
fun CameraContent(modifier: Modifier = Modifier) {
    var imageUri = remember { mutableStateOf(EMPTY_IMAGE_URI)}
    if(imageUri != EMPTY_IMAGE_URI) {
        Box(modifier = modifier) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = rememberImagePainter(imageUri),
                contentDescription = "Image")
            Button(
                modifier = Modifier.align(Alignment.BottomCenter),
                onClick = { imageUri == EMPTY_IMAGE_URI }) {
                Text(text = "Remove Image")
            }
        }
    } else {
        Box(modifier = modifier) {
            CameraCapture(

            )
        }
    }


}

val EMPTY_IMAGE_URI: Uri = Uri.parse("file://dev/null")

@Composable
fun CameraCapture () {

}