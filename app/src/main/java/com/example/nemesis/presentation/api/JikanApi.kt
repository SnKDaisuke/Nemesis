package com.example.nemesis.presentation.api

import com.google.firebase.database.core.Repo
import retrofit2.Call

import retrofit2.http.GET




interface JikanApi {

    @GET("later")
    fun getAnimeSeasonLater(): Call<AnimeLaterResponse>
}