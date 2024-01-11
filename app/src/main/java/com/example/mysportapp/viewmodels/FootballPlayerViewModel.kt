package com.example.mysportapp.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.*
import com.example.mysportapp.clients.FootballApiClient
import com.example.mysportapp.models.FootballPlayer
import com.example.mysportapp.models.Statistics
import kotlinx.coroutines.launch

class FootballPlayerViewModelFactory(private val playerId: Int) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = FootballPlayerViewModel(playerId) as T
}

class FootballPlayerViewModel(playerId: Int) : ViewModel() {

    private var _player: FootballPlayer? by mutableStateOf(null)
    val player: FootballPlayer?
        get() = _player

    private var _statistics: Statistics? by mutableStateOf(null)
    val statistics: Statistics?
        get() = _statistics

    private var _errorMessage: String by mutableStateOf("")
    val errorMessage: String
        get() = _errorMessage

    init {
        getPlayer(playerId)
    }

    private fun getPlayer(playerId: Int) {
        viewModelScope.launch {
            try {
                val response = FootballApiClient.footballPlayerService.getPlayer(id = playerId, season = 2019).response.first()
                _player = response.player
                _statistics = response.statistics.first()
            } catch (e: Exception) {
                _errorMessage = e.message.toString()
            }
        }
    }
}