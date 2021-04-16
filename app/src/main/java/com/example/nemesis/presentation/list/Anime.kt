package com.example.nemesis.presentation.list

data class Anime(
    val mal_id: Int,
    val url: String,
    val title: String,
    val image_url: String,
    val synopsis: String,
    val type: String,
    val airing_start: String,
    val episodes: Int,
    val source: String,
    val score: Double

)