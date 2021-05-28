package com.example.nemesis.presentation.api

import com.example.nemesis.presentation.Singletons.Singletons
import com.example.nemesis.presentation.choice.SeasonChoiceAnimeFragment
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

    @GET("season/{year}/{season}")
    fun getAnimeCustom(@Path("year") year: String, @Path("season") season: String): Call<AnimeResponse>
}

