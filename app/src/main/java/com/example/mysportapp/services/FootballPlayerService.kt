package com.example.mysportapp.services

import com.example.mysportapp.models.FootballLeagueTeamsResponse
import com.example.mysportapp.models.FootballLeaguesResponse
import com.example.mysportapp.models.FootballPlayerResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FootballPlayerService {
    @GET("players")
    suspend fun getPlayers(@Query(value = "team") team: Int, @Query(value = "season") season: Int) : FootballPlayerResponse

    @GET("players")
    suspend fun getPlayer(@Query(value = "id") id: Int, @Query(value = "season") season: Int) : FootballPlayerResponse

    @GET("leagues")
    suspend fun getLeagues(@Query(value = "type") type: String = "league") : FootballLeaguesResponse

    @GET("teams")
    suspend fun getTeams(@Query(value = "league") league: Int, @Query(value = "season") season: Int) : FootballLeagueTeamsResponse
}