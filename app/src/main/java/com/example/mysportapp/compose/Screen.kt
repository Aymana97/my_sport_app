package com.example.mysportapp.compose

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Screen(
    val route: String,
    val navArguments: List<NamedNavArgument> = emptyList()
) {
    data object SportSelection : Screen(route = "sportSelection")

    data object FootBallTeamSelection : Screen(route = "footballTeamSelection")

    data object FootballPlayersList : Screen(
        route = "footballPlayersList/{teamId}/{season}",
        navArguments = listOf(navArgument("teamId") {
                type = NavType.IntType
            },
            navArgument("season") {
                type = NavType.IntType
            }
        )
    )
    {
        fun createRoute(teamId: Int, season: Int) = "footballPlayersList/${teamId}/${season}"
    }

    data object FootballPlayerDetails : Screen(
        route = "footballPlayer/{playerId}",
        navArguments = listOf(navArgument("playerId") {
            type = NavType.IntType
        })
    ) {
        fun createRoute(playerId: Int) = "footballPlayer/${playerId}"
    }

    data object NBAPlayersList : Screen(route = "NBAPlayersList")

    data object NBAPlayerDetails : Screen(
        route = "NBAPlayer/{playerId}",
        navArguments = listOf(navArgument("playerId") {
            type = NavType.IntType
        })
    ) {
        fun createRoute(playerId: Int) = "NBAPlayer/${playerId}"
    }
}