package com.abhinav.musically.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abhinav.musically.common.CoroutineDispatcherProvider
import com.abhinav.musically.features.ScreenState
import com.abhinav.musically.features.home.usecase.GetArtistsFromKeywordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

interface HomeViewModel {
    val state: StateFlow<HomeState>
    fun searchWithKeyword()
    fun onSearchInputChange(keyword: String)
}

@HiltViewModel
class HomeViewModelImpl @Inject constructor(
    private val dispatchers: CoroutineDispatcherProvider,
    private val getArtistUseCase: GetArtistsFromKeywordUseCase,
) : ViewModel(), HomeViewModel {
    override val state = MutableStateFlow(HomeState())

    init {
        searchWithKeyword()
    }

    override fun searchWithKeyword() {
        if (state.value.keyword.isBlank()) {
            state.update { it.copy(screenState = ScreenState.EMPTY) }
            return
        }
        viewModelScope.launch(dispatchers.IO) {
            state.update { it.copy(screenState = ScreenState.LOADING) }
            val artists = getArtistUseCase(state.value.keyword)
            if (artists.isNullOrEmpty()) {
                val screenState = if (artists == null) ScreenState.ERROR else ScreenState.EMPTY
                state.update { it.copy(allArtists = emptyList(), screenState = screenState) }
            } else {
                state.update { it.copy(allArtists = artists, screenState = ScreenState.NORMAL) }
            }
        }
    }

    override fun onSearchInputChange(keyword: String) {
        state.update {
            it.copy(keyword = keyword.replaceIndentToSpace())
        }
    }

    private fun String.replaceIndentToSpace() = replace("\n", " ")
}
