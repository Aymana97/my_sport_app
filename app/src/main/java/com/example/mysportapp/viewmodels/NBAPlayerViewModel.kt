package com.example.mysportapp.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.*
import com.example.mysportapp.clients.NBAApiClient
import com.example.mysportapp.models.NBAPlayer
import kotlinx.coroutines.launch

class NBAPlayerViewModelFactory(private val playerId: Int) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = NBAPlayerViewModel(playerId) as T
}

class NBAPlayerViewModel(playerId: Int) : ViewModel() {

    private var _player: NBAPlayer? by mutableStateOf(null)
    val player: NBAPlayer?
        get() = _player

    private var _errorMessage: String by mutableStateOf("")
    val errorMessage: String
        get() = _errorMessage

    init {
        getPlayer(playerId)
    }

    private fun getPlayer(playerId: Int) {
        viewModelScope.launch {
            try {
                _player = NBAApiClient.playerService.getPlayer(id = playerId).response.first()
            } catch (e: Exception) {
                _errorMessage = e.message.toString()
            }
        }
    }
}