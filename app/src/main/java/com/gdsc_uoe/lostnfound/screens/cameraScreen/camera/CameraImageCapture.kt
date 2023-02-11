package com.gdsc_uoe.lostnfound.screens.cameraScreen.camera

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.util.Log
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.core.UseCase
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.gdsc_uoe.lostnfound.R
import com.gdsc_uoe.lostnfound.ui.theme.PrimaryMain
import com.gdsc_uoe.lostnfound.util.Permission
import kotlinx.coroutines.launch
import java.io.File

@Composable
fun CameraImageCapture(
    modifier: Modifier = Modifier,
    onImageFile: (File) -> Unit = { },
    onGalleryClick : (Boolean) -> Unit = { }
) {
    val cameraSelector : MutableState<CameraSelector> = remember {
        mutableStateOf(CameraSelector.DEFAULT_BACK_CAMERA)
    }
    val galleryStates = remember {
        mutableStateOf(false)
    }

    val context = LocalContext.current
    Permission(
        permission = Manifest.permission.CAMERA,
        rationale = "Please allow access to your camera.",
        permissionNotAvailableContent = {
            AlertDialog(
                modifier = Modifier.padding(start = 20.dp, end = 20.dp),
                onDismissRequest = { /* Don't */ },
                title = {
                    Text(text = "Permission request")
                },
                text = {
                    Text("Go to settings to allow permission")
                },
                confirmButton = {
                    Button(onClick = {
                        context.startActivity(
                            Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                                data = Uri.fromParts("package", context.packageName, null)
                            }
                        )
                    },
                        colors = ButtonDefaults.buttonColors(PrimaryMain)) {
                        Text("Open Settings", color = Color.White)
                    }
                }
            )
        }
    ) {
        Box(modifier = modifier) {
            val lifecycleOwner = LocalLifecycleOwner.current
            val coroutineScope = rememberCoroutineScope()
            var previewUseCase by remember { mutableStateOf<UseCase>(Preview.Builder().build()) }
            val imageCaptureUseCase by remember {
                mutableStateOf(
                    ImageCapture.Builder()
                        .setCaptureMode(ImageCapture.CAPTURE_MODE_MAXIMIZE_QUALITY)
                        .build()
                )

            }
            Box {
                CameraCapturePreview(
                    modifier = Modifier.fillMaxSize(),
                    onUseCase = {
                        previewUseCase = it
                    }
                )
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(start = 15.dp, end = 15.dp, bottom = 50.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painterResource(id = R.drawable.ic_gallery),
                        contentDescription = "Gallery",
                        modifier = Modifier
                            .clip(RoundedCornerShape(15.dp))
                            .background(Color.Black.copy(0.4f))
                            .padding(5.dp)
                            .size(45.dp)
                            .clickable {
                                galleryStates.value = true
                                onGalleryClick(galleryStates.value)
                            },
                        colorFilter = ColorFilter.tint(Color.White)
                    )

                    Spacer(modifier = Modifier.width(40.dp))

                    CapturePictureButton(
                        modifier = Modifier
                            .size(100.dp)
                            .padding(16.dp),
                        onClick = {
                            coroutineScope.launch {
                                imageCaptureUseCase.takePicture(context.executor).let(onImageFile)
                            }
                        }
                    )

                    Spacer(modifier = Modifier.width(40.dp))

                    // Camera Switching
                    Image(
                        painterResource(id = R.drawable.ic_flipcamera),
                        contentDescription = "Switch Camera",
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(Color.Black.copy(0.4f))
                            .padding(5.dp)
                            .size(45.dp)
                            .clickable {
                                cameraSelector.value =
                                    if (cameraSelector.value == CameraSelector.DEFAULT_BACK_CAMERA) CameraSelector.DEFAULT_FRONT_CAMERA
                                    else CameraSelector.DEFAULT_BACK_CAMERA

                                lifecycleOwner.lifecycleScope.launch {
                                    val cameraProvider = context.getCameraProvider()
                                    cameraProvider.unbindAll()
                                    cameraProvider.bindToLifecycle(
                                        lifecycleOwner,
                                        cameraSelector.value,
                                        previewUseCase,
                                        imageCaptureUseCase
                                    )
                                }

                            },
                        colorFilter = ColorFilter.tint(Color.White)
                    )
                }
            }
            LaunchedEffect(previewUseCase) {
                val cameraProvider = context.getCameraProvider()
                try {
                    // Must unbind the use-cases before rebinding them.
                    cameraProvider.unbindAll()
                    cameraProvider.bindToLifecycle(
                        lifecycleOwner, cameraSelector.value, previewUseCase, imageCaptureUseCase
                    )
                } catch (ex: Exception) {
                    Log.e("CameraCapture", "Failed to bind camera use cases", ex)
                }
            }
        }
    }
}
