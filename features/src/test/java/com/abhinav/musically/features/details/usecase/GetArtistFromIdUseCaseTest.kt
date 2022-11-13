package com.abhinav.musically.features.details.usecase

import com.abhinav.musically.network.model.Artist
import com.abhinav.musically.network.model.EmptyArtist
import com.abhinav.musically.network.repository.ArtistRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class GetArtistFromIdUseCaseTest {
    private val fakeArtist = EmptyArtist.copy("222")
    private val artistList = listOf(EmptyArtist, EmptyArtist)
    private val fakeRepository = object : ArtistRepository {
        override suspend fun getArtists(keyword: String): List<Artist> = artistList
        override suspend fun getArtistDataById(id: String): Artist = fakeArtist
    }

    private val useCase = GetArtistFromIdUseCase(fakeRepository)

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `invoke in Success case`() = runTest {
        val result = useCase.invoke("222")
        assertEquals(fakeArtist, result)
    }
}
