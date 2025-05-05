package com.example.cryptocurrencymanager.ui.portfolio

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cryptocurrencymanager.data.db.entity.PortfolioTransactionEntity
import com.example.cryptocurrencymanager.data.db.entity.TransactionType
import java.text.DateFormat
import java.text.NumberFormat
import java.util.*

@Composable
fun TransactionItem(
    transaction: PortfolioTransactionEntity,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = when(transaction.type) {
                        TransactionType.BUY -> "Bought"
                        TransactionType.SELL -> "Sold"
                    },
                    style = MaterialTheme.typography.titleMedium,
                    color = when(transaction.type) {
                        TransactionType.BUY -> MaterialTheme.colorScheme.primary
                        TransactionType.SELL -> MaterialTheme.colorScheme.error
                    }
                )
                Text(
                    text = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT)
                        .format(Date(transaction.timestamp)),
                    style = MaterialTheme.typography.bodySmall
                )
            }
            
            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = "${transaction.quantity} @ ${
                        NumberFormat.getCurrencyInstance(Locale.US)
                            .format(transaction.pricePerUnit)
                    }",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = NumberFormat.getCurrencyInstance(Locale.US)
                        .format(transaction.transactionValue),
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}
