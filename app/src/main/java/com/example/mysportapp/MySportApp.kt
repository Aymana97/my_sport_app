package com.example.mysportapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mysportapp.compose.Screen
import com.example.mysportapp.compose.footballplayerdetails.PlayerDetailsScreen
import com.example.mysportapp.compose.footballplayerslist.PlayersListScreen
import com.example.mysportapp.compose.footballteamselection.FootballTeamSelectionScreen
import com.example.mysportapp.compose.nbaplayerdetails.NBAPlayerDetailsScreen
import com.example.mysportapp.compose.nbaplayerslist.NBAPlayersListScreen
import com.example.mysportapp.compose.sportselection.SportSelectionScreen

@Composable
fun MySportApp() {
    val navController = rememberNavController()
    MySportNavHost(
        navController = navController
    )
}

@Composable
fun MySportNavHost(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = Screen.SportSelection.route) {

        composable(route = Screen.SportSelection.route) {
            SportSelectionScreen(navController)
        }

        composable(route = Screen.FootBallTeamSelection.route) {
            FootballTeamSelectionScreen(
                onBackClick = {
                    navController.navigateUp()
                },
                onButtonClick = { teamId, season ->
                    navController.navigate(
                        Screen.FootballPlayersList.createRoute(
                            teamId = teamId,
                            season = season
                        )
                    )
                }
            )
        }

        composable(
            route = Screen.FootballPlayersList.route,
            arguments = Screen.FootballPlayersList.navArguments,
        ) { navBackStackEntry ->
            PlayersListScreen(
                teamId = navBackStackEntry.arguments?.getInt("teamId") ?: 0,
                season = navBackStackEntry.arguments?.getInt("season") ?: 0,
                onBackClick = {
                    navController.navigateUp()
                },
                onPlayerClick = {
                    navController.navigate(
                        Screen.FootballPlayerDetails.createRoute(
                            playerId = it
                        )
                    )
                }
            )
        }

        composable(
            route = Screen.FootballPlayerDetails.route,
            arguments = Screen.FootballPlayerDetails.navArguments
        ) {
            PlayerDetailsScreen(
                playerId = it.arguments?.getInt("playerId") ?: 0,
                onBackClick = {
                    navController.navigateUp()
                }
            )
        }

        composable(route = Screen.NBAPlayersList.route) {
            NBAPlayersListScreen(
                onBackClick = {
                    navController.navigateUp()
                },
                onPlayerClick = {
                    navController.navigate(
                        Screen.NBAPlayerDetails.createRoute(
                            playerId = it
                        )
                    )
                }
            )
        }

        composable(
            route = Screen.NBAPlayerDetails.route,
            arguments = Screen.FootballPlayerDetails.navArguments
        ) {
            NBAPlayerDetailsScreen(
                playerId = it.arguments?.getInt("playerId") ?: 0,
                onBackClick = {
                    navController.navigateUp()
                },
            )
        }
    }
}