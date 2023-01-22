package com.gdsc_uoe.lostnfound.screens.postScreen

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.gdsc_uoe.lostnfound.components.InputCaption
import com.gdsc_uoe.lostnfound.components.InputField
import com.gdsc_uoe.lostnfound.components.LnFAppBar

@Composable
fun PostScreen(navController: NavController, scrollableState: ScrollState = rememberScrollState()) {
    Scaffold(
        topBar = {
            LnFAppBar(
                "Add Item",
                elevation = 0.dp,
                backgroundColor = Color.Transparent,
                navController = navController)
        }
    ) {

        Surface(modifier = Modifier
            .fillMaxSize()) {

        val name = rememberSaveable { mutableStateOf("") }
        val image = rememberSaveable { mutableStateOf("") }
        val description = rememberSaveable { mutableStateOf("") }
        val location = rememberSaveable { mutableStateOf("") }
        val keywords = rememberSaveable { mutableStateOf("") }
        val contact = rememberSaveable { mutableStateOf("") }

        Column(verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollableState)) {

            InputCaption(title = "Name")
            InputField(valueState = name, labelId = "Enter Name", enabled = true)

            InputCaption(title = "Image")
            InputField(valueState = image, labelId = "No file selected", enabled = true)

            InputCaption(title = "Description")
            InputField(valueState = description, labelId = "Enter Description",
                enabled = true, isSingleLine = false, maxLines = 5, modifier = Modifier.height(120.dp))

            InputCaption(title = "Location Found")
            InputField(valueState = location, labelId = "Enter Location Found", enabled = true)

            InputCaption(title = "Keywords")
            InputField(valueState = keywords, labelId = "Enter Keywords on Item", enabled = true)

            InputCaption(title = "Contact")
            InputField(valueState = contact, labelId = "Enter Your Contact", enabled = true)
            
            Button(onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp)
                    .padding(start = 15.dp, end = 15.dp, top = 4.dp)) {
                Text(text = "SAVE")
            }
        }
        }


    }
}

//@Composable
//fun NameInput(
//    title : String,
//
//)

