package com.gdsc_uoe.lostnfound.screens.cameraScreen.camera

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import coil.compose.rememberAsyncImagePainter
import com.gdsc_uoe.lostnfound.R
import com.gdsc_uoe.lostnfound.screens.cameraScreen.EMPTY_IMG_URI
import com.gdsc_uoe.lostnfound.screens.cameraScreen.gallery.GallerySelect

@androidx.compose.ui.tooling.preview.Preview
@Composable
fun Camera(modifier: Modifier = Modifier) {
    var imgUri by remember { mutableStateOf(EMPTY_IMG_URI) }
    var galleryValue by remember { mutableStateOf(false) }
    if (imgUri != EMPTY_IMG_URI) {
        Box(modifier = modifier) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = rememberAsyncImagePainter(imgUri),
                contentDescription = "image"
            )
            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(start = 15.dp, end = 15.dp, bottom = 50.dp)
            ) {
                Image(
                    painterResource(id = R.drawable.ic_close),
                    contentDescription = "Close",
                    modifier = Modifier
                        .clip(RoundedCornerShape(15.dp))
                        .background(Color.Black.copy(0.4f))
                        .padding(5.dp)
                        .size(45.dp)
                        .clickable { imgUri = EMPTY_IMG_URI },
                    colorFilter = ColorFilter.tint(Color.White)
                )
                Spacer(modifier = Modifier.width(180.dp))
                Image(
                    painterResource(id = R.drawable.ic_tick),
                    contentDescription = "Close",
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(Color.Black.copy(0.4f))
                        .padding(5.dp)
                        .size(45.dp),
                    colorFilter = ColorFilter.tint(Color.White)
                )
            }
        }

    } else {
        Box(modifier = modifier) {
            CameraImageCapture(
                modifier = modifier.fillMaxSize(),
                onImageFile = { file ->
                    imgUri = file.toUri()
                },
                onGalleryClick = {
                    galleryValue = it
                }
            )
        }
    }

    if (galleryValue) {
        GallerySelect(
            modifier = modifier,
            onImageUri = { uri ->
                imgUri = uri
            }
        )
    }
}