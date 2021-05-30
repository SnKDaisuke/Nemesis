package com.example.nemesis.presentation.api

data class AnimeStorageDB(
    val mal_id: Int,
    val url: String,
    val image_url: String,
    val title: String,
    val type: String,
    val status: String,
    val airing: Boolean,
    val synopsis: String
)