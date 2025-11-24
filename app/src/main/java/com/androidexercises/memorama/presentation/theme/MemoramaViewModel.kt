package com.androidexercises.memorama.presentation.theme

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel
import com.androidexercises.memorama.domain.GameCard
import com.androidexercises.memorama.domain.icons
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MemoramaViewModel : ViewModel() {
    private val _state = MutableStateFlow<MemoramaScreenState>(MemoramaScreenState.MainMenu)
    val state = _state.asStateFlow()
    var firstFlippedCardIndex : Int? = null

    fun startGame(gridSize: Int) {
        val cards = createCardDeck(gridSize)
        _state.value = MemoramaScreenState.Game(gridSize, cards)
    }

    fun updateCard(cardIndex: Int) {
        if(state.value !is MemoramaScreenState.Game) return
        if(cardIndex == firstFlippedCardIndex) return

        val currentState = state.value as MemoramaScreenState.Game
        // Or _state.value = currentState.copy(cards = updatedCards)
        _state.update {
            //val updatedCards = myMapIndexed(currentState.cards, cardIndex)
            val updatedCards = currentState.cards.mapIndexed { index, card ->
                if (index == cardIndex) {
                    card.copy(hasFlipped = !card.hasFlipped)
                    /*card.hasFlipped = !card.hasFlipped
                    card*/
                } else {
                    card
                }
            }
            currentState.copy(cards = updatedCards)

            // If there's a previous card flipped
            /*if(firstFlippedCardIndex != null){
                // Loop through the list of cards and compare the icon of the previous
                // flipped card with the card in deck[index] icon
                if(currentState.cards.){

                }
            }*/
        }

        /*

        val updatedCards2 = currentState.cards.mapIndexed { index, card ->
            if (index == cardIndex) {
                card.copy(hasFlipped = !card.hasFlipped)
            } else {
                card
            }
        }*/
    }

    // Even if we create a newDeck with a new memory reference, they are optimized and if their
    // values are the same, the old reference is re-used (doesn't matter if the properties of
    // each of the elements changed as their individual memory references were kept)
    fun myMapIndexed(deck: List<GameCard>, selectedCardIndex : Int) : List<GameCard>{
        val newDeck = mutableListOf<GameCard>()
        for(index in 0..deck.lastIndex){
            val card = deck[index]
            if(selectedCardIndex == index){
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
            val card = GameCard(false, icons[i])
            cardDeck.add(card)
            cardDeck.add(card)
        }
        return cardDeck.shuffled()
    }
}
