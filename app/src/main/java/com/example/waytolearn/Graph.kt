package com.example.waytolearn

import android.content.Context
import com.example.waytolearn.data.room.ListingListDatabase
import com.example.waytolearn.ui.repository.Repository

object Graph {
    lateinit var db:ListingListDatabase
        private set

    val repository by lazy {
        Repository(
            listDao = db.listDao(),
            sourceDao = db.sourceDao(),
            wordDao = db.wordDao()
        )
    }

    fun provide(context:Context){
        db = ListingListDatabase.getDatabase(context)
    }

}