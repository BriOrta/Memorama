package com.androidexercises.memorama.presentation.minesweeper

import kotlin.compareTo
import kotlin.ranges.rangeTo

sealed class MinesweeperState() {
    data class MinesweeperGame(val board: Board) : MinesweeperState()
    data object MinesweeperGameOver: MinesweeperState()
}

data class Board(val grid:List<Array<MinesweeperCell>>) {
    // X = Row, Y = Column
    // [x, y] ---> [0,1]
    companion object {
        fun generateBoard() : Board {
            val grid = List(NUMBER_OF_ROWS) { Array(NUMBER_OF_COLUMNS) { MinesweeperCell() } }
            val listOfCells = buildList {
                repeat(NUMBER_OF_BOMBS) {
                    add(MinesweeperCell(isBomb = true))
                }
                repeat(NUMBER_OF_COLUMNS * NUMBER_OF_ROWS - NUMBER_OF_BOMBS) {
                    add(MinesweeperCell(isBomb = false))
                }
            }.shuffled()

            var counter = 0

            for (i in 0..NUMBER_OF_ROWS - 1) {
                for (j in 0..NUMBER_OF_COLUMNS - 1) {
                    val cell = listOfCells[counter]
                    grid[i][j] = cell
                    counter++
                }
            }

            val newGrid = countBombs(grid)

            return Board(newGrid)
        }

        fun countBombs(grid:List<Array<MinesweeperCell>>) : List<Array<MinesweeperCell>>{
            val newGrid = grid.map { it.clone() }

            for(i in 0..NUMBER_OF_ROWS-1){
                for(j in 0..NUMBER_OF_COLUMNS-1){
                    if (grid[i][j].isBomb){
                        val startX = if(i - 1 < 0 ) i else i -1
                        val startY = if(j - 1 < 0 ) 0 else j -1
                        val endX = if(i + 1 >= NUMBER_OF_ROWS ) i else i +1
                        val endY = if(j + 1 >= NUMBER_OF_COLUMNS ) j else j + 1

                        for (x in startX..endX){
                            for(y in startY..endY) {
                                val cell = newGrid[x][y]
                                if (!cell.isBomb) {
                                    newGrid[x][y] =
                                        cell.copy(surroundingBombs = cell.surroundingBombs + 1)
                                }
                            }
                        }
                    }
                }
            }

            return newGrid
        }
    }
}