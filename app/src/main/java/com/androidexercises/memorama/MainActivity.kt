package com.androidexercises.memorama

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.androidexercises.memorama.presentation.mainmenu.Game
import com.androidexercises.memorama.presentation.mainmenu.MainMenuScreen
import com.androidexercises.memorama.presentation.mainmenu.MainMenuState
import com.androidexercises.memorama.presentation.mainmenu.MainMenuViewModel
import com.androidexercises.memorama.presentation.memorama.MemoramaScreen
import com.androidexercises.memorama.presentation.memorama.MemoramaScreenMainMenu
import com.androidexercises.memorama.presentation.minesweeper.MinesweeperScreen
import com.androidexercises.memorama.presentation.theme.MemoramaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MemoramaTheme {
                val viewModel = MainMenuViewModel()
                val state by viewModel.state.collectAsStateWithLifecycle()
                // MemoramaScreen()
                MinesweeperScreen()
                /*when(val currentState = state){
                    is MainMenuState.IdleState -> MainMenuScreen(viewModel::startGame)
                    is MainMenuState.GameState -> {
                        when(currentState.game){
                            Game.Minesweeper -> MinesweeperScreen()
                            Game.Memorama -> MemoramaScreen()
                            Game.Poker -> MemoramaScreen()
                        }
                    }
                }*/
            }
        }
    }
}
