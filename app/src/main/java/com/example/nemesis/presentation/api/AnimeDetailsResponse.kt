package com.example.nemesis.presentation.api

data class AnimeDetailsResponse(
    val mal_id: Int,
    val url: String,
    val image_url: String,
    val trailer_url: String,
    val title: String,
    val title_english: String,
    val title_japanese: String,
    val type: String,
    val source: String,
    val status: String,
    val airing: Boolean,
    val synopsis: String,
    val premiered: String
)