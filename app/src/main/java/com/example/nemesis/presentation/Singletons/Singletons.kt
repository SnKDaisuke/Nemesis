package com.example.nemesis.presentation.Singletons

import com.example.nemesis.presentation.api.JikanApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Singletons {
    companion object {
        val jikanApi: JikanApi = Retrofit.Builder()
                .baseUrl("https://api.jikan.moe/v3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(JikanApi::class.java)



    }
}

