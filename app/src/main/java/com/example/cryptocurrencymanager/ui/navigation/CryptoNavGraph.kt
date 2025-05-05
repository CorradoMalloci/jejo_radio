package com.example.cryptocurrencymanager.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cryptocurrencymanager.ui.crypto.detail.CryptoDetailScreen
import com.example.cryptocurrencymanager.ui.crypto.list.CryptoListScreen
import com.example.cryptocurrencymanager.ui.favorites.FavoritesScreen
import com.example.cryptocurrencymanager.ui.portfolio.PortfolioScreen
import com.example.cryptocurrencymanager.ui.portfolio.add.AddTransactionScreen

sealed class Screen(val route: String) {
    object CryptoList : Screen("cryptoList")
    object CryptoDetail : Screen("cryptoDetail/{cryptoId}") {
        fun createRoute(cryptoId: Int) = "cryptoDetail/$cryptoId"
    }
    object Portfolio : Screen("portfolio")
    object AddTransaction : Screen("addTransaction")
    object Favorites : Screen("favorites")
}

@Composable
fun CryptoNavGraph(
    navController: NavHostController,
    startDestination: String = Screen.CryptoList.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.CryptoList.route) {
            CryptoListScreen(
                onCryptoClick = { cryptoId ->
                    navController.navigate(Screen.CryptoDetail.createRoute(cryptoId))
                }
            )
        }

        composable(Screen.CryptoDetail.route) { backStackEntry ->
            val cryptoId = backStackEntry.arguments?.getString("cryptoId")?.toIntOrNull() ?: return@composable
            CryptoDetailScreen(
                cryptoId = cryptoId,
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(Screen.Portfolio.route) {
            PortfolioScreen(
                onAddTransaction = {
                    navController.navigate(Screen.AddTransaction.route)
                }
            )
        }

        composable(Screen.AddTransaction.route) {
            AddTransactionScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(Screen.Favorites.route) {
            FavoritesScreen(
                onCryptoClick = { cryptoId ->
                    navController.navigate(Screen.CryptoDetail.createRoute(cryptoId))
                }
            )
        }
    }
}
