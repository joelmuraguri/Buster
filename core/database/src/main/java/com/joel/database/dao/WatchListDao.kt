package com.joel.database.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.joel.database.model.WatchListEntity
import kotlinx.coroutines.flow.Flow

interface WatchListDao {

    @Query("SELECT * FROM WatchListEntity")
    fun fetchAll() : Flow<List<WatchListEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSearchedItem(watchListEntity: WatchListEntity)

    @Query("DELETE FROM SearchEntity")
    suspend fun clearAll()

}