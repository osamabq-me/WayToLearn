package com.example.waytolearn.ui.repository

import com.example.waytolearn.data.room.ListDao
import com.example.waytolearn.data.room.SourceDao
import com.example.waytolearn.data.room.WordDao
import com.example.waytolearn.data.room.models.Source
import com.example.waytolearn.data.room.models.Word
import com.example.waytolearn.data.room.models.ListingList

class Repository(
    private val listDao: ListDao,
    private val sourceDao: SourceDao,
    private val wordDao: WordDao,
) {
    val source = sourceDao.getAllSources()
    val getWordsWithListAndSource = listDao.getWordsWithSourceAndList()

    fun getWordWithSourceAndList(id: Int) = listDao
        .getWordWithSourceAndListFilteredById(id)

    fun getWordWithSourceAndListFilteredById(id: Int) =
        listDao.getWordsWithSourceAndListFilteredById(id)

    suspend fun insertList(listingList: ListingList) {
        listDao.insertListingList(listingList)
    }

    suspend fun insertSource(source: Source) {
        sourceDao.insert(source)
    }

    suspend fun insertWord(word: Word) {
        wordDao.insert(word)
    }

    suspend fun deleteWord(word: Word) {
        wordDao.delete(word)
    }

    suspend fun updateWord(word: Word) {
        wordDao.update(word)
    }


}