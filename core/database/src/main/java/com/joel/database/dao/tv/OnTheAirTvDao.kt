package com.joel.database.dao.tv

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.joel.database.model.tv.OnAirTvEntity

@Dao
interface OnTheAirTvDao {

    @Query("SELECT * FROM OnAirTvEntity")
    fun getAll(): PagingSource<Int, OnAirTvEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(tvs: List<OnAirTvEntity>)

    @Query("DELETE FROM OnAirTvEntity")
    suspend fun deleteAll()
}