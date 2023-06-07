package com.example.network.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.network.model.Film
import com.example.network.service.BusterApiService

class TrendingFilmSource(private val apiService: BusterApiService) : PagingSource<Int, Film>() {

    override fun getRefreshKey(state: PagingState<Int, Film>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Film> {
        TODO("Not yet implemented")
    }
}