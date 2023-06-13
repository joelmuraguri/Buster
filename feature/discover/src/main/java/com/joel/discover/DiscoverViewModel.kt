package com.joel.discover

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.network.model.FilmDTO
import com.example.network.repo.FilmRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiscoverViewModel @Inject constructor(
    private val repository: FilmRepository
): ViewModel() {

    private var _trendingMovies = mutableStateOf<Flow<PagingData<FilmDTO>>>(emptyFlow())
    val trendingMoviesState: State<Flow<PagingData<FilmDTO>>> = _trendingMovies

    init {
        refresh()
    }

    fun refresh(){
        getTrendingFilms()
    }

    private fun getTrendingFilms() {
        viewModelScope.launch {
            _trendingMovies.value = repository.getTrendingMovies().cachedIn(viewModelScope)
        }
    }


}