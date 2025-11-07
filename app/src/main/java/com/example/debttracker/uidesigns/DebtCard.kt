package com.example.debttracker.uidesigns

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.rememberNavController
import com.example.debttracker.formatters.TimeAndDate
import com.example.debttracker.formatters.moneyFormat
import com.example.debttracker.models.DebtClass
import com.example.debttracker.models.DebtType
import com.example.debttracker.models.SharedViewModel

data class Debtor(
    var name: String? = null,
    val debt: DebtClass? = null
)

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DebtCard(
    vm: SharedViewModel,
    debtor: Debtor?,
    navC: NavController
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 8.dp),
        shape = CardDefaults.shape,
        elevation = CardDefaults.cardElevation(6.dp),
        onClick = {
            vm.selectedRecord = debtor
            navC.navigate("ClearDebt") {
                popUpTo(navC.graph.findStartDestination().id) { saveState = true }
                launchSingleTop = true
                restoreState = true
            }
        },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // 🔹 Debtor Name
            Text(
                text = debtor?.name?:"noname",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
            )

            // 🔹 Debts List
            val debts = debtor?.debt?.itemsOwed?.keys
            debts?.forEach { type ->
                val debtString = debtor.debt.getDebtTypeString(type)
                val amountOwed = debtor.debt.itemsOwed[type]

                val displayAmount =
                    if (debtString == "Cash") moneyFormat(amountOwed) else amountOwed.toString()

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "$debtString owed",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = displayAmount,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            // 🔹 Footer with Date
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {

                Text(
                    text = "${debtor?.debt?.dateAdded}, ${debtor?.debt?.timeAdded}",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.outline
                )
            }
        }
    }
}



@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun DebtCardPreview(){

    val new = DebtClass()
    new.addDebt(DebtType.MONEY, 8500.0)
    new.addDebt(DebtType.MONEY, 8500.0)
    new.payDebt(DebtType.MONEY, 10000.0)
    val debtor = Debtor("Mama Ejima", new)
    DebtCard(
        vm = viewModel(),
        debtor = Debtor(
            name = "chuks",
            debt = DebtClass(mutableMapOf(DebtType.MONEY.name to 5000.0))),
        navC = rememberNavController()
    )

}