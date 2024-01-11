package com.example.mysportapp.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mysportapp.clients.NBAApiClient
import com.example.mysportapp.models.NBAPlayer
import kotlinx.coroutines.launch

class NBATeamPlayersViewModel : ViewModel() {

    private var _players: List<NBAPlayer> by mutableStateOf(mutableStateListOf())
    val players: List<NBAPlayer>
        get() = _players

    private var _errorMessage: String by mutableStateOf("")
    val errorMessage: String
        get() = _errorMessage

    init {
        getPlayers()
    }

    private fun getPlayers() {
        viewModelScope.launch {
            try {
                val response = NBAApiClient.playerService.getPlayers(team = 1, season = 2023)
                _players = response.response
            } catch (e: Exception) {
                _errorMessage = e.message.toString()
            }
        }
    }
}