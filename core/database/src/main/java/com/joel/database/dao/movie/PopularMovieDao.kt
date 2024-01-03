package com.joel.database.dao.movie

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.joel.database.model.movie.PopularMovieEntity

@Dao
interface PopularMovieDao {

    @Query("SELECT * FROM PopularMovieEntity")
    fun getAll(): PagingSource<Int, PopularMovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movies: List<PopularMovieEntity>)

    @Query("DELETE FROM PopularMovieEntity")
    suspend fun deleteAll()
}