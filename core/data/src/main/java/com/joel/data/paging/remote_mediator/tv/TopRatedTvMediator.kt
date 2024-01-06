package com.joel.data.paging.remote_mediator.tv

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.joel.data.mappers.toTopRatedTvEntity
import com.joel.database.BusterDatabase
import com.joel.database.model.RemoteKeys
import com.joel.database.model.tv.TopRatedTvEntity
import com.joel.network.api.BusterRemoteSource

@OptIn(ExperimentalPagingApi::class)
class TopRatedTvMediator(
    private val db : BusterDatabase,
    private val remoteSource: BusterRemoteSource
) : RemoteMediator<Int, TopRatedTvEntity>() {


    private val topRatedTvDao = db.topRatedTvDao()
    private val remoteKeysDao = db.remoteKeysDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, TopRatedTvEntity>
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



            val response = remoteSource.fetchTopRatedTvShows(page = currentPage)
            val endOfPaginationReached = response.tvs.isEmpty()
            val tvEntities = response.tvs.map { it.toTopRatedTvEntity() }

            val prevPage = if (currentPage == 1) null else currentPage - 1
            val nextPage = if (endOfPaginationReached) null else currentPage + 1

            db.withTransaction {
                if (loadType == LoadType.REFRESH){
                    topRatedTvDao.deleteAll()
                    remoteKeysDao.deleteAllRemoteKeys()
                }
                val keys = tvEntities.map { movie ->
                    RemoteKeys(
                        id = movie.filmId,
                        prevPage = prevPage,
                        nextPage = nextPage
                    )
                }
                remoteKeysDao.addAllRemoteKeys(remoteKeys = keys)
                topRatedTvDao.insert(tvs = tvEntities)
            }
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e : Exception){
            MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, TopRatedTvEntity>
    ) : RemoteKeys?{
        return state.anchorPosition?.let { position->
            state.closestItemToPosition(position)?.filmId?.let { id ->
                remoteKeysDao.getRemoteKeys(id = id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, TopRatedTvEntity>
    ) : RemoteKeys?{
        return state.pages.firstOrNull { it.data.isNotEmpty()  }?.data?.firstOrNull()
            ?.let { topRatedMovieEntity ->
                remoteKeysDao.getRemoteKeys(id = topRatedMovieEntity.filmId)
            }

    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, TopRatedTvEntity>
    ) : RemoteKeys?{
        return state.pages.lastOrNull { it.data.isNotEmpty()  }?.data?.lastOrNull()
            ?.let { topRatedMovieEntity ->
                remoteKeysDao.getRemoteKeys(id = topRatedMovieEntity.filmId)
            }

    }
}