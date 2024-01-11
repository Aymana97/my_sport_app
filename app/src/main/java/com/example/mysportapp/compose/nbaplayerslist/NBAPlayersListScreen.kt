package com.example.mysportapp.compose.nbaplayerslist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mysportapp.viewmodels.NBATeamPlayersViewModel

@Composable
fun NBAPlayersListScreen(
    onBackClick: () -> Unit,
    onPlayerClick: (playerId: Int) -> Unit,
    viewModel: NBATeamPlayersViewModel = viewModel()
) {
    val players = viewModel.players
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
                        Text("Players")
                    }
                }
            )
        }
    ) {
        if (viewModel.errorMessage.isEmpty()) {
            Column(
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
                                        .clickable { onPlayerClick(player.id) }
                                ) {
                                    Text(
                                        "${player.firstname} - ${player.lastname}",
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
}