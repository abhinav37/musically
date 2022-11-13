package com.abhinav.musically.features.details

import com.abhinav.musically.features.ScreenState
import com.abhinav.musically.network.model.Artist
import com.abhinav.musically.network.model.EmptyArtist

data class DetailsState(
    val artist: Artist = EmptyArtist,
    val imageVisibility: Boolean = false,
    val artistId: String,
    val screenState: ScreenState = ScreenState.LOADING,
)
