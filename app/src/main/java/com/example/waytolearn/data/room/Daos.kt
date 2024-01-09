package com.example.waytolearn.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Embedded
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.waytolearn.data.room.models.Word
import com.example.waytolearn.data.room.models.Source
import com.example.waytolearn.data.room.models.ListingList
import kotlinx.coroutines.flow.Flow



@Dao
interface WordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(word: Word)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(word: Word)

    @Delete
    suspend fun delete(word: Word)

    @Query("SELECT * FROM words")
    fun getAllWords(): Flow<List<Word>>

    @Query("SELECT * FROM words WHERE word_id =:wordid")
    fun getWord(wordid: Int): Flow<Word>

}

@Dao
interface SourceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(source: Source)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(source: Source)

    @Delete
    suspend fun delete(word: Word)

    @Query("SELECT * FROM sources")
    fun getAllSources(): Flow<List<Source>>

    @Query("SELECT * FROM sources WHERE source_id =:sourceId")
    fun getSource(sourceId: Int): Flow<Source>
}

@Dao
interface ListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertListingList(shoppingList: ListingList): Long

    @Query("""
        SELECT * FROM words AS I INNER JOIN listing_list AS S
        ON I.listId = S.list_id INNER JOIN sources AS ST
        ON I.sourceIdFk = ST.source_id
    """)
    fun getWordsWithSourceAndList():Flow<List<WordsWithSourceAndList>>
    @Query("""
        SELECT * FROM words AS I INNER JOIN listing_list AS S
        ON I.listId = S.list_id INNER JOIN sources AS ST
        ON I.sourceIdFk = ST.source_id WHERE S.list_id =:listId
    """)
    fun getWordsWithSourceAndListFilteredById(listId:Int)
            :Flow<List<WordsWithSourceAndList>>

    @Query("""
        SELECT * FROM words AS I INNER JOIN listing_list AS S
        ON I.listId = S.list_id INNER JOIN sources AS ST
        ON I.sourceIdFk = ST.source_id WHERE I.word_id =:wordid
    """)
    fun getWordWithSourceAndListFilteredById(wordid: Int)
            :Flow<WordsWithSourceAndList>
}

data class WordsWithSourceAndList(
    @Embedded val word: Word,
    @Embedded val listingList: ListingList,
    @Embedded val source: Source,
)




