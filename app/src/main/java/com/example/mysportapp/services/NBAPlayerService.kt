package com.example.mysportapp.services

import com.example.mysportapp.models.NBAPlayerResponse
import com.example.mysportapp.models.NBATeamPlayersResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NBAPlayerService {

    @GET("players")
    suspend fun getPlayers(
        @Query(value = "team") team: Int,
        @Query(value = "season") season: Int
    ) : NBATeamPlayersResponse

    @GET("players")
    suspend fun getPlayer(
        @Query(value = "id") id: Int,
    ) : NBAPlayerResponse

}