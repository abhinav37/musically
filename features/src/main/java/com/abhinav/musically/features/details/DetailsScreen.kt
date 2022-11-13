package com.abhinav.musically.features.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.abhinav.musically.features.R
import com.abhinav.musically.designsystem.components.AlbumContent
import com.abhinav.musically.designsystem.components.ArtistContent
import com.abhinav.musically.designsystem.components.BasicButton
import com.abhinav.musically.designsystem.components.LoadingAlbumListContent
import com.abhinav.musically.designsystem.components.LoadingArtistContent
import com.abhinav.musically.designsystem.getArtistImageUrl
import com.abhinav.musically.designsystem.getPlaceholderImage
import com.abhinav.musically.designsystem.theme.DSColors
import com.abhinav.musically.designsystem.theme.DSTypography
import com.abhinav.musically.features.ScreenState.EMPTY
import com.abhinav.musically.features.ScreenState.ERROR
import com.abhinav.musically.features.ScreenState.LOADING
import com.abhinav.musically.features.ScreenState.NORMAL
import com.abhinav.musically.network.model.Album
import com.abhinav.musically.network.model.Artist
import com.valentinilk.shimmer.ShimmerBounds
import com.valentinilk.shimmer.rememberShimmer
import com.valentinilk.shimmer.shimmer

@Composable
fun DetailsScreen(onBackClick: () -> Unit, viewModel: DetailsViewModel) {
    val state by viewModel.state.collectAsState()
    Scaffold(topBar = {
        Row(modifier = Modifier.padding(16.dp)) {
            IconButton(onClick = onBackClick, modifier = Modifier.size(24.dp)) {
                Icon(
                    Icons.Rounded.ArrowBack,
                    contentDescription = stringResource(R.string.back),
                    tint = DSColors.primaryText)
            }
            Text(
                modifier = Modifier.padding(start = 20.dp),
                text = stringResource(id = R.string.details_title, state.artist.name ?: ""),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = DSTypography.body1Medium,
                color = DSColors.primaryText
            )
        }
    }, bottomBar = {
        if (state.screenState == ERROR) {
            BasicButton(onClick = {
                viewModel.loadData()
            },
                text = stringResource(R.string.reload_from_server),
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
    }) { rootPadding ->
        when (state.screenState) {
            LOADING -> DetailScreenLoading(modifier = Modifier.padding(rootPadding))
            NORMAL -> DetailScreenWithData(
                artist = state.artist,
                modifier = Modifier.padding(rootPadding)
            )
            ERROR, EMPTY -> Column(modifier = Modifier
                .fillMaxHeight()
                .padding(rootPadding),
                verticalArrangement = Arrangement.Center) {
                Text(
                    modifier = Modifier.padding(start = 20.dp),
                    text = stringResource(id = R.string.error_text),
                    style = DSTypography.body1Medium,
                    color = DSColors.primaryText
                )
            }
        }

    }
}

@Composable
fun DetailScreenWithData(artist: Artist, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        AsyncImage(
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth()
                .sizeIn(maxHeight = 100.dp),
            model = getArtistImageUrl(artist.id),
            placeholder = painterResource(id = getPlaceholderImage(artist.imageNumber)),
            error = painterResource(id = getPlaceholderImage(artist.imageNumber)),
            contentDescription = stringResource(id = R.string.image))
        ArtistContent(
            modifier = Modifier.padding(vertical = 24.dp, horizontal = 16.dp),
            artistName = artist.name ?: stringResource(id = R.string.unknown),
            artistType = artist.type ?: stringResource(id = R.string.unknown),
            artistLifeSpanStart = artist.lifeSpan?.begin ?: stringResource(id = R.string.unknown),
            artistLifeSpanEnd = artist.lifeSpan?.end ?: stringResource(id = R.string.ongoing),
        )
        AlbumListContent(
            modifier = Modifier.padding(vertical = 24.dp, horizontal = 16.dp),
            albums = artist.albums,
        )
    }
}

@Composable
fun DetailScreenLoading(modifier: Modifier = Modifier) {
    val shimmerInstance = rememberShimmer(shimmerBounds = ShimmerBounds.Window)
    Column(modifier = modifier) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .shimmer(shimmerInstance)
                .background(DSColors.primaryForegroundText)
        )
        LoadingArtistContent(
            modifier = Modifier.padding(vertical = 24.dp),
        )
        LoadingAlbumListContent(
            modifier = Modifier.padding(vertical = 24.dp, horizontal = 16.dp),
        )
    }
}

@Composable
fun AlbumListContent(albums: List<Album>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        item {
            Text(
                text = stringResource(id = R.string.albums_by_artist),
                style = DSTypography.headline,
                color = DSColors.primaryText
            )
        }
        itemsIndexed(albums,
            key = { _, album -> album.id + album.hashCode() }) { _, album ->
            AlbumContent(
                albumId = album.id ?: "",
                albumName = album.title ?: stringResource(id = R.string.unknown),
                albumFirstReleaseDate = album.firstReleaseDate
                    ?: stringResource(id = R.string.unknown),
            )
        }
        if (albums.isEmpty()) {
            item {
                Text(
                    text = stringResource(id = com.abhinav.musically.designsystem.R.string.no_albums_found),
                    style = DSTypography.body2Regular,
                    color = DSColors.secondaryText
                )
            }
        }
    }
}
