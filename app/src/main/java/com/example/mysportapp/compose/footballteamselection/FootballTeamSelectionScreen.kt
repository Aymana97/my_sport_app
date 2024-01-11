package com.example.mysportapp.compose.footballteamselection

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mysportapp.models.*
import com.example.mysportapp.viewmodels.FootballTeamSelectionViewModel

@Composable
fun FootballTeamSelectionScreen(
    onBackClick: () -> Unit,
    onButtonclick: (teamId: Int, season: Int) -> Unit,
    viewModel: FootballTeamSelectionViewModel = viewModel()
) {
    val leagues: List<FootballLeague> = viewModel.leagues
    val teams: List<FootballLeagueTeam> = viewModel.teams

    var league: FootballLeague? by remember {
        mutableStateOf(null)
    }

    var seasons: List<FootballSeason> by remember {
        mutableStateOf(mutableStateListOf())
    }

    var season: FootballSeason? by remember {
        mutableStateOf(null)
    }

    var team: FootballLeagueTeam? by remember {
        mutableStateOf(null)
    }

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
                        Text("Team selection")
                    }
                }
            )
        },
        content = {

            if (viewModel.errorMessage.isEmpty()) {
                Column (
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxHeight()
                ) {

                    DropDownButton(
                        buttonToString = { league -> league?.league?.name ?: "Select a league" },
                        elementToString = { league -> "${league.league.id} - ${league.league.name}" },
                        elements = leagues,
                        onclick = { l -> league = l; seasons = l.seasons; }
                    )

                    DropDownButton(
                        visible = league != null,
                        buttonToString = { season -> season?.year?.toString() ?: "Select a season" },
                        elementToString = { season -> season.year.toString() },
                        elements = seasons,
                        onclick = { s -> season = s; viewModel.getTeams(league!!.league.id, s.year) }
                    )

                    DropDownButton(
                        visible = league != null && season != null,
                        buttonToString = { team -> team?.team?.name ?: "Select a team" },
                        elementToString = { team -> "${team.team.code} - ${team.team.name}" },
                        elements = teams,
                        onclick = { t -> team = t }
                    )

                    if (team != null && season != null) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 8.dp),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Button(
                                onClick = { onButtonclick(team!!.team.id, season!!.year) },
                                contentPadding = PaddingValues(10.dp),
                            ) {
                                Text(text = "Show team players")
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

@Composable
fun<T> DropDownButton(
    visible: Boolean = true,
    buttonToString: (T?) -> String,
    elementToString: (T) -> String,
    elements: List<T>,
    onclick: (T) -> Unit
) {
    var isExpanded: Boolean by remember {
        mutableStateOf(false)
    }

    var observedValue: T? by remember {
        mutableStateOf<T?>(null)
    }

    if (visible) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = { isExpanded = !isExpanded },
                contentPadding = PaddingValues(10.dp),
            ) {
                Text(buttonToString(observedValue))
            }
            DropdownMenu(
                expanded = isExpanded,
                onDismissRequest = { isExpanded = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                elements.forEach() { e ->
                    DropdownMenuItem(
                        onClick = {
                            observedValue = e
                            isExpanded = false
                            onclick(e)
                        }
                    ) {
                        Text(text = elementToString(e))
                    }
                }
            }
        }
    }
}