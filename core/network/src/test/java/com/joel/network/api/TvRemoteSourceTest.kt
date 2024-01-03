package com.joel.network.api

import com.joel.network.api.impl.BusterRemoteSourceImpl
import com.joel.network.response.film.tv.TvApiResponse
import com.joel.network.response.film.tv.TvCastApi
import com.joel.network.response.film.tv.TvDetailsApi
import com.joel.network.response.film.tv.TvGenreApi
import com.joel.network.response.film.tv.TvReviewApi
import com.joel.network.utils.HttpClientFactory
import com.joel.network.utils.tv.tvApiModel
import com.joel.network.utils.tv.tvCastModel
import com.joel.network.utils.tv.tvDetailsModel
import com.joel.network.utils.tv.tvGenreModel
import com.joel.network.utils.tv.tvReviewModel
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.HttpHeaders
import io.ktor.http.headersOf
import junit.framework.TestCase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import org.junit.Test

class TvRemoteSourceTest {


    private val tvId = 4614

    @Test
    fun `success on TopRated Tv Success`(){
        val mockEngine = MockEngine{
            respond(
                content = tvApiModel,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }

        val client = HttpClientFactory().createEngine(mockEngine)

        runBlocking {
            // When
            val response = BusterRemoteSourceImpl(client, "").fetchTopRatedTvShows(1)

            // Then
            val expected = Json.decodeFromString<TvApiResponse>(tvApiModel)
            TestCase.assertEquals(expected, response)
        }
    }

    @Test
    fun `success on OnTheAir TV Success`(){
        val mockEngine = MockEngine{
            respond(
                content = tvApiModel,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }

        val client = HttpClientFactory().createEngine(mockEngine)

        runBlocking {
            // When
            val response = BusterRemoteSourceImpl(client, "").fetchOnTheAirTvShows(1)

            // Then
            val expected = Json.decodeFromString<TvApiResponse>(tvApiModel)
            TestCase.assertEquals(expected, response)
        }
    }

    @Test
    fun `success on Popular Tv Success`(){
        val mockEngine = MockEngine{
            respond(
                content = tvApiModel,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }

        val client = HttpClientFactory().createEngine(mockEngine)

        runBlocking {
            // When
            val response = BusterRemoteSourceImpl(client, "").fetchPopularTvShows(1)

            // Then
            val expected = Json.decodeFromString<TvApiResponse>(tvApiModel)
            TestCase.assertEquals(expected, response)
        }
    }


    @Test
    fun `success on AiringToday Tv Success`(){
        val mockEngine = MockEngine{
            respond(
                content = tvApiModel,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }

        val client = HttpClientFactory().createEngine(mockEngine)

        runBlocking {
            // When
            val response = BusterRemoteSourceImpl(client, "").fetchAiringTodayTvShows(1)

            // Then
            val expected = Json.decodeFromString<TvApiResponse>(tvApiModel)
            TestCase.assertEquals(expected, response)
        }
    }

    @Test
    fun `success on Tv Details Success`(){
        val mockEngine = MockEngine{
            respond(
                content = tvDetailsModel,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }

        val client = HttpClientFactory().createEngine(mockEngine)

        runBlocking {

            // When
            val response = BusterRemoteSourceImpl(client, "").fetchTvShowsDetails(tvId)

            // Then
            val expected = Json.decodeFromString<TvDetailsApi>(tvDetailsModel)
            TestCase.assertEquals(expected, response)
        }
    }

    @Test
    fun `success on Tv Reviews Success`(){
        val mockEngine = MockEngine{
            respond(
                content = tvReviewModel,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }

        val client = HttpClientFactory().createEngine(mockEngine)

        runBlocking {

            // When
            val response = BusterRemoteSourceImpl(client, "").fetchTvShowsReviews(tvId, 1)

            // Then
            val expected = Json.decodeFromString<TvReviewApi>(tvReviewModel)
            TestCase.assertEquals(expected, response)
        }
    }

    @Test
    fun `success on Similar Tv Success`(){
        val mockEngine = MockEngine{
            respond(
                content = tvApiModel,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }

        val client = HttpClientFactory().createEngine(mockEngine)

        runBlocking {

            // When
            val response = BusterRemoteSourceImpl(client, "").fetchSimilarTvShows(tvId, 1)

            // Then
            val expected = Json.decodeFromString<TvApiResponse>(tvApiModel)
            TestCase.assertEquals(expected, response)
        }
    }

    @Test
    fun `success on Movie Cast Success`(){
        val mockEngine = MockEngine{
            respond(
                content = tvCastModel,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }

        val client = HttpClientFactory().createEngine(mockEngine)

        runBlocking {

            // When
            val response = BusterRemoteSourceImpl(client, "").fetchCastTvShows(tvId)

            // Then
            val expected = Json.decodeFromString<TvCastApi>(tvCastModel)
            assertEquals(expected, response)
        }
    }

    @Test
    fun `success on Tv Genres Success`(){
        val mockEngine = MockEngine{
            respond(
                content = tvGenreModel,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }

        val client = HttpClientFactory().createEngine(mockEngine)

        runBlocking {

            // When
            val response = BusterRemoteSourceImpl(client, "").fetchTvGenres()

            // Then
            val expected = Json.decodeFromString<TvGenreApi>(tvGenreModel)
            TestCase.assertEquals(expected, response)
        }
    }

}