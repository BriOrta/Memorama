package com.androidexercises.memorama.presentation.minesweeper

import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
    reset: () -> Unit,
    changeColor: (Int, Int) -> Unit,
    flagCell: (Int, Int) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        board.grid.forEachIndexed { rowIndex, row ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                row.forEachIndexed { columnIndex, cell ->
                    Box(modifier = Modifier.weight(1f)) {
                        MinesweeperCellIcon(
                            cell,
                            onClick = {
                                if (!cell.isBomb) {
                                    changeColor(rowIndex, columnIndex)
                                } else {
                                    reset()
                                }
                            },
                            onLongClick = {
                                flagCell(rowIndex,columnIndex)
                            }
                        )
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
    cell: MinesweeperCell,
    onClick: () -> Unit,
    onLongClick: () -> Unit
) {
    val color = if (cell.isClicked) Color.Gray else (if(cell.isBomb) Color.Red else Color.White)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(1.dp)
            .background(color)
            .combinedClickable(
                onClick = { if(!cell.isFlagged) onClick() },
                onLongClick = { onLongClick() }
            ),
        contentAlignment = Alignment.Center
    ) {
        if (cell.isFlagged) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = "Flagged",
            )
        }
    }
}