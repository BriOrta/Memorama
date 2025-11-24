package com.androidexercises.memorama.presentation.theme

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.androidexercises.memorama.domain.GameCard

@Composable
fun MemoramaScreenGame(
    gridSize: Int,
    cards: List<GameCard>,
    onCardClick: (Int) -> Unit
) {
    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        columns = GridCells.Fixed(gridSize),
        verticalArrangement = Arrangement.spacedBy(5.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        itemsIndexed(cards) { index, card ->
            val flippedContainerColor = if (card.hasFlipped) Color.Green else Color.Black
            Card(
                modifier = Modifier
                    .aspectRatio(1f)
                    .clickable(onClick = { onCardClick(index) }),
                colors = CardColors(
                    contentColor = Color.Black,
                    containerColor = flippedContainerColor,
                    disabledContainerColor = Color.Black,
                    disabledContentColor = Color.Black
                )
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        card.icon,
                        contentDescription = card.icon.name
                    )
                }
            }
        }
    }
}
