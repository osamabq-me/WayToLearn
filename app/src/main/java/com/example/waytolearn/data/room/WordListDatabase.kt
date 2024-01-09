package com.example.waytolearn.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.waytolearn.data.room.models.Word
import com.example.waytolearn.data.room.models.Source
import com.example.waytolearn.data.room.models.ListingList


@Database(
    entities = [ListingList::class,Word::class,Source::class],
    version = 1,
    exportSchema = false
)
abstract class ListingListDatabase:RoomDatabase() {
    abstract fun listDao():ListDao
    abstract fun wordDao():WordDao
    abstract fun sourceDao():SourceDao

    companion object{
        @Volatile
        var INSTANCE:ListingListDatabase? = null
        fun getDatabase(context:Context):ListingListDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context,
                    ListingListDatabase::class.java,
                    "word_db"
                ).build()
                INSTANCE = instance
                return instance
            }
        }

    }

}





















