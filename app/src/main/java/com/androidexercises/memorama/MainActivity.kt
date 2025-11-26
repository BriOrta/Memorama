package com.androidexercises.memorama

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.androidexercises.memorama.presentation.minesweeper.MinesweeperScreen
import com.androidexercises.memorama.presentation.theme.MemoramaScreen
import com.androidexercises.memorama.presentation.theme.MemoramaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MemoramaTheme {
                // MemoramaScreen()
                MinesweeperScreen()
            }
        }
    }
}
