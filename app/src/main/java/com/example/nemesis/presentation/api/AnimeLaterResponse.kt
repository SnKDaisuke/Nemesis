package com.example.nemesis.presentation.api

import com.example.nemesis.presentation.list.Anime

data class AnimeLaterResponse(
    val season_name : String,
    val season_year : String,
    val anime : List<Anime>

)