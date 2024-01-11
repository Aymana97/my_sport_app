package com.example.mysportapp.compose.footballplayerdetails

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mysportapp.compose.PlayerCard
import com.example.mysportapp.viewmodels.FootballPlayerViewModel
import com.example.mysportapp.viewmodels.FootballPlayerViewModelFactory

@Composable
fun PlayerDetailsScreen(
    playerId: Int,
    onBackClick: () -> Unit,
    footballPlayerViewModel: FootballPlayerViewModel = viewModel(factory = FootballPlayerViewModelFactory(playerId = playerId))
) {
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
                        Text(footballPlayerViewModel.player?.name ?: "")
                    }
                }
            )
        },
        content = {
            if (footballPlayerViewModel.errorMessage.isEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(16.dp)
                ) {
                    if (footballPlayerViewModel.player != null) {
                        PlayerCard(
                            id = footballPlayerViewModel.player!!.id,
                            name = footballPlayerViewModel.player!!.name,
                            photo = footballPlayerViewModel.player!!.photo
                        )
                    }
                    if (footballPlayerViewModel.statistics != null) {
                        PlayerStatisticsCard(statistics = footballPlayerViewModel.statistics!!)
                    }
                }
            } else {
                Text(footballPlayerViewModel.errorMessage)
            }
        }
    )
}