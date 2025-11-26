package com.androidexercises.memorama.presentation.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameOverScreen(
    onDismiss: () -> Unit,
    playAgain: () -> Unit
) {
    BasicAlertDialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(dismissOnClickOutside = false, dismissOnBackPress = false)
    ) {
        Surface(modifier = Modifier.background(Color(0xFF90EE90))) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.background(Color(0xFF90EE90)).padding(bottom = 15.dp, top = 15.dp)
            ) {
                Text("Congratulations! You won!", style = TextStyle(fontWeight = FontWeight.SemiBold),
                    modifier = Modifier.padding(bottom = 15.dp))
                Button(onClick = { onDismiss() }) {
                    Text("Main Menu")
                }
                Button(onClick = { playAgain() }) {
                    Text("Play Again")
                }
            }
        }
    }
}