package com.abhinav.musically.features.details.usecase

import com.abhinav.musically.network.repository.ArtistRepository
import com.abhinav.musically.network.model.Artist
import javax.inject.Inject

open class GetArtistFromIdUseCase @Inject constructor(private val artistRepository: ArtistRepository) {
    suspend operator fun invoke(id: String): Artist = artistRepository.getArtistDataById(id)
}
