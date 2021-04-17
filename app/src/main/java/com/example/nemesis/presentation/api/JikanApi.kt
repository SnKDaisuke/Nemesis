package com.example.nemesis.presentation.api

import com.google.firebase.database.core.Repo
import retrofit2.Call

import retrofit2.http.GET
import retrofit2.http.Path


interface JikanApi {

    @GET("season/later")
    fun getAnimeSeasonLater(): Call<AnimeLaterResponse>

    @GET("anime/{id}")
    fun getAnimeDetails(@Path("id") id: Int): Call<AnimeDetailsResponse>
}