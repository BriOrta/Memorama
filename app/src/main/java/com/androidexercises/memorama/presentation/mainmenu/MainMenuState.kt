package com.androidexercises.memorama.presentation.mainmenu

sealed class MainMenuState() {
    data object IdleState : MainMenuState()
    data class GameState(val game: Game) : MainMenuState()
}