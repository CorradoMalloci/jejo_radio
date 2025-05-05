package com.example.cryptocurrencymanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.cryptocurrencymanager.ui.navigation.CryptoNavGraph
import com.example.cryptocurrencymanager.ui.navigation.Screen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination

            Scaffold(
                bottomBar = {
                    NavigationBar {
                        listOf(
                            Screen.CryptoList to "Market",
                            Screen.Portfolio to "Portfolio",
                            Screen.Favorites to "Favorites"
                        ).forEach { (screen, label) ->
                            NavigationBarItem(
                                selected = currentDestination?.hierarchy?.any { 
                                    it.route == screen.route 
                                } == true,
                                onClick = {
                                    navController.navigate(screen.route) {
                                        popUpTo(navController.graph.startDestinationId) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                },
                                icon = { /* Add icons later */ },
                                label = { Text(label) }
                            )
                        }
                    }
                }
            ) { paddingValues ->
                CryptoNavGraph(
                    navController = navController,
                    modifier = Modifier.padding(paddingValues)
                )
            }
        }
    }
}
