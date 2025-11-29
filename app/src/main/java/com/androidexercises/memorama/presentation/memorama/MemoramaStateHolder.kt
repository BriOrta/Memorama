package com.androidexercises.memorama.presentation.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.androidexercises.memorama.domain.memorama.GameCard

// Annotation that tells Kotlin that only when the memory reference changes, it has to recompose
@Stable
class MemoramaStateHolder {
    var gridSize by mutableIntStateOf(1)
        private set

    fun onGridSizeChange(newGridSize: Int){
        gridSize = newGridSize
    }

    fun updateCard(card: GameCard){
    }
}

@Composable
fun rememberMemoramaStateHolder(): MemoramaStateHolder {
    // Ensures the class maintains as it is even if there are changes in configuration
    return remember {
        MemoramaStateHolder()
    }
}