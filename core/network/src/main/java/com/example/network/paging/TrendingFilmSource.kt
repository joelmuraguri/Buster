package com.example.network.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.network.model.FilmDTO
import com.example.network.service.BusterApiService
import retrofit2.HttpException
import java.io.IOException

class TrendingFilmSource(private val apiService: BusterApiService) : PagingSource<Int, FilmDTO>() {

    override fun getRefreshKey(state: PagingState<Int, FilmDTO>): Int? {
        return state.anchorPosition?.let { position ->
            val anchorPosition = state.closestPageToPosition(position)
            anchorPosition?.prevKey?.plus(1) ?: anchorPosition?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, FilmDTO> {
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