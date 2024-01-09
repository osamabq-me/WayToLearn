package com.example.waytolearn.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "listing_list")
data class ListingList(
    @ColumnInfo(name = "list_id")
    @PrimaryKey
    val id: Int,
    val name: String,
)

@Entity(tableName = "words")
data class Word(
    @ColumnInfo(name = "word_id")
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val wordName: String,
    val mean: String,
    val descraipe: String,
    val listId: Int,
    val sourceIdFk: Int,
    val isChecked: Boolean,

    )

@Entity(tableName = "sources")
data class Source(
    @ColumnInfo(name = "source_id")
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val listIdFk: Int,
    val sourceName: String,
)


