package com.gdsc_uoe.lostnfound.screens.cameraScreen.gallery

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.gdsc_uoe.lostnfound.screens.cameraScreen.camera.EMPTY_IMAGE_URI
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
            onImageUri(uri ?: EMPTY_IMAGE_URI)
        })

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
                Column(modifier) {
                    Text(text = "Please allow access to gallery")
                    Spacer(modifier = Modifier.height(8.dp))
                    Row {
                       Button(
                           modifier= Modifier.padding(4.dp),
                           onClick = {
                               context.startActivity(
                                   Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                                       data = Uri.fromParts("package", context.packageName, null)
                                   }
                               )
                           }) {
                           Text(text = "Open Settings")
                       }
                        // Going back if permission is not granted
                        Button(
                            modifier = Modifier.padding(4.dp),
                            onClick = {
                                onImageUri(EMPTY_IMAGE_URI)
                            }) {
                            Text(text = "Use Camera")
                        }
                    }
                }
            }
        ) {
            LaunchGallery()
        }
    } else {
        LaunchGallery()
    }
}