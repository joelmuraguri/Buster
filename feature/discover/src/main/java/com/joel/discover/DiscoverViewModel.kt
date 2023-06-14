package com.joel.discover

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.network.model.FilmDTO
import com.example.network.paging.PopularFilmSource
import com.example.network.paging.UpcomingFimSource
import com.example.network.repo.PagedFilmsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class DiscoverViewModel @Inject constructor(
    private val repository: PagedFilmsRepository
): ViewModel() {


    val popularFilms : Flow<PagingData<FilmDTO>> = Pager(PagingConfig(pageSize = 20)){
        PopularFilmSource(repository)
    }.flow.cachedIn(viewModelScope)

    val upcomingFilms : Flow<PagingData<FilmDTO>> = Pager(PagingConfig(pageSize = 20)){
        UpcomingFimSource(repository)
    }.flow.cachedIn(viewModelScope)



}