package com.androidexercises.memorama.presentation.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val gridSizes = listOf(2, 4, 6, 8)
val difficultyLevels = listOf("Tutorial", "Easy", "Normal", "Hard")

@Composable
fun MemoramaScreenMainMenu(
    startGame: (Int) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        //verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Memorama",
            Modifier.padding(bottom = 100.dp, top = 180.dp, start = 100.dp, end = 100.dp),
            style = TextStyle(color = Color.Black, fontSize = 40.sp, fontWeight = FontWeight.Bold)
        )
        Text("Choose your difficulty",
            Modifier.padding(bottom = 15.dp)
        )
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(gridSizes.size) { index ->
                Button(onClick = { startGame(gridSizes[index]) }) {
                    Text(difficultyLevels[index])
                }
            }
        }
    }
}