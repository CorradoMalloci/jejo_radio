package com.example.cryptocurrencymanager.ui.portfolio

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.text.NumberFormat
import java.util.*

@Composable
fun PortfolioContent(
    state: PortfolioUiState.Success,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.padding(16.dp)
    ) {
        // Portfolio Summary Card
        item {
            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Portfolio Value",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = NumberFormat.getCurrencyInstance(Locale.US)
                            .format(state.totalValue),
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row {
                        Text(
                            text = "Total Profit/Loss: ",
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Text(
                            text = NumberFormat.getCurrencyInstance(Locale.US)
                                .format(state.totalProfit),
                            color = if (state.totalProfit.signum() >= 0)
                                MaterialTheme.colorScheme.primary
                            else
                                MaterialTheme.colorScheme.error
                        )
                        Text(
                            text = " (${String.format("%.2f", state.profitPercentage)}%)"
                        )
                    }
                }
            }
        }

        item { Spacer(modifier = Modifier.height(16.dp)) }

        // Holdings Section
        item {
            Text(
                text = "Your Holdings",
                style = MaterialTheme.typography.titleLarge
            )
        }

        items(state.holdings) { holding ->
            PortfolioHoldingItem(
                holding = holding,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        item { Spacer(modifier = Modifier.height(16.dp)) }

        // Recent Transactions Section
        item {
            Text(
                text = "Recent Transactions",
                style = MaterialTheme.typography.titleLarge
            )
        }

        items(state.recentTransactions) { transaction ->
            TransactionItem(
                transaction = transaction,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}
