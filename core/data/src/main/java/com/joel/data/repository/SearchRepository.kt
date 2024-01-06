package com.joel.data.repository

import androidx.paging.PagingData
import com.joel.data.model.SearchDataModel
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    fun searchFilms(query : String) : Flow<PagingData<SearchDataModel>>
}