package com.joel.database.dao.search

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.joel.database.model.search.SearchEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SearchDao {

    /** fetches list of searches made by order of most recently searched and clicked on */
    @Query("SELECT *, datetime(timeStamp / 1000, 'unixepoch') as formattedDate FROM SearchEntity ORDER BY timeStamp DESC")
    fun fetchAll() : Flow<List<SearchEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSearchedItem(searchEntity: SearchEntity)

    @Query("DELETE FROM SearchEntity")
    suspend fun clearAll()

}