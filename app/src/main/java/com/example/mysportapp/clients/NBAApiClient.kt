package com.example.mysportapp.clients

import com.example.mysportapp.services.NBAPlayerService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NBAApiClient {

    private val BASE_URL = "https://v2.nba.api-sports.io/"

    private val client = OkHttpClient.Builder()
        .addInterceptor(ClientInterceptor())
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    val playerService: NBAPlayerService = retrofit.create(NBAPlayerService::class.java)
}