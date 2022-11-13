package com.abhinav.musically.features.home.usecase

import com.abhinav.musically.network.repository.ArtistRepository
import com.abhinav.musically.network.model.Artist
import javax.inject.Inject

open class GetArtistsFromKeywordUseCase @Inject constructor(private val artistRepository: ArtistRepository) {
    suspend operator fun invoke(keyword: String): List<Artist>? =
        artistRepository.getArtists(keyword)
}
