package com.abhinav.musically.network.repository

import com.abhinav.musically.network.model.Artist
import com.abhinav.musically.network.model.EmptyArtist
import com.abhinav.musically.network.services.ArtistService
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

interface ArtistRepository {
    suspend fun getArtists(keyword: String): List<Artist>?
    suspend fun getArtistDataById(id: String): Artist
}

class ArtistRepositoryImpl @Inject constructor(
    private val artistService: ArtistService,
) : ArtistRepository {
    override suspend fun getArtists(keyword: String): List<Artist>? = try {
        artistService.getArtists(keyword = "\"$keyword\"").artists
    } catch (e: Exception) {
        if (e is CancellationException) {
            throw e
        }
        null
    }

    override suspend fun getArtistDataById(id: String): Artist =
        try {
            artistService.getArtistWithAlbums(id)
        } catch (e: Exception) {
            if (e is CancellationException) {
                throw e
            }
            EmptyArtist
        }
}
