package com.androidexercises.memorama.presentation.mainmenu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun MainMenuScreen(
    startGame : (Game) -> Unit
) {
    Scaffold { innerPadding ->
        Column(Modifier.padding(innerPadding)) {
            Column(
                Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(onClick = {startGame(Game.Memorama)}) {
                    Text("Memorama")
                }
                Button(onClick = {startGame(Game.Minesweeper)}) {
                    Text("Minesweeper")
                }
                Button(onClick = {startGame(Game.Poker)}) {
                    Text("Poker")
                }
            }
        }
    }
}