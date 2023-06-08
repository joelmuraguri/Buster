package com.example.network.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.network.model.Film
import com.example.network.service.BusterApiService
import retrofit2.HttpException
import java.io.IOException

class TrendingFilmSource(private val apiService: BusterApiService) : PagingSource<Int, Film>() {

    override fun getRefreshKey(state: PagingState<Int, Film>): Int? {
        return state.anchorPosition?.let { position ->
            val anchorPosition = state.closestPageToPosition(position)
            anchorPosition?.prevKey?.plus(1) ?: anchorPosition?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Film> {
        return try {
            val nextPage = params.key ?: 1
            val films = apiService.getTrendingMovies(page = nextPage)

            LoadResult.Page(
                data = films.results,
                nextKey = films.page.plus(1),
                prevKey = if (nextPage == 1) null else -1,
            )
        }
        catch (e: Exception){
            LoadResult.Error(e)
        }
        catch (e: IOException){
            LoadResult.Error(e)
        }
        catch (e: HttpException){
            LoadResult.Error(e)
        }
    }
}