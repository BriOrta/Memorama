package com.androidexercises.memorama.presentation.minesweeper

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.androidexercises.memorama.presentation.minesweeper.MinesweeperState

@Composable
fun MinesweeperScreen(
    viewModel : MinesweeperViewModel = MinesweeperViewModel()
){
    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding).fillMaxSize()) {
            when (val currentState = state) {
                is MinesweeperState.MinesweeperGame -> MinesweeperGameScreen(
                    board = currentState.board,
                    showIcon = {viewModel.showIcon()}
                )
            }
        }
    }
}