package com.abhinav.musically.features.home

import com.abhinav.musically.features.ScreenState
import com.abhinav.musically.features.TestDispatcherProvider
import com.abhinav.musically.features.home.usecase.GetArtistsFromKeywordUseCase
import com.abhinav.musically.network.model.Album
import com.abhinav.musically.network.model.Artist
import com.abhinav.musically.network.model.EmptyArtist
import com.abhinav.musically.network.repository.ArtistRepository
import org.junit.Assert.assertEquals
import org.junit.Test

class HomeViewModelTest {
    private val fakeAlbum = Album()
    private val fakeArtist = EmptyArtist.copy("222", albums = listOf(fakeAlbum, fakeAlbum))
    private val artistList = listOf(EmptyArtist, EmptyArtist)
    private val fakeRepository = object : ArtistRepository {
        override suspend fun getArtists(keyword: String): List<Artist> = artistList
        override suspend fun getArtistDataById(id: String): Artist = fakeArtist
    }
    private val dispatchers = TestDispatcherProvider()
    private val useCase = GetArtistsFromKeywordUseCase(fakeRepository)
    private val viewModel = HomeViewModelImpl(dispatchers, useCase)

    @Test
    fun `on search input change update Keyword`() {
        val verifyKey = "verify"
        viewModel.onSearchInputChange(verifyKey)
        val keyword = viewModel.state.value.keyword
        assertEquals(verifyKey, keyword)
    }

    @Test
    fun `on searchInputChange Keyword should be trimmed of enter char with whitespace`() {
        val key = "verify\n"
        val verifyKey = "verify "
        viewModel.onSearchInputChange(key)
        val keyword = viewModel.state.value.keyword
        assertEquals(verifyKey, keyword)
    }

    @Test
    fun `searchWithKeyword set EMPTY state if keyword is Empty`() {
        viewModel.onSearchInputChange("")
        viewModel.searchWithKeyword()
        val scState = viewModel.state.value.screenState
        assertEquals(ScreenState.EMPTY, scState)
    }

    @Test
    fun `searchWithKeyword set Normal state if artistList is NOT EMPTY OR NULL`() {
        viewModel.searchWithKeyword()
        val scState = viewModel.state.value.screenState
        val artists = viewModel.state.value.allArtists
        assertEquals(scState, ScreenState.NORMAL)
        assertEquals(artistList, artists)
    }

    @Test
    fun `searchWithKeyword set EMPTY state if artistList is Empty`() {
        val fakeRepository = object : ArtistRepository {
            override suspend fun getArtists(keyword: String): List<Artist> = emptyList()
            override suspend fun getArtistDataById(id: String): Artist = EmptyArtist
        }
        val vm = HomeViewModelImpl(dispatchers, GetArtistsFromKeywordUseCase(fakeRepository))
        vm.searchWithKeyword()
        val scState = vm.state.value.screenState
        assertEquals(ScreenState.EMPTY, scState)
    }

    @Test
    fun `searchWithKeyword set ERROR state if artistList is Null`() {
        val fakeRepository = object : ArtistRepository {
            override suspend fun getArtists(keyword: String): List<Artist>? = null
            override suspend fun getArtistDataById(id: String): Artist = EmptyArtist
        }
        val vm = HomeViewModelImpl(dispatchers, GetArtistsFromKeywordUseCase(fakeRepository))
        vm.searchWithKeyword()
        val scState = vm.state.value.screenState
        assertEquals(ScreenState.ERROR, scState)
    }
}
