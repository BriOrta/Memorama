package com.androidexercises.memorama.presentation.minesweeper

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

            for (x in 0..NUMBER_OF_ROWS - 1) {
                for (y in 0..NUMBER_OF_COLUMNS - 1) {
                    grid[x][y] = listOfCells[counter]
                    counter++
                }
            }

            return Board(grid)
        }
    }
}