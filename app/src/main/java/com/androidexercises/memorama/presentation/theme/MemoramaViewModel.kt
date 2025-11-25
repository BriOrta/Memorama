package com.androidexercises.memorama.presentation.theme

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidexercises.memorama.domain.GameCard
import com.androidexercises.memorama.domain.icons
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MemoramaViewModel : ViewModel() {
    private val _state = MutableStateFlow<MemoramaScreenState>(MemoramaScreenState.MainMenu)
    val state = _state.asStateFlow()
    var lastFlippedIndex: Int? = null
    var isWorking = false

    fun startGame(gridSize: Int) {
        val cards = createCardDeck(gridSize)
        _state.value = MemoramaScreenState.Game(gridSize, cards)
    }

    fun updateCard(cardIndex: Int) {
        if (state.value !is MemoramaScreenState.Game) return
        if (cardIndex == lastFlippedIndex || isWorking) return

        val currentState = state.value as MemoramaScreenState.Game

        // val updatedCards = myMapIndexed(currentState.cards, cardIndex)
        val deck = currentState.cards.map { it.copy() }
        val firstCard = deck[cardIndex]
        if(firstCard.isChecked) return

        firstCard.hasFlipped = true

        _state.value = currentState.copy(cards = deepCopyList(deck))

        // If there's no previous card flipped
        viewModelScope.launch {
            if (lastFlippedIndex == null) {
                lastFlippedIndex = cardIndex
                return@launch
            } else {
                isWorking = true
                val secondCard = deck[lastFlippedIndex!!]
                // If the cards have the same icon
                if (firstCard.icon == secondCard.icon) {
                    // Keep cards flipped & reset
                    firstCard.isChecked = true
                    secondCard.isChecked = true
                } else {
                    delay(1000)
                    // Modify the flipped property of both cards and reset
                    firstCard.hasFlipped = false
                    secondCard.hasFlipped = false
                }
                lastFlippedIndex = null
            }

            _state.update {
                currentState.copy(cards = deepCopyList(deck))
            }
            isWorking = false
        }
        // Or _state.value = currentState.copy(cards = updatedCards)
    }

    fun deepCopyList(list : List<GameCard>) : List<GameCard>{
        val newList = mutableListOf<GameCard>()
        for(i in 0..list.lastIndex){
            val card = list[i]
            newList.add(i, GameCard(card.hasFlipped,card.icon,card.isChecked))
        }
        return newList
    }

    // Even if we create a newDeck with a new memory reference, they are optimized and if their
    // values are the same, the old reference is re-used (doesn't matter if the properties of
    // each of the elements changed as their individual memory references were kept)
    fun myMapIndexed(deck: List<GameCard>, selectedCardIndex: Int): List<GameCard> {
        val newDeck = mutableListOf<GameCard>()
        for (index in 0..deck.lastIndex) {
            val card = deck[index]
            if (selectedCardIndex == index) {
                // Only possible if hasFlipped is mutable
                //card.hasFlipped = !card.hasFlipped
                println(card.hasFlipped)
            }
            newDeck.add(card)
        }

        // By adding this new element, the list is different than the original one
        // Therefore, the newDeck reference is returned and Compose reacts to the
        // memory reference change.
        val gameCard = GameCard(false, Icons.Filled.Menu)
        newDeck.add(gameCard)
        println(gameCard)
        return newDeck
    }

    private fun createCardDeck(gridSize: Int): List<GameCard> {
        val numberOfUniqueCards = gridSize * gridSize / 2
        val icons = icons.shuffled().take(numberOfUniqueCards)
        val cardDeck = mutableListOf<GameCard>()
        for (i in 0 until numberOfUniqueCards) {
            val card = GameCard(icon = icons[i])
            cardDeck.add(card)
            cardDeck.add(card)
        }
        return cardDeck.shuffled()
    }
}
