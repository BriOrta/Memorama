package com.androidexercises.memorama.presentation.minesweeper

sealed class MinesweeperState() {
    data object MinesweeperGame : MinesweeperState()
}
