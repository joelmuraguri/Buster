package com.joel.discover

import androidx.compose.foundation.gestures.DraggableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.common.Resource
import com.example.domain.GetLatestFilmUseCase
import com.example.network.model.FilmDTO
import com.example.network.paging.PopularFilmSource
import com.example.network.paging.TopRatedFilmSource
import com.example.network.paging.TrendingFilmSource
import com.example.network.paging.UpcomingFimSource
import com.example.network.repo.PagedFilmsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiscoverViewModel @Inject constructor(
    private val repository: PagedFilmsRepository,
    private val getLatestFilmUseCase: GetLatestFilmUseCase
): ViewModel() {


    val popularFilms : Flow<PagingData<FilmDTO>> = Pager(PagingConfig(pageSize = 20)){
        PopularFilmSource(repository)
    }.flow.cachedIn(viewModelScope)

    val upcomingFilms : Flow<PagingData<FilmDTO>> = Pager(PagingConfig(pageSize = 20)){
        UpcomingFimSource(repository)
    }.flow.cachedIn(viewModelScope)

    val trendingFilms : Flow<PagingData<FilmDTO>> = Pager(PagingConfig(pageSize = 20)){
        TrendingFilmSource(repository)
    }.flow.cachedIn(viewModelScope)

    val topRatedFilms : Flow<PagingData<FilmDTO>> = Pager(PagingConfig(pageSize = 20)){
        TopRatedFilmSource(repository)
    }.flow.cachedIn(viewModelScope)


    private val _latestFilmState = mutableStateOf(LatestFilmState())
    val latestFilmState : State<LatestFilmState> = _latestFilmState


    private val _tabIndex: MutableStateFlow<Int> = MutableStateFlow(0)
    val tabIndex: StateFlow<Int> get() = _tabIndex.asStateFlow()

    val tabs = listOf("Now Playing", "Coming Soon" )

    private var isSwipeToTheLeft: Boolean = false
    private val draggableState = DraggableState { delta ->
        isSwipeToTheLeft = delta > 0
    }

    private val _dragState = MutableStateFlow(draggableState)
    val dragState: StateFlow<DraggableState> get() = _dragState.asStateFlow()

    init {
        getLatestFilm()
    }
    
    fun updateTabIndexBasedOnSwipe() {
        val currentIndex = _tabIndex.value
        val newIndex = when (isSwipeToTheLeft) {
            true -> Math.floorMod(currentIndex + 1, tabs.size)
            false -> Math.floorMod(currentIndex - 1, tabs.size)
        }
        _tabIndex.value = newIndex
    }

    fun updateTabIndex(i: Int) {
        _tabIndex.value = i
    }


    fun getLatestFilm(){
        viewModelScope.launch {
            getLatestFilmUseCase().onEach { result ->
                when(result){
                    is Resource.Error -> {
                        _latestFilmState.value = LatestFilmState(
                            error = result.errorMessage ?: "An unexpected error occurred"
                        )
                    }
                    is Resource.Loading -> {
                        _latestFilmState.value = LatestFilmState(
                            isLoading = true
                        )
                    }
                    is Resource.Success -> {
                        _latestFilmState.value = LatestFilmState(
                            latestFilm = result.data
                        )
                    }
                }
            }
        }
    }


}


data class LatestFilmState(
    val isLoading: Boolean = false,
    val latestFilm : FilmDTO ?= null,
    val error : String = ""
)