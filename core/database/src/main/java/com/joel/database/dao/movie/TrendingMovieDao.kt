package com.joel.database.dao.movie

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.joel.database.model.movie.TrendingMovieEntity

@Dao
interface TrendingMovieDao {

    @Query("SELECT * FROM TrendingMovieEntity")
    fun getAll(): PagingSource<Int, TrendingMovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movies: List<TrendingMovieEntity>)

    @Query("DELETE FROM TrendingMovieEntity")
    suspend fun deleteAll()
}