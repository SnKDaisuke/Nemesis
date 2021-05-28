package com.example.nemesis.presentation.api

import retrofit2.Call

import retrofit2.http.GET
import retrofit2.http.Path


interface JikanApi {

    @GET("season")
    fun getAnimeSeasonNow(): Call<AnimeResponse>

    @GET("anime/{id}")
    fun getAnimeDetails(@Path("id") id: Int): Call<AnimeDetailsResponse>

    @GET("season/later")
    fun getAnimeUpcomming(): Call<AnimeResponse>

}