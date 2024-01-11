package com.example.mysportapp.models

data class FootballLeague(
    val league: FootballLeagueInfo,
    val country: Country,
    val seasons: List<FootballSeason>
)

data class FootballLeagueInfo(
    val id: Int,
    val name: String,
    val type: String,
    val logo: String
)
