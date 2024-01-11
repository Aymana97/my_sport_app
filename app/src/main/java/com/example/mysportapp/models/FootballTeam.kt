package com.example.mysportapp.models

data class FootballTeam (
    val id: Int,
    val name: String,
    val code: String,
    val country: String,
    val founded: Int,
    val national: Boolean,
    val logo: String
)

data class FootballTeamVenue (
    val id: Int,
    val name: String,
    val address: String,
    val city: String,
    val capacity: Int,
    val surface: String,
    val image: String
)

data class FootballPlayerTeam (
    val id: Int,
    val name: String,
    val logo: String
)