package com.gdsc_uoe.lostnfound.screens.cameraScreen

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.util.Log
import android.view.ViewGroup
import android.widget.ImageView.ScaleType
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCapture.CAPTURE_MODE_MAXIMIZE_QUALITY
import androidx.camera.core.Preview
import androidx.camera.core.UseCase
import androidx.camera.view.PreviewView
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.net.toUri
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.gdsc_uoe.lostnfound.R
import com.gdsc_uoe.lostnfound.screens.cameraScreen.camera.*
import com.gdsc_uoe.lostnfound.screens.cameraScreen.gallery.GallerySelect
import com.gdsc_uoe.lostnfound.ui.theme.PrimaryMain
import com.gdsc_uoe.lostnfound.util.Permission
import kotlinx.coroutines.launch
import java.io.File
import java.security.Permission
import java.util.jar.Manifest

@Composable
fun CameraScreen(navController: NavController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        //CameraContent(modifier = Modifier.fillMaxSize())

        Camera(modifier = Modifier.fillMaxSize())


    }
}

@androidx.compose.ui.tooling.preview.Preview
@Composable
fun Camera(modifier: Modifier = Modifier) {
    var imgUri by remember { mutableStateOf(EMPTY_IMG_URI) }
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
                        .clickable { imgUri = EMPTY_IMAGE_URI },
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
                }
            )
        }
    }
}

val EMPTY_IMG_URI: Uri = Uri.parse("file://lostnfound/null")


// Preview Screen

//Box(modifier = Modifier.fillMaxSize()) {
//    Text(text = "Lots of text")
//    Row(modifier = Modifier
//        .align(Alignment.BottomCenter)
//        .padding(start = 15.dp, end = 15.dp, bottom = 50.dp)) {
//        Image(
//            painterResource(id = R.drawable.ic_close),
//            contentDescription = "Close",
//            modifier = Modifier
//                .clip(RoundedCornerShape(15.dp))
//                .background(Color.Black.copy(0.4f))
//                .padding(5.dp)
//                .size(45.dp),
//            colorFilter = ColorFilter.tint(Color.White)
//        )
//        Spacer(modifier = Modifier.width(180.dp))
//        Image(
//            painterResource(id = R.drawable.ic_tick),
//            contentDescription = "Close",
//            modifier = Modifier
//                .clip(CircleShape)
//                .background(Color.Black.copy(0.4f))
//                .padding(5.dp)
//                .size(45.dp),
//            colorFilter = ColorFilter.tint(Color.White)
//        )
//    }
//}


// Camera Screen

//Box(modifier = Modifier.fillMaxSize()) {
//    Text(text = "Lots of text")
//    Row(modifier = Modifier
//        .align(Alignment.BottomCenter)
//        .padding(start = 15.dp, end = 15.dp, bottom = 50.dp)) {
//        Image(
//            painterResource(id = R.drawable.ic_gallery),
//            contentDescription = "Close",
//            modifier = Modifier
//                .clip(RoundedCornerShape(15.dp))
//                .background(Color.Black.copy(0.4f))
//                .padding(5.dp)
//                .size(45.dp),
//            colorFilter = ColorFilter.tint(Color.White)
//        )
//        Spacer(modifier = Modifier.width(90.dp))
//        Image(
//            painterResource(id = R.drawable.ic_close),
//            contentDescription = "Close",
//            modifier = Modifier
//                .clip(RoundedCornerShape(15.dp))
//                .background(Color.Black.copy(0.4f))
//                .padding(5.dp)
//                .size(45.dp),
//            colorFilter = ColorFilter.tint(Color.White)
//        )
//
//        Spacer(modifier = Modifier.width(90.dp))
//        Image(
//            painterResource(id = R.drawable.ic_flipcamera),
//            contentDescription = "Close",
//            modifier = Modifier
//                .clip(CircleShape)
//                .background(Color.Black.copy(0.4f))
//                .padding(5.dp)
//                .size(45.dp),
//            colorFilter = ColorFilter.tint(Color.White)
//        )
//    }
//}