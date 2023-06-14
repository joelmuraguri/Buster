package com.example.network.repo

import com.example.common.Resource
import com.example.network.model.FilmDTO
import com.example.network.service.BusterApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class FilmRepoImpl(
    private val apiService: BusterApiService
) : FilmRepo {

    override suspend fun getLatestFilm(): Flow<Resource<FilmDTO>> = flow {
        emit(Resource.Loading())
        try {
            val response = apiService.getLatestFilm()
            emit(Resource.Success(response))
        } catch (e : HttpException){
            emit(Resource.Error(errorMessage =  e.localizedMessage ?: "An expected Error Occurred"))
        } catch ( e: IOException){
            emit(Resource.Error(errorMessage = "Couldn't reach Server. Check your Internet Connection"))
        }
    }

    override suspend fun getFilmDetails(id: Int): Flow<Resource<FilmDTO>> {
        TODO("Not yet implemented")
    }
}