package com.gdsc_uoe.lostnfound

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.gdsc_uoe.lostnfound.navigation.LostnFoundNavigation
import com.gdsc_uoe.lostnfound.ui.theme.LostnFoundTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LostnFoundApp()
        }
    }
}

@Composable
fun LostnFoundApp() {
    LostnFoundTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Column() {
                LostnFoundNavigation()
            }

        }
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LostnFoundTheme {
        LostnFoundApp()
    }
}