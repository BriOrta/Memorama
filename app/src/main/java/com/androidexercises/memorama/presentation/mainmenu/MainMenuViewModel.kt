package com.androidexercises.memorama.presentation.mainmenu

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainMenuViewModel() : ViewModel(){
    private val _state = MutableStateFlow<MainMenuState>(MainMenuState.IdleState)
    val state = _state.asStateFlow()

    fun startGame(game: Game){
        _state.update {
            MainMenuState.GameState(game)
        }
    }

    fun mainMenu(){
        _state.update {
            MainMenuState.IdleState
        }
    }
}