package com.gdsc_uoe.lostnfound.screens.cameraScreen.gallery

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.gdsc_uoe.lostnfound.screens.cameraScreen.EMPTY_IMG_URI
import com.gdsc_uoe.lostnfound.ui.theme.PrimaryMain
import com.gdsc_uoe.lostnfound.util.Permission

@Composable
fun GallerySelect(
    modifier: Modifier = Modifier,
    onImageUri : (Uri) -> Unit = { }
) {
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri: Uri? ->
            onImageUri(uri ?: EMPTY_IMG_URI)
        })
    var openDialog by remember { mutableStateOf(true) }

    @Composable
    fun LaunchGallery() {
        SideEffect {
            launcher.launch("image/*")
        }
    }

    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        Permission(
            permission = Manifest.permission.ACCESS_MEDIA_LOCATION,
            rationale = "Please allow access to your Gallery",
            permissionNotAvailableContent = {
                if (openDialog) {
                    AlertDialog(
                        modifier = Modifier.padding(start = 20.dp, end = 20.dp),
                        onDismissRequest = { /* Don't */ },
                        title = {
                            Text(text = "Please allow gallery permission")
                        },
                        text = {
                            Text("Go to settings to allow permission")
                        },
                        confirmButton = {
                            Row {
                                Button(
                                    modifier= Modifier.padding(4.dp),
                                    colors = ButtonDefaults.buttonColors(PrimaryMain),
                                    onClick = {
                                        context.startActivity(
                                            Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                                                data = Uri.fromParts("package", context.packageName, null)
                                            }
                                        )
                                    }) {
                                    Text(text = "Open Settings", color = Color.White)
                                }
                                // Going back if permission is not granted
                                Button(
                                    modifier = Modifier.padding(4.dp),
                                    colors = ButtonDefaults.buttonColors(PrimaryMain),
                                    onClick = {
                                        onImageUri(EMPTY_IMG_URI)
                                        openDialog = false
                                    }) {
                                    Text(text = "Use Camera", color = Color.White)
                                }
                            }
                        } )
                }
            }
        ) {
            LaunchGallery()
        }
    } else {
        LaunchGallery()
    }
}