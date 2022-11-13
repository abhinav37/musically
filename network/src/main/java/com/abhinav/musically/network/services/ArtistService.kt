package com.abhinav.musically.network.services

import com.abhinav.musically.network.model.Artist
import com.abhinav.musically.network.model.ArtistListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ArtistService {
    @GET("artist/?fmt=json")
    suspend fun getArtists(
        @Query("query")
        keyword: String,
        @Query("limit")
        limit: Int = 30,
        @Query("offset")
        offset: Int = 0,
    ): ArtistListResponse

    @GET("artist/{artist_id}?inc=release-groups&fmt=json&type=album|ep")
    suspend fun getArtistWithAlbums(
        @Path(value = "artist_id", encoded = true)
        artistId: String,
    ): Artist
}

