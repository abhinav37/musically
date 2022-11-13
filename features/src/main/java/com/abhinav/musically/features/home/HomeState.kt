package com.abhinav.musically.features.home

import com.abhinav.musically.features.ScreenState
import com.abhinav.musically.network.model.Artist

data class HomeState(
    val allArtists: List<Artist> = emptyList(),
    val keyword: String = "rock & roll",
    val screenState: ScreenState = ScreenState.LOADING,
)
