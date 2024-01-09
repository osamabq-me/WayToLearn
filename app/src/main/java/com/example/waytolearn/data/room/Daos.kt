package com.example.waytolearn.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.waytolearn.data.room.models.Word
import com.example.waytolearn.data.room.models.Source
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

