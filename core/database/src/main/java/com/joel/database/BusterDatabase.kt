package com.joel.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.joel.database.dao.WatchListDao
import com.joel.database.dao.movie.NowPlayingMovieDao
import com.joel.database.dao.movie.PopularMovieDao
import com.joel.database.dao.movie.TopRatedMovieDao
import com.joel.database.dao.movie.TrendingMovieDao
import com.joel.database.dao.search.SearchDao
import com.joel.database.dao.tv.AiringTodayTvDao
import com.joel.database.dao.tv.OnTheAirTvDao
import com.joel.database.dao.tv.PopularTvDao
import com.joel.database.dao.tv.TopRatedTvDao
import com.joel.database.model.WatchListEntity
import com.joel.database.model.movie.NowPlayingMovieEntity
import com.joel.database.model.movie.PopularMovieEntity
import com.joel.database.model.movie.TopRatedMovieEntity
import com.joel.database.model.movie.TrendingMovieEntity
import com.joel.database.model.search.SearchEntity
import com.joel.database.model.tv.AiringTodayTvEntity
import com.joel.database.model.tv.OnAirTvEntity
import com.joel.database.model.tv.PopularTvEntity
import com.joel.database.model.tv.TopRatedTvEntity
import com.joel.database.utils.StringTypeConverter

@Database(
    entities = [
        SearchEntity::class,
        WatchListEntity::class,
        TrendingMovieEntity::class,
        TopRatedMovieEntity::class,
        NowPlayingMovieEntity::class,
        PopularMovieEntity::class,
        AiringTodayTvEntity::class,
        OnAirTvEntity::class, PopularTvEntity::class,
        TopRatedTvEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(StringTypeConverter::class)
abstract class BusterDatabase : RoomDatabase() {

    abstract fun searchDao() : SearchDao
    abstract fun watchListDao() : WatchListDao
    abstract fun trendingMovieDao() : TrendingMovieDao
    abstract fun popularMovieDao() : PopularMovieDao
    abstract fun nowPlayingMovieDao() : NowPlayingMovieDao
    abstract fun topRatedMovieDao() : TopRatedMovieDao
    abstract fun topRatedTvDao() : TopRatedTvDao
    abstract fun popularTvDao() : PopularTvDao
    abstract fun airingTodayTvDao() : AiringTodayTvDao
    abstract fun onTheAirTvDao() : OnTheAirTvDao

}