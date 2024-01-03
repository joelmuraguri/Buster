package com.joel.network.api

import com.joel.network.api.impl.BusterRemoteSourceImpl
import com.joel.network.response.film.search.SearchApi
import com.joel.network.utils.HttpClientFactory
import com.joel.network.utils.movie.movieGenre
import com.joel.network.utils.search.searchResponseModel
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.HttpHeaders
import io.ktor.http.headersOf
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import org.junit.Test

class SearchRemoteTest {

    private val query = "superman"

    @Test
    fun `success on Search Server Success`(){
        val mockEngine = MockEngine{
            respond(
                content = searchResponseModel,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }

        val client = HttpClientFactory().createEngine(mockEngine)

        runBlocking {

            // When
            val response = BusterRemoteSourceImpl(client, "").fetchSearchQuery(query, 5)

            // Then
            val expected = Json.decodeFromString<SearchApi>(searchResponseModel)
            TestCase.assertEquals(expected, response)
        }
    }
}