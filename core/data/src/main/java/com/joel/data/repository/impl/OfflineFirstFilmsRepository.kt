package com.joel.data.repository.impl

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.joel.data.mappers.toDatModel
import com.joel.data.model.FilmDataModel
import com.joel.data.paging.remote_mediator.movie.TopRatedMovieMediator
import com.joel.data.paging.remote_mediator.tv.TopRatedTvMediator
import com.joel.data.repository.FilmRepository
import com.joel.database.BusterDatabase
import com.joel.network.api.BusterRemoteSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class OfflineFirstFilmsRepository @Inject constructor(
    private val remoteSource: BusterRemoteSource,
    private val db : BusterDatabase,
) : FilmRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getTopRatedMovies(): Flow<PagingData<FilmDataModel>> {
        val pagingSourceFactory = { db.topRatedMovieDao().getAll()}

        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = true),
            remoteMediator = TopRatedMovieMediator(
                db, remoteSource
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
            .map { pagingData ->
                pagingData.map { movieEntity->
                    movieEntity.toDatModel()
                }
            }
    }

    override fun getTrendingMovies(): Flow<PagingData<FilmDataModel>> {
        TODO("Not yet implemented")
    }

    override fun getNowPlayingMovies(): Flow<PagingData<FilmDataModel>> {
        TODO("Not yet implemented")
    }

    override fun getPopularMovies(): Flow<PagingData<FilmDataModel>> {
        TODO("Not yet implemented")
    }

    override fun getOnTheAirTv(): Flow<PagingData<FilmDataModel>> {
        TODO("Not yet implemented")
    }

    override fun getAiringTodayTv(): Flow<PagingData<FilmDataModel>> {
        TODO("Not yet implemented")
    }

    override fun getPopularTv(): Flow<PagingData<FilmDataModel>> {
        TODO("Not yet implemented")
    }

    @OptIn(ExperimentalPagingApi::class)
    override fun getTopRatedTv(): Flow<PagingData<FilmDataModel>> {
        val pagingSourceFactory = { db.topRatedTvDao().getAll()}

        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = true),
            remoteMediator = TopRatedTvMediator(
                db, remoteSource
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
            .map { pagingData ->
                pagingData.map { movieEntity->
                    movieEntity.toDatModel()
                }
            }
    }
}