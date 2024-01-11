package com.example.mysportapp.compose.footballplayerslist

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.mysportapp.models.FootBallTeamLigth
import com.example.mysportapp.models.FootballPlayerStats
import com.example.mysportapp.viewmodels.FootballTeamPlayersViewModel
import com.example.mysportapp.viewmodels.FootballTeamPlayersViewModelFactory

@Composable
fun PlayersListScreen(
    teamId: Int,
    season: Int,
    onBackClick: () -> Unit,
    onPlayerClick: (playerId: Int) -> Unit,
    viewModel: FootballTeamPlayersViewModel = viewModel(factory = FootballTeamPlayersViewModelFactory(teamId = teamId, season = season))
) {
    val players: List<FootballPlayerStats> = viewModel.players
    val team: FootBallTeamLigth? = viewModel.team
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
                    if (team != null) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(10.dp)
                        ) {
                            Image(
                                painter = rememberAsyncImagePainter(model = team.logo),
                                contentDescription = null
                            )
                            Text(" - ${team.name}")
                        }
                    }
                }
            )
        },
        content = {
            if (viewModel.errorMessage.isEmpty()) {
                Column (
                    modifier = Modifier
                        .padding(16.dp),
                ) {
                    Text(text = "${players.size} players")
                    LazyColumn(modifier = Modifier.fillMaxHeight()) {
                        items(players) { player ->
                            Column {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(6.dp),
                                ) {
                                    Card(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clickable { onPlayerClick(player.player.id) }
                                    ) {
                                        Text(
                                            player.player.name,
                                            maxLines = 1,
                                            modifier = Modifier.padding(16.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                Text(viewModel.errorMessage)
            }
        }
    )
}