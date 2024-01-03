package com.joel.network.api

import com.joel.network.api.impl.BusterRemoteSourceImpl
import com.joel.network.response.film.movie.MovieApiResponse
import com.joel.network.response.film.movie.MovieDetailsApi
import com.joel.network.response.film.movie.MovieGenreApi
import com.joel.network.response.film.movie.MovieReviewApi
import com.joel.network.utils.HttpClientFactory
import com.joel.network.utils.movie.movieDetailsModel
import com.joel.network.utils.movie.movieGenre
import com.joel.network.utils.movie.movieReviewsModel
import com.joel.network.utils.movie.nowPlayingMovieModel
import com.joel.network.utils.movie.popularMovieModel
import com.joel.network.utils.movie.similarMovieModel
import com.joel.network.utils.movie.topRatedMovieModel
import com.joel.network.utils.movie.upComingMovieModel
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.HttpHeaders
import io.ktor.http.headersOf
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import org.junit.Test

class MovieRemoteSourceTest {

    private val movieId = 572802

    @Test
    fun `success on TopRated Movie Success`(){
        val mockEngine = MockEngine{
            respond(
                content = topRatedMovieModel,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }

        val client = HttpClientFactory().createEngine(mockEngine)

        runBlocking {
            // When
            val response = BusterRemoteSourceImpl(client, "").fetchTopRatedMovies(20)

            // Then
            val expected = Json.decodeFromString<MovieApiResponse>(topRatedMovieModel)
            assertEquals(expected, response)
        }
    }

    @Test
    fun `success on Upcoming Movie Success`(){
        val mockEngine = MockEngine{
            respond(
                content = upComingMovieModel,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }

        val client = HttpClientFactory().createEngine(mockEngine)

        runBlocking {
            // When
            val response = BusterRemoteSourceImpl(client, "").fetchUpcomingMovies(20)

            // Then
            val expected = Json.decodeFromString<MovieApiResponse>(upComingMovieModel)
            assertEquals(expected, response)
        }
    }

    @Test
    fun `success on Popular Movie Success`(){
        val mockEngine = MockEngine{
            respond(
                content = popularMovieModel,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }

        val client = HttpClientFactory().createEngine(mockEngine)

        runBlocking {
            // When
            val response = BusterRemoteSourceImpl(client, "").fetchUpcomingMovies(20)

            // Then
            val expected = Json.decodeFromString<MovieApiResponse>(popularMovieModel)
            assertEquals(expected, response)
        }
    }


    @Test
    fun `success on NowPlaying Movie Success`(){
        val mockEngine = MockEngine{
            respond(
                content = nowPlayingMovieModel,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }

        val client = HttpClientFactory().createEngine(mockEngine)

        runBlocking {
            // When
            val response = BusterRemoteSourceImpl(client, "").fetchUpcomingMovies(1)

            // Then
            val expected = Json.decodeFromString<MovieApiResponse>(nowPlayingMovieModel)
            assertEquals(expected, response)
        }
    }

    @Test
    fun `success on Movie Details Success`(){
        val mockEngine = MockEngine{
            respond(
                content = movieDetailsModel,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }

        val client = HttpClientFactory().createEngine(mockEngine)

        runBlocking {

            // When
            val response = BusterRemoteSourceImpl(client, "").fetchMovieDetails(movieId)

            // Then
            val expected = Json.decodeFromString<MovieDetailsApi>(movieDetailsModel)
            assertEquals(expected, response)
        }
    }

    @Test
    fun `success on Movie Reviews Success`(){
        val mockEngine = MockEngine{
            respond(
                content = movieReviewsModel,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }

        val client = HttpClientFactory().createEngine(mockEngine)

        runBlocking {

            // When
            val response = BusterRemoteSourceImpl(client, "").fetchMovieReviews(movieId, 1)

            // Then
            val expected = Json.decodeFromString<MovieReviewApi>(movieReviewsModel)
            assertEquals(expected, response)
        }
    }

    @Test
    fun `success on Similar Movies Success`(){
        val mockEngine = MockEngine{
            respond(
                content = similarMovieModel,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }

        val client = HttpClientFactory().createEngine(mockEngine)

        runBlocking {

            // When
            val response = BusterRemoteSourceImpl(client, "").fetchSimilarMovies(movieId, 20)

            // Then
            val expected = Json.decodeFromString<MovieApiResponse>(similarMovieModel)
            assertEquals(expected, response)
        }
    }

//    @Test
//    fun `success on Movie Cast Success`(){
//        val mockEngine = MockEngine{
//            respond(
//                content = movieCastModel,
//                headers = headersOf(HttpHeaders.ContentType, "application/json")
//            )
//        }
//
//        val client = HttpClientFactory().createEngine(mockEngine)
//
//        runBlocking {
//
//            // When
//            val response = BusterRemoteSourceImpl(client, "", 1).fetchCastMovies(movieId)
//
//            // Then
//            val expected = Json.decodeFromString<MovieCastApi>(movieCastModel)
//            assertEquals(expected, response)
//        }
//    }

    @Test
    fun `success on Movie Genres Success`(){
        val mockEngine = MockEngine{
            respond(
                content = movieGenre,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }

        val client = HttpClientFactory().createEngine(mockEngine)

        runBlocking {

            // When
            val response = BusterRemoteSourceImpl(client, "").fetchMovieGenre()

            // Then
            val expected = Json.decodeFromString<MovieGenreApi>(movieGenre)
            assertEquals(expected, response)
        }
    }


}