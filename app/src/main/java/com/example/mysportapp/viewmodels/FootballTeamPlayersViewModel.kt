package com.example.mysportapp.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.mysportapp.clients.FootballApiClient
import com.example.mysportapp.models.FootballPlayerStats
import com.example.mysportapp.models.FootBallTeamLigth
import kotlinx.coroutines.launch

class FootballTeamPlayersViewModelFactory(private val teamId: Int, private val season: Int) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = FootballTeamPlayersViewModel(teamId, season) as T
}

class FootballTeamPlayersViewModel(teamId: Int, season: Int) : ViewModel() {

    private var _players: List<FootballPlayerStats> by mutableStateOf(mutableStateListOf())
    val players: List<FootballPlayerStats>
        get() = _players

    private var _team: FootBallTeamLigth? by mutableStateOf(null)
    val team: FootBallTeamLigth?
        get() = _team

    private var _errorMessage: String by mutableStateOf("")
    val errorMessage: String
        get() = _errorMessage

    init {
        getPlayers(teamId, season)
    }

    private fun getPlayers(teamId: Int, season: Int) {
        viewModelScope.launch {
            try {
                val response = FootballApiClient.footballPlayerService.getPlayers(team = teamId, season = season).response
                _players = response
                _team = response.first().statistics.first().team
            } catch (e: Exception) {
                _errorMessage = e.message.toString()
            }
        }
    }
}