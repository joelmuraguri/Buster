package com.joel.data.paging.remote_mediator.movie

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.joel.data.mappers.toTopRatedMovieEntity
import com.joel.database.BusterDatabase
import com.joel.database.model.RemoteKeys
import com.joel.database.model.movie.TopRatedMovieEntity
import com.joel.network.api.BusterRemoteSource

@OptIn(ExperimentalPagingApi::class)
class TopRatedMovieMediator(
    private val db : BusterDatabase,
    private val remoteSource: BusterRemoteSource
) : RemoteMediator<Int, TopRatedMovieEntity>() {


    private val topRatedMovieDao = db.topRatedMovieDao()
    private val remoteKeysDao = db.remoteKeysDao()

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, TopRatedMovieEntity>
    ): MediatorResult {
        return try {
            val currentPage = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    prevPage
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    nextPage
                }
            }



            val response = remoteSource.fetchTopRatedMovies(page = currentPage)
            val endOfPaginationReached = response.movies.isEmpty()
            val movieEntities = response.movies.map { it.toTopRatedMovieEntity() }

            val prevPage = if (currentPage == 1) null else currentPage - 1
            val nextPage = if (endOfPaginationReached) null else currentPage + 1

            db.withTransaction {
                if (loadType == LoadType.REFRESH){
                    topRatedMovieDao.deleteAll()
                    remoteKeysDao.deleteAllRemoteKeys()
                }
                val keys = movieEntities.map { movie ->
                    RemoteKeys(
                        id = movie.filmId,
                        prevPage = prevPage,
                        nextPage = nextPage
                    )
                }
                remoteKeysDao.addAllRemoteKeys(remoteKeys = keys)
                topRatedMovieDao.insert(movies = movieEntities)
            }
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e : Exception){
            MediatorResult.Error(e)
        }

    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, TopRatedMovieEntity>
    ) : RemoteKeys?{
        return state.anchorPosition?.let { position->
            state.closestItemToPosition(position)?.filmId?.let { id ->
                remoteKeysDao.getRemoteKeys(id = id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, TopRatedMovieEntity>
    ) : RemoteKeys?{
        return state.pages.firstOrNull { it.data.isNotEmpty()  }?.data?.firstOrNull()
            ?.let { topRatedMovieEntity ->
                remoteKeysDao.getRemoteKeys(id = topRatedMovieEntity.filmId)
            }

    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, TopRatedMovieEntity>
    ) : RemoteKeys?{
        return state.pages.lastOrNull { it.data.isNotEmpty()  }?.data?.lastOrNull()
            ?.let { topRatedMovieEntity ->
                remoteKeysDao.getRemoteKeys(id = topRatedMovieEntity.filmId)
            }
    }

}