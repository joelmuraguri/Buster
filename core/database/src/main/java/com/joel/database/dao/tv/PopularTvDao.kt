package com.joel.database.dao.tv

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.joel.database.model.tv.PopularTvEntity

@Dao
interface PopularTvDao {

    @Query("SELECT * FROM PopularTvEntity")
    fun getAll(): PagingSource<Int, PopularTvEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(tvs: List<PopularTvEntity>)

    @Query("DELETE FROM PopularTvEntity")
    suspend fun deleteAll()
}