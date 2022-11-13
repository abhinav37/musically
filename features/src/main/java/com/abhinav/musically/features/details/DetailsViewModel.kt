package com.abhinav.musically.features.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abhinav.musically.common.navigation.Details.ARG_ARTIST_ID
import com.abhinav.musically.common.CoroutineDispatcherProvider
import com.abhinav.musically.features.ScreenState
import com.abhinav.musically.features.details.usecase.GetArtistFromIdUseCase
import com.abhinav.musically.network.model.EmptyArtist
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

interface DetailsViewModel {
    fun loadData()
    val state: StateFlow<DetailsState>
}

@HiltViewModel
class DetailsViewModelImpl @Inject constructor(
    private val dispatchers: CoroutineDispatcherProvider,
    private val getArtistFromIDUseCase: GetArtistFromIdUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel(), DetailsViewModel {

    override val state = MutableStateFlow(
        DetailsState(artistId = savedStateHandle.get<String>(ARG_ARTIST_ID) ?: "")
    )

    init {
        loadData()
    }

    override fun loadData() {
        viewModelScope.launch(dispatchers.IO) {
            state.update { it.copy(screenState = ScreenState.LOADING) }
            val artist = getArtistFromIDUseCase(state.value.artistId)
            if (artist == EmptyArtist) {
                state.update { it.copy(screenState = ScreenState.ERROR) }
            } else {
                state.update { it.copy(artist = artist, screenState = ScreenState.NORMAL) }
            }
        }
    }
}
