package com.androidexercises.memorama.presentation.theme

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidexercises.memorama.domain.memorama.GameCard
import com.androidexercises.memorama.domain.memorama.icons
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
    var gameGridSize:Int = 1

    fun startGame(gridSize: Int) {
        gameGridSize = gridSize
        val cards = createCardDeck(gridSize)
        _state.value = MemoramaScreenState.Game(gridSize, cards)
    }

    fun goBackToMainMenu(){
        _state.value = MemoramaScreenState.MainMenu
    }

    fun playAgain(){
        startGame(gameGridSize)
    }

    fun checkScore(deck:List<GameCard>){
        // Check if all cards have been checked and modify the game state
        val allChecked = deck.all { card -> card.isChecked }

        if(allChecked) {
            viewModelScope.launch {
                delay(600)
                _state.update {
                    MemoramaScreenState.GameOver
                }
            }
        }
    }

    // Non-idiomatic Kotlin way to modify the properties of the card elements inside
    // of the card deck list. Kotlin optimizes attempts of copying an object (list in this case)
    // as all of the elements are the same (but with a tiny modification, i.e. hasFlipped property)
    // Which doesn't trigger Compose as there are no memory reference changes. -- That's why the
    // deepCopyList() method forces the creation of a new object/reference to make the
    // corresponding modifications on the desired card object.
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
                    delay(800)
                    // Modify the flipped property of both cards and reset
                    firstCard.hasFlipped = false
                    secondCard.hasFlipped = false
                }
                lastFlippedIndex = null
            }

            // Or _state.value = currentState.copy(cards = updatedCards)
            _state.update {
                currentState.copy(cards = deepCopyList(deck))
            }
            isWorking = false
        }

        // Check if the user has won
        checkScore(deck)
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
