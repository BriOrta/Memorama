package com.androidexercises.memorama.presentation.theme

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.androidexercises.memorama.domain.GameCard
import java.nio.file.WatchEvent

@Composable
fun MemoramaScreenGame(
    gridSize: Int,
    cards: List<GameCard>,
    onCardClick: (Int) -> Unit,
    onClose: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize().padding(5.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(gridSize),
            verticalArrangement = Arrangement.spacedBy(5.dp),
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            itemsIndexed(cards) { index, card ->
                val containerColor = when {
                    card.isChecked -> Color(0xFFFFC754)
                    card.hasFlipped -> Color.DarkGray
                    else -> Color.LightGray
                }
                val contentColor = when {
                    card.isChecked -> Color.Black
                    card.hasFlipped -> Color.LightGray
                    else -> Color.LightGray
                }

                Card(
                    modifier = Modifier
                        .aspectRatio(1f)
                        .clickable(onClick = { onCardClick(index) }),
                    colors = CardColors(
                        contentColor = contentColor,
                        containerColor = containerColor,
                        disabledContainerColor = Color.LightGray,
                        disabledContentColor = Color.LightGray
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
        Button(onClick = {onClose()}, modifier = Modifier.padding(top = 25.dp)) {
            Text("Main Menu")
        }
    }
}
