package com.example.mysportapp.models

data class FootballPlayerResponse (
    val response: List<FootballPlayerStats>
)

data class FootballLeaguesResponse (
    val response: List<FootballLeague>
)

data class FootballLeagueTeamsResponse (
    val response: List<FootballLeagueTeam>
)

data class NBATeamPlayersResponse (
    val response: List<NBAPlayer>
)

data class NBAPlayerResponse (
    val response: List<NBAPlayer>
)