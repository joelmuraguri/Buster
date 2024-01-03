package com.joel.database.dao.tv

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.joel.database.model.tv.TopRatedTvEntity

@Dao
interface TopRatedTvDao {

    @Query("SELECT * FROM TopRatedTvEntity")
    fun getAll(): PagingSource<Int, TopRatedTvEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(tvs: List<TopRatedTvEntity>)

    @Query("DELETE FROM TopRatedTvEntity")
    suspend fun deleteAll()
}