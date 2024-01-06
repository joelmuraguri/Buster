package com.joel.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("Remote_Keys_Table")
data class RemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val prevPage: Int?,
    val nextPage: Int?
)
