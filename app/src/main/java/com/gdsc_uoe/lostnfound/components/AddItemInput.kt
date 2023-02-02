package com.gdsc_uoe.lostnfound.components

import android.graphics.Color
import android.icu.text.CaseMap.Title
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.gdsc_uoe.lostnfound.navigation.LostnFoundScreens
import com.gdsc_uoe.lostnfound.ui.theme.PrimaryMain

@Composable
fun InputCaption(
    title: String,
    modifier: Modifier = Modifier
) {
    Text(text = title,
        modifier = modifier.padding(start = 17.dp, bottom = 5.dp),
        textAlign = TextAlign.Start,
        style = MaterialTheme.typography.body1,
        color = MaterialTheme.colors.onBackground)
}

@Composable
fun InputField(
    modifier: Modifier = Modifier,
    valueState : MutableState<String>,
    navController: NavController,
    labelId : String,
    enabled : Boolean,
    isSingleLine : Boolean = true,
    maxLines : Int = 1,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction : ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default
) {

    if(labelId == "No file selected") {
        // For image input
        OutlinedTextField(
            value = valueState.value,
            onValueChange = {valueState.value = it },
            placeholder = { Text(text = labelId) },
            singleLine = isSingleLine,
            maxLines = maxLines,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                cursorColor = PrimaryMain,
                focusedBorderColor = PrimaryMain
            ),
            textStyle = TextStyle(
                color = MaterialTheme.colors.onBackground,
                fontSize = 18.sp
            ),
            leadingIcon = {
                    Row(horizontalArrangement = Arrangement.Start){
                        Spacer(modifier = Modifier.width(10.dp))
                        Button(onClick = { navController.navigate(LostnFoundScreens.CameraScreen.name)},
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = PrimaryMain,
                                contentColor = MaterialTheme.colors.onPrimary
                            )) {
                            Text(text = "Choose File")
                        }}
            },
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 15.dp, end = 15.dp, bottom = 10.dp),
            enabled = false,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
            keyboardActions = onAction
        )
    } else {
        OutlinedTextField(
            value = valueState.value,
            onValueChange = {valueState.value = it },
            placeholder = { Text(text = labelId) },
            singleLine = isSingleLine,
            maxLines = maxLines,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                cursorColor = PrimaryMain,
                focusedBorderColor = PrimaryMain
            ),
            textStyle = TextStyle(
                color = MaterialTheme.colors.onBackground,
                fontSize = 18.sp
            ),
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 15.dp, end = 15.dp, bottom = 10.dp),
            enabled = enabled,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
            keyboardActions = onAction
        )
    }



}