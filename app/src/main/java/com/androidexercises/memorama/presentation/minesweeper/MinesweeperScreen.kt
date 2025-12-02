package com.androidexercises.memorama.presentation.minesweeper

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun MinesweeperScreen(
    viewModel: MinesweeperViewModel = MinesweeperViewModel(),
    goToMainMenu: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold { innerPadding ->
        Column(modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()) {
            when (val currentState = state) {
                is MinesweeperState.MinesweeperGame -> MinesweeperGameScreen(
                    board = currentState.board,
                    reset = { viewModel.gameOver() },
                    flagCell = {rowIndex, columnIndex -> viewModel.flagCell(rowIndex,columnIndex)},
                    changeColor = {rowIndex, columnIndex -> viewModel.changeColor(rowIndex,columnIndex)}
                )

                is MinesweeperState.MinesweeperGameOver -> AlertDialog(
                    onDismissRequest = { viewModel.reset() },
                    confirmButton = {
                        TextButton({ goToMainMenu() }) {
                            Text("Main Menu")
                        }
                    },
                    dismissButton = {
                        TextButton({ viewModel.reset() }) {
                            Text("Play Again")
                        }
                    },
                    title = { Text("You lost") }
                )
            }
        }
    }
}