package com.androidexercises.memorama.presentation.minesweeper

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import java.util.LinkedList

const val NUMBER_OF_ROWS = 20
const val NUMBER_OF_COLUMNS = 10
const val NUMBER_OF_BOMBS = 10
@Composable
fun MinesweeperGameScreen(
    showIcon: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        // Create each row
        repeat(NUMBER_OF_ROWS) {
            // Each Row fills the available width and takes an equal portion of the height.
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f) // Each row gets an equal share of the vertical space
            ) {
                // Create each cell within the row
                repeat(NUMBER_OF_COLUMNS) {
                    // Each Box (cell) takes an equal portion of the horizontal space.
                    Box(modifier = Modifier.weight(1f)) {
                        MinesweeperCell {
                            showIcon()
                        }
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

fun generateBombs(){
    val rows = arrayOf(arrayOf(MinesweeperCell()))

    val listOfBombs = buildList {
        repeat(NUMBER_OF_BOMBS){
            add(MinesweeperCell(isBomb = true))
        }
        repeat(NUMBER_OF_COLUMNS*NUMBER_OF_ROWS - NUMBER_OF_BOMBS){
            add(MinesweeperCell(isBomb = false))
        }
    }.shuffled()
}

@Composable
fun MinesweeperCell(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(1.dp)
            .background(Color.White)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        /*Icon(
            imageVector = Icons.Filled.Close,
            contentDescription = "Bomb",
        )*/
    }
}