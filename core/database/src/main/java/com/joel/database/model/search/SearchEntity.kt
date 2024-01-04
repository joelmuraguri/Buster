package com.joel.database.model.search

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("SearchEntity")
data class SearchEntity(
    @PrimaryKey val filmId : Int,
    val title : String,
    val postUrl : String,
    val filmType : String,
    val releaseDate : String,
    val genres : List<String>,
    @ColumnInfo("formattedDate")
    val timeStamp : Long
)
