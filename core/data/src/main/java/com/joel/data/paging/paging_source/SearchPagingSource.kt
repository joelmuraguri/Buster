package com.joel.data.paging.paging_source

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.joel.network.api.BusterRemoteSource
import com.joel.network.response.film.search.SearchApi
import java.io.IOException

class SearchPagingSource(
    private val remoteSource: BusterRemoteSource,
    private val searchQuery :String
) : PagingSource<Int, SearchApi.Result>() {

    override fun getRefreshKey(state: PagingState<Int, SearchApi.Result>): Int? = state.anchorPosition

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SearchApi.Result> {
        return try {
            val nextPage = params.key ?: 1
            val searchFilms = remoteSource.fetchSearchQuery(
                page = nextPage,
                query = searchQuery
            )
            LoadResult.Page(
                data = searchFilms.results,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (searchFilms.results.isEmpty()) null else searchFilms.page?.plus(1)
            )

        } catch (e : IOException){
            LoadResult.Error(e)
        } catch (e : HttpException){
            LoadResult.Error(e)
        }
    }
}