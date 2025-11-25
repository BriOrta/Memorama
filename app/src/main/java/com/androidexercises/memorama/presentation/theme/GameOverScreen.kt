package com.androidexercises.memorama.presentation.theme

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.DialogProperties

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameOverScreen(
    onDismiss: () -> Unit
){
    BasicAlertDialog(
        onDismissRequest = {onDismiss()},
        //modifier = Modifier.fillMaxSize(),
        properties = DialogProperties(dismissOnClickOutside = false, dismissOnBackPress = false)
    ){
        Text("You won!")
    }
}