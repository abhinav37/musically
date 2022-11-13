package com.abhinav.musically.designsystem

fun getAlbumImageUrl(id: String) = "http://coverartarchive.org/release-group/$id/front"

fun getArtistImageUrl(id: String) = "http://coverartarchive.org/release/$id/front"

fun getPlaceholderImage(imageNumber: Int): Int = when (imageNumber) {
    1 -> R.drawable.music_1
    2 -> R.drawable.music_2
    3 -> R.drawable.music_3
    4 -> R.drawable.music_4
    5 -> R.drawable.music_5
    6 -> R.drawable.music_6
    7 -> R.drawable.music_7
    8 -> R.drawable.music_8
    9 -> R.drawable.music_9
    10 -> R.drawable.music_10
    11 -> R.drawable.music_11
    12 -> R.drawable.music_12
    13 -> R.drawable.music_13
    14 -> R.drawable.music_14
    15 -> R.drawable.music_15
    else -> R.drawable.music_16
}
