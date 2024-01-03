package com.joel.database.dao.tv

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.joel.database.model.tv.AiringTodayTvEntity

@Dao
interface AiringTodayTvDao {

    @Query("SELECT * FROM AiringTodayTvEntity")
    fun getAll(): PagingSource<Int, AiringTodayTvEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(tvs: List<AiringTodayTvEntity>)

    @Query("DELETE FROM AiringTodayTvEntity")
    suspend fun deleteAll()
}