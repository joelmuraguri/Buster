package com.example.network.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.network.model.FilmDTO
import com.example.network.repo.PagedFilmsRepository

class PopularFilmSource(
    private val repository: PagedFilmsRepository) : PagingSource<Int, FilmDTO>() {
    override fun getRefreshKey(state: PagingState<Int, FilmDTO>): Int? {
        return state.anchorPosition?.let { position ->
            val anchorPosition = state.closestPageToPosition(position)
            anchorPosition?.prevKey?.plus(1) ?: anchorPosition?.nextKey?.plus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, FilmDTO> {
        return try {
            val nextPage = params.key ?: 1
            val response = repository.getPopularFilms(nextPage)
            LoadResult.Page(
                data = response.results,
                nextKey = response.page.plus(1),
                prevKey = if (nextPage ==1) null else -1
            )
        }
        catch (e : Exception){
            LoadResult.Error(e)
        }
    }
}