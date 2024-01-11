package com.example.mysportapp.compose.nbaplayerdetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mysportapp.compose.PlayerCard
import com.example.mysportapp.viewmodels.NBAPlayerViewModel
import com.example.mysportapp.viewmodels.NBAPlayerViewModelFactory

@Composable
fun NBAPlayerDetailsScreen(
    playerId: Int,
    onBackClick: () -> Unit,
    viewModel: NBAPlayerViewModel = viewModel(factory = NBAPlayerViewModelFactory(playerId = playerId))
) {
    val player = viewModel.player
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                title = {
                    Row {
                        Text("${player?.firstname ?: ""} ${player?.lastname ?: ""}")
                    }
                }
            )
        },
        content = {
            if (viewModel.errorMessage.isEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(16.dp)
                ) {
                    if (player != null) {
                        PlayerCard(
                            id = player.id,
                            name = "${player.firstname} - ${player.lastname}",
                            photo = null
                        )
                    }
                }
            } else {
                Text(viewModel.errorMessage)
            }
        }
    )
}