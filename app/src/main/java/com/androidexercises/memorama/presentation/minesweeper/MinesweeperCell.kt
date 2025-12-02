package com.androidexercises.memorama.presentation.minesweeper

data class MinesweeperCell(
    val isBomb:Boolean = false,
    val isClicked:Boolean = false,
    var isFlagged:Boolean = false
)
