package com.example.mysportapp.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mysportapp.clients.FootballApiClient
import com.example.mysportapp.models.FootballLeague
import com.example.mysportapp.models.FootballLeagueTeam
import kotlinx.coroutines.launch

class FootballTeamSelectionViewModel: ViewModel() {

    private var _leagues: List<FootballLeague> by mutableStateOf(mutableStateListOf())
    val leagues: List<FootballLeague>
        get() = _leagues

    private var _teams: List<FootballLeagueTeam> by mutableStateOf(mutableStateListOf())
    val teams: List<FootballLeagueTeam>
        get() = _teams

    private var _errorMessage: String by mutableStateOf("")
    val errorMessage: String
        get() = _errorMessage

    init {
        getLeagues()
    }

    private fun getLeagues() {
        viewModelScope.launch {
            try {
                _leagues = FootballApiClient.footballPlayerService.getLeagues().response
            } catch (e: Exception) {
                _errorMessage = e.message.toString()
            }
        }
    }

    fun getTeams(leagueId: Int, season: Int) {
        viewModelScope.launch {
            try {
                _teams = FootballApiClient.footballPlayerService.getTeams(league = leagueId, season = season).response
            } catch (e: Exception) {
                _errorMessage = e.message.toString()
            }
        }
    }
}