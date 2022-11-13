package com.abhinav.musically.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.abhinav.musically.designsystem.R
import com.abhinav.musically.designsystem.getAlbumImageUrl
import com.abhinav.musically.designsystem.theme.DSColors
import com.abhinav.musically.designsystem.theme.DSTypography
import com.valentinilk.shimmer.ShimmerBounds
import com.valentinilk.shimmer.rememberShimmer
import com.valentinilk.shimmer.shimmer

@Composable
fun AlbumContent(
    modifier: Modifier = Modifier,
    albumId: String = "",
    albumName: String = stringResource(id = R.string.unknown),
    albumFirstReleaseDate: String = stringResource(id = R.string.unknown),
) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        AsyncImage(
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(48.dp),
            model = getAlbumImageUrl(albumId),
            placeholder = painterResource(id = R.drawable.music_1),
            error = painterResource(id = R.drawable.music_1),
            contentDescription = stringResource(id = R.string.image))
        Column(modifier = Modifier.padding(vertical = 12.dp, horizontal = 16.dp)) {
            Text(
                text = albumName,
                style = DSTypography.headline,
                color = DSColors.primaryText,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = stringResource(id = R.string.release_date, albumFirstReleaseDate),
                style = DSTypography.body2Regular,
                color = DSColors.secondaryText,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )
        }
    }
}

@Composable
fun LoadingAlbumContent(modifier: Modifier = Modifier) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        val shimmerInstance = rememberShimmer(shimmerBounds = ShimmerBounds.Window)
        Box(
            modifier = Modifier
                .size(48.dp)
                .shimmer(shimmerInstance)
                .background(DSColors.primaryForegroundText)
        )
        Column(modifier = Modifier.padding(vertical = 12.dp, horizontal = 16.dp)) {
            Box(
                modifier = Modifier
                    .width(96.dp)
                    .height(20.dp)
                    .padding(bottom = 2.dp)
                    .shimmer(shimmerInstance)
                    .background(DSColors.primaryForegroundText)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(28.dp)
                    .padding(bottom = 8.dp)
                    .shimmer(shimmerInstance)
                    .background(DSColors.primaryForegroundText)
            )
        }
    }
}

@Preview
@Composable
private fun PreviewAlbumListContent(count: Int = 4) {
    val items = List(count) { it }
    LazyColumn {
        itemsIndexed(items) { _, _ ->
            AlbumContent()
        }
    }
}

@Preview
@Composable
fun LoadingAlbumListContent(modifier: Modifier = Modifier, count: Int = 2) {
    val items = List(count) { it }
    LazyColumn(modifier) {
        item {
            Text(
                text = stringResource(id = R.string.albums_by_artist),
                style = DSTypography.headline,
                color = DSColors.primaryText
            )
        }
        itemsIndexed(items) { _, _ ->
            LoadingAlbumContent()
        }
    }
}
