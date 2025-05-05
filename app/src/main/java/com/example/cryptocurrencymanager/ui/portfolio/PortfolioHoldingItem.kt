package com.example.cryptocurrencymanager.ui.portfolio

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.text.NumberFormat
import java.util.*

@Composable
fun PortfolioHoldingItem(
    holding: PortfolioHolding,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = holding.name,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = holding.symbol,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = NumberFormat.getCurrencyInstance(Locale.US)
                            .format(holding.totalValue),
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "${holding.quantity} ${holding.symbol}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "Avg. Buy Price",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Text(
                        text = NumberFormat.getCurrencyInstance(Locale.US)
                            .format(holding.averageBuyPrice),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = "Profit/Loss",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Text(
                        text = "${NumberFormat.getCurrencyInstance(Locale.US)
                            .format(holding.profitLoss)} (${String.format("%.2f", holding.profitLossPercentage)}%)",
                        style = MaterialTheme.typography.bodyMedium,
                        color = if (holding.profitLoss.signum() >= 0)
                            MaterialTheme.colorScheme.primary
                        else
                            MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    }
}
