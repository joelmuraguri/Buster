package com.joel.database.dao.movie

import androidx.paging.PagingSource
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.joel.database.model.movie.TopRatedMovieEntity

interface TopRatedMovieDao {

    @Query("SELECT * FROM TopRatedMovieEntity")
    fun getAll(): PagingSource<Int, TopRatedMovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movies: List<TopRatedMovieEntity>)

    @Query("DELETE FROM TopRatedMovieEntity")
    suspend fun deleteAll()
}