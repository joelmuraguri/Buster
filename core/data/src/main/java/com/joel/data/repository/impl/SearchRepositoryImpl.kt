package com.joel.data.repository.impl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.joel.data.mappers.toDataModel
import com.joel.data.model.SearchDataModel
import com.joel.data.paging.paging_source.SearchPagingSource
import com.joel.data.repository.SearchRepository
import com.joel.network.api.BusterRemoteSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val remoteSource: BusterRemoteSource
) : SearchRepository {

    override fun searchFilms(query: String): Flow<PagingData<SearchDataModel>> {
        return Pager(
            config = PagingConfig(pageSize = 5, enablePlaceholders = true),
            pagingSourceFactory = {
                SearchPagingSource(remoteSource = remoteSource, searchQuery = query)
            }
        ).flow
            .map { pagingData ->
                pagingData.map { search ->
                    search.toDataModel()
                }
            }
    }
}