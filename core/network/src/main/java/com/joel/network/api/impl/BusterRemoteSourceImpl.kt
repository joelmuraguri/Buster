package com.joel.network.api.impl

import com.joel.network.api.BusterRemoteSource
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
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import javax.inject.Inject

class BusterRemoteSourceImpl @Inject constructor(
    private val client : HttpClient,
    private val apiKey : String,
) : BusterRemoteSource {

    override suspend fun fetchUpcomingMovies(page: Int): MovieApiResponse =
        client.get("https://api.themoviedb.org/3/movie/upcoming"){
            parameter("page", page)
            parameter("api_key",apiKey)
        }.body()

    override suspend fun fetchTopRatedMovies(page: Int): MovieApiResponse =
        client.get("https://api.themoviedb.org/3/movie/top_rated"){
            parameter("page", page)
            parameter("api_key",apiKey)
        }.body()

    override suspend fun fetchPopularMovies(page: Int): MovieApiResponse =
        client.get("https://api.themoviedb.org/3/movie/popular"){
            parameter("page", page)
            parameter("api_key",apiKey)
        }.body()


    override suspend fun fetchNowPlayingMovies(page: Int): MovieApiResponse =
        client.get("https://api.themoviedb.org/3/movie/now_playing"){
            parameter("page", page)
            parameter("api_key",apiKey)
        }.body()

    override suspend fun fetchMovieGenre(): MovieGenreApi =
        client.get("https://api.themoviedb.org/3/genre/movie/list"){
            parameter("api_key",apiKey)
        }.body()


    override suspend fun fetchSimilarMovies(movieId: Int, page: Int): MovieApiResponse =
        client.get("https://api.themoviedb.org/3/movie/$movieId/similar"){
            parameter("api_key",apiKey)
            parameter("page", page)
        }.body()

    override suspend fun fetchMovieDetails(movieId: Int): MovieDetailsApi =
        client.get("https://api.themoviedb.org/3/movie/$movieId") {
            parameter("api_key",apiKey)
        }.body()

    override suspend fun fetchCastMovies(movieId: Int): MovieCastApi =
        client.get("https://api.themoviedb.org/3/movie/$movieId/credits") {
            parameter("api_key",apiKey)
        }.body()

    override suspend fun fetchMovieReviews(movieId: Int, page: Int): MovieReviewApi =
        client.get("https://api.themoviedb.org/3/movie/$movieId/reviews") {
            parameter("api_key",apiKey)
            parameter("page",page)
        }.body()

    override suspend fun fetchOnTheAirTvShows(page: Int): TvApiResponse =
        client.get("https://api.themoviedb.org/3/tv/on_the_air"){
            parameter("api_key",apiKey)
            parameter("page",page)
        }.body()

    override suspend fun fetchTopRatedTvShows(page: Int): TvApiResponse =
        client.get("https://api.themoviedb.org/3/tv/top_ratedr"){
            parameter("api_key",apiKey)
            parameter("page",page)
        }.body()


    override suspend fun fetchPopularTvShows(page: Int): TvApiResponse =
        client.get("https://api.themoviedb.org/3/tv/popular"){
            parameter("api_key",apiKey)
            parameter("page",page)
        }.body()

    override suspend fun fetchAiringTodayTvShows(page: Int): TvApiResponse =
        client.get("https://api.themoviedb.org/3/tv/airing_today"){
            parameter("api_key",apiKey)
            parameter("page",page)
        }.body()

    override suspend fun fetchTvGenres(): TvGenreApi =
        client.get("https://api.themoviedb.org/3/genre/tv/list"){
            parameter("api_key",apiKey)
        }.body()


    override suspend fun fetchSimilarTvShows(tvId: Int, page: Int): TvApiResponse =
        client.get("https://api.themoviedb.org/3/tv/$tvId/similar"){
            parameter("api_key",apiKey)
            parameter("page",page)
        }.body()


    override suspend fun fetchTvShowsDetails(tvId: Int): TvDetailsApi =
        client.get("https://api.themoviedb.org/3/tv/$tvId"){
            parameter("api_key",apiKey)
        }.body()

    override suspend fun fetchCastTvShows(tvId: Int): TvCastApi =
        client.get("https://api.themoviedb.org/3/tv/$tvId/credits"){
            parameter("api_key",apiKey)
        }.body()

    override suspend fun fetchTvShowsReviews(tvId: Int, page: Int): TvReviewApi =
        client.get("https://api.themoviedb.org/3/tv/$tvId/reviews"){
            parameter("api_key",apiKey)
            parameter("page",page)
        }.body()

    override suspend fun fetchSearchQuery(query: String, page: Int): SearchApi =
        client.get("https://api.themoviedb.org/3/search/multi") {
           parameter("page", page)
           parameter("api_key",apiKey)
           parameter("query",query)
        }.body()
}