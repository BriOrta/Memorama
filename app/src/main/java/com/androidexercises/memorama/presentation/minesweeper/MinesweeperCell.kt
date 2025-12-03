package com.androidexercises.memorama.presentation.minesweeper

data class MinesweeperCell(
    val isBomb:Boolean = false,
    val isClicked:Boolean = false,
    val isFlagged:Boolean = false,
    val surroundingBombs:Int = 0
)
