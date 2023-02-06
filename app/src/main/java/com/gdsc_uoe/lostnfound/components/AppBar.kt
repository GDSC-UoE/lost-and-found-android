package com.gdsc_uoe.lostnfound.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.gdsc_uoe.lostnfound.R

@Composable
fun LnFAppBar(
    title : String,
    modifier: Modifier = Modifier,
    elevation : Dp = 0.dp,
    backgroundColor : Color,
    navController: NavController
) {
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        elevation = elevation,
        backgroundColor = backgroundColor,
        navigationIcon = {
            Box(modifier = modifier, contentAlignment = Alignment.Center) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(painter = painterResource(id = R.drawable.ic_back_arrow),
                        contentDescription = "Back Arrow")
                }
            }

        },
        title = {
            Text(
                text = title,
                modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp)
        },
        actions = { 
            IconButton(
            modifier = Modifier
                .padding(start = 16.dp),
            enabled = false,
            onClick = {} ) {}
        }
    )
    
}

