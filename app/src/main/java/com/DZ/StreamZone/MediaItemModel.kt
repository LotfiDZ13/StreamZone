package com.DZ.StreamZone

enum class MediaType { LIVE, MOVIE, SERIES }

data class MediaItemModel(
    val id: String,
    val title: String,
    val streamUrl: String,
    val category: String,
    val type: MediaType
)