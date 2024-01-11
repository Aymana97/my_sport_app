package com.example.mysportapp.compose.footballplayerdetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mysportapp.models.Games
import com.example.mysportapp.models.Statistics

@Composable
fun PlayerStatisticsCard(statistics: Statistics) {
    Column {
        PlayerGamesCard(games = statistics.games)
    }
}

@Composable
fun PlayerGamesCard(games: Games) {
    Card (
        modifier = Modifier.fillMaxWidth()
            ) {
        Column (
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Position : ${games.position}")
            Text(text = "Number : ${games.number}")
            Text(text = "Captain : ${games.captain}")
            Text(text = "Rating : ${String.format("%.2f", games.rating)}")
            Text(text = "Appearances : ${games.appearences.toString()}")
            Text(text = "Lineups : ${games.lineups}")
            Text(text = "Minutes : ${games.minutes}")
        }
    }
}