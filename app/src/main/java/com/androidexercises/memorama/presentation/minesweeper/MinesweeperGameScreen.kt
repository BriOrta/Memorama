package com.androidexercises.memorama.presentation.minesweeper

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

const val NUMBER_OF_ROWS = 10
const val NUMBER_OF_COLUMNS = 5
const val NUMBER_OF_BOMBS = 10

@Composable
fun MinesweeperGameScreen(
    board: Board,
    showIcon: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        board.grid.map { row ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                row.map { cell ->
                    Box(modifier = Modifier.weight(1f)) {
                        MinesweeperCellIcon(cell)
                    }
                }
            }
        }
    }

        // Lazy Grids are scrollable layouts by default
        /*LazyVerticalGrid(
            columns = GridCells.Fixed(5),
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
        ) {
            items(60) {
                MinesweeperCell { showIcon() }
            }
        }*/
    }

    @Composable
    fun MinesweeperCellIcon(
        cell : MinesweeperCell
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(1.dp)
                .background(Color.White)
                .clickable {  },
            contentAlignment = Alignment.Center
        ) {
            if (cell.isBomb) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = "Bomb",
                )
            }
        }
    }