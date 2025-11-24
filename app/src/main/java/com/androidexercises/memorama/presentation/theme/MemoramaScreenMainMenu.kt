package com.androidexercises.memorama.presentation.theme

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

val gridSizes = listOf(4, 6, 8)
val difficultyLevels = listOf("Fácil", "Normal", "Difícil")

@Composable
fun MemoramaScreenMainMenu(
    startGame: (Int) -> Unit
){
    LazyColumn {
        items(gridSizes.size){ index ->
            Button(onClick = { startGame(gridSizes[index]) }) {
                Text(difficultyLevels[index])
            }
        }
    }
}