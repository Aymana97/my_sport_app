package com.example.mysportapp.clients

import com.example.mysportapp.services.FootballPlayerService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object FootballApiClient {

    private const val BASE_URL = "https://v3.football.api-sports.io/"

    private val client = OkHttpClient.Builder()
        .addInterceptor(ClientInterceptor())
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    val footballPlayerService: FootballPlayerService = retrofit.create(FootballPlayerService::class.java)
}