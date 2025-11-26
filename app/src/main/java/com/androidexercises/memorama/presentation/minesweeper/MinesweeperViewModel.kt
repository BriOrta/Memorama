package com.androidexercises.memorama.presentation.minesweeper

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MinesweeperViewModel () : ViewModel(){
    private val _state = MutableStateFlow<MinesweeperState>(MinesweeperState.MinesweeperGame)
    val state = _state.asStateFlow()

    fun startGame(){

    }

    fun showIcon(){

    }
}