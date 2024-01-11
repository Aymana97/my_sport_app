package com.example.mysportapp.models

data class Statistics(
    val team: FootBallTeamLigth,
    val games: Games,
)

data class FootBallTeamLigth(
    val id: Int,
    val name: String,
    val logo: String
)

data class Games(
    val appearences: Int,
    val lineups: Int,
    val minutes: Int,
    val number: Int?,
    val position: String,
    val rating: Float,
    val captain: Boolean
)
