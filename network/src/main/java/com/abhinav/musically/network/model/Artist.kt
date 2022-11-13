package com.abhinav.musically.network.model

import com.google.gson.annotations.SerializedName
import kotlin.random.Random
import kotlinx.serialization.Serializable

@Serializable
data class ArtistListResponse(
    @SerializedName("created") var created: String? = null,
    @SerializedName("count") var count: Int? = null,
    @SerializedName("offset") var offset: Int? = null,
    @SerializedName("artists") var artists: ArrayList<Artist> = arrayListOf(),
)

@Serializable
data class Artist(
    @SerializedName("id") val id: String = "",
    @SerializedName("country") val country: String? = null,
    @SerializedName("gender") val gender: String? = null,
    @SerializedName("release-groups") val albums: List<Album> = arrayListOf(),
    @SerializedName("name") val name: String? = null,
    @SerializedName("life-span") val lifeSpan: LifeSpan? = null,
    @SerializedName("type") val type: String? = null,
    val imageNumber: Int = Random.nextInt(1, 16),
)

@Serializable
data class LifeSpan(
    @SerializedName("end") val end: String? = null,
    @SerializedName("ended") val ended: Boolean? = null,
    @SerializedName("begin") val begin: String? = null,
)

@Serializable
data class Album(
    @SerializedName("first-release-date") val firstReleaseDate: String? = null,
    @SerializedName("title") val title: String? = null,
    @SerializedName("id") val id: String? = null,
)

val EmptyArtist: Artist = Artist()
