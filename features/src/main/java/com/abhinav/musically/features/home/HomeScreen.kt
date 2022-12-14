package com.abhinav.musically.features.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.abhinav.musically.designsystem.components.ArtistContentWithThumbnail
import com.abhinav.musically.designsystem.components.BasicButton
import com.abhinav.musically.designsystem.components.LoadingArtistContentWithThumbnail
import com.abhinav.musically.designsystem.components.SearchInput
import com.abhinav.musically.designsystem.theme.DSColors
import com.abhinav.musically.designsystem.theme.DSTypography
import com.abhinav.musically.features.R
import com.abhinav.musically.features.ScreenState.EMPTY
import com.abhinav.musically.features.ScreenState.ERROR
import com.abhinav.musically.features.ScreenState.LOADING
import com.abhinav.musically.features.ScreenState.NORMAL
import com.abhinav.musically.network.model.Artist
import com.valentinilk.shimmer.ShimmerBounds
import com.valentinilk.shimmer.rememberShimmer

@Composable
fun HomeScreen(onItemClick: (String) -> Unit, viewModel: HomeViewModel) {
    val state by viewModel.state.collectAsState()
    Scaffold(topBar = {
        Surface(elevation = 4.dp, color = DSColors.primaryBackground) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    modifier = Modifier.padding(bottom = 16.dp),
                    text = stringResource(R.string.app_name),
                    style = DSTypography.headline,
                    color = DSColors.primaryText
                )
                SearchInput(
                    value = state.keyword,
                    onValueChange = viewModel::onSearchInputChange,
                    onClearIconClick = {
                        viewModel.onSearchInputChange("")
                    },
                    placeholder = stringResource(R.string.search_by_keyword),
                    showLeadingIcon = true,
                    onSearch = { viewModel.searchWithKeyword() },
                )
            }
        }
    }) { rootPadding ->
        Column(
            modifier = Modifier
                .padding(rootPadding)
                .fillMaxSize(),
        ) {
            when (state.screenState) {
                LOADING -> LoadingArtistListContent(10)
                ERROR -> ErrorScreen(viewModel::searchWithKeyword)
                NORMAL -> ArtistListContent(state.allArtists, onItemClick)
                EMPTY -> Text(
                    modifier = Modifier.padding(start = 20.dp),
                    text = stringResource(R.string.no_results_found),
                    style = DSTypography.body1Medium,
                    color = DSColors.primaryText
                )
            }
        }
    }
}

@Composable
fun ErrorScreen(reload: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
        BasicButton(
            text = stringResource(id = R.string.reload_from_server),
            onClick = reload,
        )
    }
}

@Composable
fun ArtistListContent(artists: List<Artist>, onItemClick: (String) -> Unit) {
    LazyColumn {
        itemsIndexed(artists, key = { _, item -> item.id + item.hashCode() }) { _, artist ->
            ArtistContentWithThumbnail(
                artistId = artist.id,
                artistName = artist.name ?: stringResource(id = R.string.unknown),
                artistType = artist.type ?: stringResource(id = R.string.unknown),
                artistLifeSpanStart = artist.lifeSpan?.begin
                    ?: stringResource(id = R.string.unknown),
                artistLifeSpanEnd = artist.lifeSpan?.end ?: stringResource(id = R.string.ongoing),
                artistPlaceHolderImageNum = artist.imageNumber,
                onItemClick = { onItemClick(artist.id) })
        }
    }
}

@Preview
@Composable
fun LoadingArtistListContent() {
    LoadingArtistListContent(count = 4)
}

@Composable
fun LoadingArtistListContent(count: Int) {
    val items = List(count) { it }
    val shimmer = rememberShimmer(shimmerBounds = ShimmerBounds.Window)
    LazyColumn {
        itemsIndexed(items) { _, _ ->
            LoadingArtistContentWithThumbnail(shimmer = shimmer)
        }
    }
}
