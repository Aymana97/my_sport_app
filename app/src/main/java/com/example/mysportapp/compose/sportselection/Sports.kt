package com.example.mysportapp.compose.sportselection

import androidx.navigation.NavController
import com.example.mysportapp.compose.Screen

enum class Sports {
    FOOTBALL {
        override fun onClick(navController: NavController): () -> Unit {
            return { navController.navigate(Screen.FootBallTeamSelection.route) }
        }
    },

    NBA {
        override fun onClick(navController: NavController): () -> Unit {
            return { navController.navigate(Screen.NBAPlayersList.route) }
        }
    };

    abstract fun onClick(navController: NavController): () -> Unit
}