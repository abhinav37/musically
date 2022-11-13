package com.abhinav.musically.network

import com.abhinav.musically.network.model.Artist
import com.abhinav.musically.network.model.ArtistListResponse
import com.abhinav.musically.network.model.EmptyArtist
import com.abhinav.musically.network.repository.ArtistRepositoryImpl
import com.abhinav.musically.network.services.ArtistService
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class ArtistRepositoryTest {

    private val testArtist = Artist("22")
    private val artistList = arrayListOf(EmptyArtist, EmptyArtist)
    private val response = ArtistListResponse(artists = artistList)

    @Test
    fun `getArtists returns list of artists`() = runBlocking {
        val service = object : ArtistService {
            override suspend fun getArtists(keyword: String, limit: Int, offset: Int) = response
            override suspend fun getArtistWithAlbums(artistId: String) = Artist()
        }
        val repository = ArtistRepositoryImpl(service)
        val list = repository.getArtists("any")
        assertEquals(artistList, list)
    }

    @Test
    fun `getArtists on error returns null`() = runBlocking {
        val service = object : ArtistService {
            override suspend fun getArtists(keyword: String, limit: Int, offset: Int): Nothing =
                run { throw Exception() }

            override suspend fun getArtistWithAlbums(artistId: String) = Artist()
        }
        val repository = ArtistRepositoryImpl(service)
        val list = repository.getArtists("any")
        assertNull(list)
    }

    @Test
    fun `getArtistDataById returns Artists`() = runBlocking {
        val service = object : ArtistService {
            override suspend fun getArtists(keyword: String, limit: Int, offset: Int) = response
            override suspend fun getArtistWithAlbums(artistId: String) = testArtist
        }
        val repository = ArtistRepositoryImpl(service)
        val artist = repository.getArtistDataById("any")
        assertEquals(testArtist, artist)
    }

    @Test
    fun `getArtistDataById on error returns EmptyArtist`() = runBlocking {
        val service = object : ArtistService {
            override suspend fun getArtists(keyword: String, limit: Int, offset: Int) = response
            override suspend fun getArtistWithAlbums(artistId: String): Nothing =
                run { throw Exception() }
        }
        val repository = ArtistRepositoryImpl(service)
        val artist = repository.getArtistDataById("any")
        assertEquals(EmptyArtist, artist)
    }
}
