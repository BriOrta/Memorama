package com.androidexercises.memorama.presentation.mainmenu

sealed class Game(){
    data object Memorama : Game()
    data object Poker : Game()
    data object Minesweeper : Game()
}
