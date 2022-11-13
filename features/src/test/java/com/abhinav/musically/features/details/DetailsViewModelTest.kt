package com.abhinav.musically.features.details

import androidx.lifecycle.SavedStateHandle
import com.abhinav.musically.common.navigation.Details.ARG_ARTIST_ID
import com.abhinav.musically.features.ScreenState
import com.abhinav.musically.features.TestDispatcherProvider
import com.abhinav.musically.features.details.usecase.GetArtistFromIdUseCase
import com.abhinav.musically.network.model.Album
import com.abhinav.musically.network.model.Artist
import com.abhinav.musically.network.model.EmptyArtist
import com.abhinav.musically.network.repository.ArtistRepository
import org.junit.Assert.assertEquals
import org.junit.Test

class DetailsViewModelTest {
    private val fakeAlbum = Album()
    private val fakeArtist = EmptyArtist.copy("222", albums = listOf(fakeAlbum, fakeAlbum))
    private val artistList = listOf(EmptyArtist, EmptyArtist)
    private val fakeRepository = object : ArtistRepository {
        override suspend fun getArtists(keyword: String): List<Artist> = artistList
        override suspend fun getArtistDataById(id: String): Artist = fakeArtist
    }
    private val dispatchers = TestDispatcherProvider()
    private val useCase = GetArtistFromIdUseCase(fakeRepository)
    private val testArg = "222"
    private val savedStateHandle = SavedStateHandle().apply {
        set(ARG_ARTIST_ID, testArg)
    }
    private val viewModel =
        DetailsViewModelImpl(dispatchers, useCase, savedStateHandle)

    @Test
    fun `check if arguments are correct`() {
        val id = viewModel.state.value.artistId
        assertEquals(testArg, id)
    }

    @Test
    fun `on initilization fetch data from server`() {
        val artist = viewModel.state.value.artist
        assertEquals(fakeArtist, artist)
    }

    @Test
    fun `loadData set Normal state if artist is Not Empty`() {
        val scState = viewModel.state.value.screenState
        val artist = viewModel.state.value.artist
        assertEquals(ScreenState.NORMAL, scState)
        assertEquals(fakeArtist, artist)
    }

    @Test
    fun `loadData set error state if artist is Empty`() {
        val fakeRepository = object : ArtistRepository {
            override suspend fun getArtists(keyword: String): List<Artist> = artistList
            override suspend fun getArtistDataById(id: String): Artist = EmptyArtist
        }
        val vm = DetailsViewModelImpl(dispatchers,
            GetArtistFromIdUseCase(fakeRepository), savedStateHandle)
        val scState = vm.state.value.screenState
        assertEquals(ScreenState.ERROR, scState)
    }
}
