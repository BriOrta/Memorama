package com.androidexercises.memorama.presentation.theme

import com.androidexercises.memorama.domain.GameCard

sealed class MemoramaScreenState {
    data object MainMenu : MemoramaScreenState()
    data class Game(val gridSize: Int, var cards: List<GameCard>) : MemoramaScreenState()
    data object GameOver : MemoramaScreenState()
}
