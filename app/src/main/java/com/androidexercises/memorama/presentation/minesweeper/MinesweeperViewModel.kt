package com.androidexercises.memorama.presentation.minesweeper

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MinesweeperViewModel() : ViewModel() {
    private val _state = MutableStateFlow<MinesweeperState>(
        MinesweeperState.MinesweeperGame(
            board = Board.generateBoard()
        )
    )

    fun reset(){
        _state.update {
            MinesweeperState.MinesweeperGame(
                board = Board.generateBoard()
            )
        }
    }

    fun gameOver(){
        _state.update {
            MinesweeperState.MinesweeperGameOver
        }
    }

    fun flagCell(rowIndex:Int, columnIndex:Int){
        val currentState = state.value
        if(currentState !is MinesweeperState.MinesweeperGame) return

        val newGrid = currentState.board.grid.map { it.clone() }
        val cell = newGrid[rowIndex][columnIndex]
        newGrid[rowIndex][columnIndex] = cell.copy(isFlagged = !cell.isFlagged)

        _state.update {
            currentState.copy(Board(newGrid))
        }
    }

    fun changeColor(rowIndex:Int, columnIndex:Int){
        val currentState = state.value
        if(currentState !is MinesweeperState.MinesweeperGame) return

        val newGrid = currentState.board.grid.map { it.clone() }
        val cell = newGrid[rowIndex][columnIndex]
        newGrid[rowIndex][columnIndex] = cell.copy(isClicked = true)

        _state.update {
            currentState.copy(Board(newGrid))
        }
    }

    val state = _state.asStateFlow()

    fun findWord(board: Array<CharArray>, word: String): Boolean {
        val wordArray = word.toCharArray()
        val wordSize = wordArray.size
        var foundWord = false

        //  Check if there's a word
        if (wordSize > 0) {
            var letterIndex = 0
            val wordLastIndex = wordArray.lastIndex
            var letter = wordArray[letterIndex]

            for (rowIndex in 0..board.lastIndex) {
                val column = board[rowIndex]
                for (columnIndex in 0..column.lastIndex) {
                    val cell = column[columnIndex]
                    if (cell == letter) {
                        // Check adjacent cells for the next letter
                        letterIndex++

                        // If there are more letters in the word
                        if (letterIndex <= wordLastIndex) {
                            // Get next letter
                            letter = wordArray[letterIndex]
                            continue
                        } else {
                            // No more letters
                            foundWord = true
                            break
                        }
                    } else {
                        // Move to the next cell and start again
                        // If in [rowIndex, columnIndex]

                        // [rowIndex, columnIndex+1]
                        // [rowIndex, columnIndex-1]
                        // [rowIndex+1, columnIndex]
                        // [rowIndex-1, columnIndex]
                        continue
                    }
                }
            }
        }

        return foundWord
    }

    fun binarySearch(nums: IntArray, target: Int): Int {
        // Check size of nums
        var startIndex = 0 // 0 [-1,0,3,5,9,12] ---> Search: 67
        var end = nums.size - 1 // 1

        while (startIndex <= end) {
            val middle = (startIndex + end) / 2
            val element = nums[middle]
            when {
                element == target -> return middle // Found number
                element < target -> startIndex = middle + 1
                else -> end = middle - 1
            }
        }
        return -1
    }

}