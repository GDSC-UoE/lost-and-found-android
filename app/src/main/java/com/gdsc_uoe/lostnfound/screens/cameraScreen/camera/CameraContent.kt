package com.gdsc_uoe.lostnfound.screens.cameraScreen.camera

import android.net.Uri
import com.gdsc_uoe.lostnfound.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import coil.compose.rememberImagePainter
import com.gdsc_uoe.lostnfound.screens.cameraScreen.gallery.GallerySelect

@Composable
fun CameraContent(modifier: Modifier = Modifier) {
    var imageUri by remember { mutableStateOf(EMPTY_IMAGE_URI) }
    if(imageUri != EMPTY_IMAGE_URI) {
        Box(modifier = modifier) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = rememberImagePainter(imageUri),
                contentDescription = "Image")
            Button(
                modifier = Modifier.align(Alignment.BottomCenter),
                onClick = { imageUri = EMPTY_IMAGE_URI }) {
                Text(text = "Remove Image")
            }
        }
    } else {
        var showGallerySelect by remember { mutableStateOf(false) }
        if (showGallerySelect) {
            GallerySelect(
                modifier = modifier,
                onImageUri = { uri ->
                    showGallerySelect = false
                    imageUri = uri
                }
            )
        } else {
            Box(modifier = modifier) {
                CameraImageCapture(
                    modifier = modifier.fillMaxSize(),
                    onImageFile = { file ->
                        imageUri = file.toUri()
                    }
                )

                Button(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(10.dp),
                    colors = ButtonDefaults.buttonColors(Color.White.copy(alpha = 0.9f)),
                    onClick = {
                        showGallerySelect = true
                    }
                ) {
                    Icon(painterResource(id = R.drawable.ic_gallery),
                        contentDescription = "Gallery Button",
                        tint = MaterialTheme.colors.onBackground)
                }
            }
        }
    }
}

val EMPTY_IMAGE_URI: Uri = Uri.parse("file://lostnfound/null")