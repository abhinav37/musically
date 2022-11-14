package com.abhinav.musically.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.abhinav.musically.designsystem.R
import com.abhinav.musically.designsystem.getArtistImageUrl
import com.abhinav.musically.designsystem.getPlaceholderImage
import com.abhinav.musically.designsystem.theme.DSColors
import com.abhinav.musically.designsystem.theme.DSTypography
import com.valentinilk.shimmer.Shimmer
import com.valentinilk.shimmer.shimmer

// Reuseable Components

@Composable
fun ArtistContentWithThumbnail(
    modifier: Modifier = Modifier,
    artistId: String = "",
    artistName: String = stringResource(id = R.string.unknown),
    artistType: String = stringResource(id = R.string.unknown),
    artistLifeSpanStart: String = stringResource(id = R.string.unknown),
    artistLifeSpanEnd: String = stringResource(id = R.string.ongoing),
    artistPlaceHolderImageNum: Int = 1,
    onItemClick: () -> Unit = {},
) {
    Column(
        modifier = modifier
            .clickable(onClick = onItemClick)
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
    ) {
        Row(modifier = Modifier.padding(vertical = 12.dp)) {
            AsyncImage(
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(48.dp),
                model = getArtistImageUrl(artistId),
                placeholder = painterResource(id = getPlaceholderImage(artistPlaceHolderImageNum)),
                error = painterResource(id = getPlaceholderImage(artistPlaceHolderImageNum)),
                contentDescription = stringResource(id = R.string.image))
            ArtistContent(
                modifier = Modifier.padding(start = 12.dp, end = 16.dp),
                artistName = artistName,
                artistType = artistType,
                artistLifeSpanStart = artistLifeSpanStart,
                artistLifeSpanEnd = artistLifeSpanEnd,
            )
        }
        Divider(thickness = 1.dp, color = DSColors.border)
    }
}

@Composable
fun ArtistContent(
    modifier: Modifier = Modifier,
    artistName: String = stringResource(id = R.string.unknown),
    artistType: String = stringResource(id = R.string.unknown),
    artistLifeSpanStart: String = stringResource(id = R.string.unknown),
    artistLifeSpanEnd: String = stringResource(id = R.string.ongoing),
) {
    Column(modifier = modifier) {
        Text(
            text = artistName,
            style = DSTypography.body1Medium,
            color = DSColors.primaryText,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = stringResource(id = R.string.description, artistType),
            style = DSTypography.body2Regular,
            color = DSColors.secondaryText,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )
        Row {
            Column(modifier = Modifier
                .padding(end = 8.dp, top = 2.dp)
                .wrapContentHeight()) {
                Icon(
                    tint = DSColors.primary,
                    painter = painterResource(id = R.drawable.ic_calendar),
                    modifier = Modifier
                        .width(16.dp)
                        .padding(bottom = 8.dp),
                    contentDescription = stringResource(id = R.string.created)
                )

                Icon(
                    tint = DSColors.primary,
                    painter = painterResource(id = R.drawable.ic_exit),
                    modifier = Modifier.size(16.dp),
                    contentDescription = stringResource(id = R.string.ended)
                )
            }
            Column {
                Text(
                    text = stringResource(id = R.string.created),
                    style = DSTypography.body2Medium,
                    color = DSColors.primaryText,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = stringResource(id = R.string.ended),
                    style = DSTypography.body2Medium,
                    color = DSColors.primaryText,
                )
            }
            Column(modifier = Modifier.padding(start = 4.dp)) {
                Text(
                    text = artistLifeSpanStart,
                    style = DSTypography.body2Regular,
                    color = DSColors.primaryText,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = artistLifeSpanEnd,
                    style = DSTypography.body2Regular,
                    color = DSColors.primaryText,
                )
            }
        }
    }
}

@Composable
fun LoadingArtistContentWithThumbnail(shimmer: Shimmer, modifier: Modifier = Modifier) {
    Row(modifier = modifier
        .padding(vertical = 12.dp, horizontal = 16.dp)
        .fillMaxWidth()) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .shimmer(shimmer)
                .background(DSColors.primaryForegroundText)
        )
        LoadingArtistContent(shimmer = shimmer)
    }
}

@Composable
fun LoadingArtistContent(
    shimmer: Shimmer,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.padding(start = 12.dp, end = 16.dp)) {
        Box(
            modifier = Modifier
                .width(96.dp)
                .height(20.dp)
                .padding(bottom = 2.dp)
                .shimmer(shimmer)
                .background(DSColors.primaryForegroundText)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(28.dp)
                .padding(bottom = 8.dp)
                .shimmer(shimmer)
                .background(DSColors.primaryForegroundText)
        )
        Row {
            Column(modifier = Modifier
                .padding(end = 8.dp, top = 2.dp)
                .wrapContentHeight()) {
                Icon(
                    tint = DSColors.primary,
                    painter = painterResource(id = R.drawable.ic_calendar),
                    modifier = Modifier
                        .width(16.dp)
                        .padding(bottom = 8.dp),
                    contentDescription = stringResource(id = R.string.created)
                )

                Icon(
                    tint = DSColors.primary,
                    painter = painterResource(id = R.drawable.ic_exit),
                    modifier = Modifier.size(16.dp),
                    contentDescription = stringResource(id = R.string.ended)
                )
            }
            Column {
                Text(
                    text = stringResource(id = R.string.created),
                    style = DSTypography.body2Medium,
                    color = DSColors.primaryText,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = stringResource(id = R.string.ended),
                    style = DSTypography.body2Medium,
                    color = DSColors.primaryText,
                )
            }
            Column(modifier = Modifier.padding(start = 8.dp)) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(20.dp)
                        .padding(bottom = 0.dp)
                        .shimmer(shimmer)
                        .background(DSColors.primaryForegroundText)
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(28.dp)
                        .padding(top = 8.dp)
                        .shimmer(shimmer)
                        .background(DSColors.primaryForegroundText)
                )
            }
        }
    }
}

@Preview
@Composable
fun ArtistPreview() {
    ArtistContentWithThumbnail()
}
