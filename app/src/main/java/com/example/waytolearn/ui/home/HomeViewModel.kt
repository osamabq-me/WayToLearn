package com.example.waytolearn.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.waytolearn.Graph
import com.example.waytolearn.data.room.WordsWithSourceAndList
import com.example.waytolearn.data.room.models.Word
import com.example.waytolearn.ui.Category
import com.example.waytolearn.ui.repository.Repository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: Repository = Graph.repository
):ViewModel() {
    var state by mutableStateOf(HomeState())
        private set

    init {
        getWords()
    }

    private fun getWords(){
        viewModelScope.launch {
            repository.getWordsWithListAndSource.collectLatest {
                state = state.copy(
                    words = it
                )
            }
        }
    }

    fun deleteWord(word: Word){
        viewModelScope.launch {
            repository.deleteWord(word)
        }
    }

    fun onCategoryChange(category: Category){
        state = state.copy(category = category)
        filterBy(category.id)
    }

    fun onWordCheckedChange(word: Word, isChecked:Boolean){
        viewModelScope.launch {

            repository.updateWord(
                word = word.copy(isChecked = isChecked)
            )

        }


    }

    private fun filterBy(shoppingListId:Int){
        if (shoppingListId != 10001){
            viewModelScope.launch {
                repository.getWordWithSourceAndListFilteredById(
                    shoppingListId
                ).collectLatest {
                    state = state.copy(words = it)
                }
            }
        }else{
            getWords()
        }
    }
}

data class HomeState(
    val words:List<WordsWithSourceAndList> = emptyList(),
    val category: Category = Category(),
    val wordChecked:Boolean = false
)
