package com.joel.database.dao.movie

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.joel.database.model.movie.NowPlayingMovieEntity
import com.joel.database.model.movie.TrendingMovieEntity

@Dao
interface NowPlayingMovieDao {

    @Query("SELECT * FROM NowPlayingMovieEntity")
    fun getAll(): PagingSource<Int, NowPlayingMovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movies: List<NowPlayingMovieEntity>)

    @Query("DELETE FROM NowPlayingMovieEntity")
    suspend fun deleteAll()

}