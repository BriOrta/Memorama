package com.androidexercises.memorama.presentation.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun MemoramaScreen(
    viewModel: MemoramaViewModel = MemoramaViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding).fillMaxSize()) {
            when (val currentState = state) {
                is MemoramaScreenState.MainMenu -> MemoramaScreenMainMenu(startGame = viewModel::startGame)
                is MemoramaScreenState.Game -> MemoramaScreenGame(
                    gridSize = currentState.gridSize,
                    cards = currentState.cards,
                    onCardClick = { cardIndex -> viewModel.updateCard(cardIndex) }
                )
                is MemoramaScreenState.GameOver -> Text(text = "Game Over")
            }
        }
    }
}
