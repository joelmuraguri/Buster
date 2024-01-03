package com.joel.network.api

import com.joel.network.response.film.movie.MovieApiResponse
import com.joel.network.response.film.movie.MovieCastApi
import com.joel.network.response.film.movie.MovieDetailsApi
import com.joel.network.response.film.movie.MovieGenreApi
import com.joel.network.response.film.movie.MovieReviewApi
import com.joel.network.response.film.search.SearchApi
import com.joel.network.response.film.tv.TvApiResponse
import com.joel.network.response.film.tv.TvCastApi
import com.joel.network.response.film.tv.TvDetailsApi
import com.joel.network.response.film.tv.TvGenreApi
import com.joel.network.response.film.tv.TvReviewApi

interface BusterRemoteSource {

    /**
    logic for network requests for movies
    * */
    suspend fun fetchUpcomingMovies(page : Int) : MovieApiResponse
    suspend fun fetchTopRatedMovies(page : Int) : MovieApiResponse
    suspend fun fetchPopularMovies(page : Int) : MovieApiResponse
    suspend fun fetchNowPlayingMovies(page : Int) : MovieApiResponse
    suspend fun fetchMovieGenre() : MovieGenreApi
    suspend fun fetchSimilarMovies(movieId: Int,page : Int) : MovieApiResponse
    suspend fun fetchMovieDetails(movieId : Int) : MovieDetailsApi
    suspend fun fetchCastMovies(movieId: Int) : MovieCastApi
    suspend fun fetchMovieReviews(movieId: Int, page : Int) : MovieReviewApi

    /**
    logic for network requests for tv shows
     * */
    suspend fun fetchOnTheAirTvShows(page : Int) : TvApiResponse
    suspend fun fetchTopRatedTvShows(page : Int ) : TvApiResponse
    suspend fun fetchPopularTvShows(page : Int) : TvApiResponse
    suspend fun fetchAiringTodayTvShows(page : Int) : TvApiResponse
    suspend fun fetchTvGenres() : TvGenreApi
    suspend fun fetchSimilarTvShows(tvId: Int, page : Int) : TvApiResponse
    suspend fun fetchTvShowsDetails(tvId : Int) : TvDetailsApi
    suspend fun fetchCastTvShows(tvId: Int) : TvCastApi
    suspend fun fetchTvShowsReviews(tvId: Int, page : Int) : TvReviewApi

    /**
    logic for network requests for search results
     * */
    suspend fun fetchSearchQuery(query : String, page: Int) : SearchApi


}