package com.example.debttracker.uidesigns

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.rememberNavController
import com.example.debttracker.Screen
import com.example.debttracker.formatters.TimeAndDate
import com.example.debttracker.formatters.moneyFormat
import com.example.debttracker.models.BottleType
import com.example.debttracker.models.Debt
import com.example.debttracker.models.DebtType
import com.example.debttracker.models.Debtor
import com.example.debttracker.models.SharedViewModel


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
            navC.navigate(Screen.UpdateDebtScreen.route) {
                popUpTo(navC.graph.findStartDestination().id)
                launchSingleTop = true
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
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                // 🔹 Debtor Name
                Text(
                    text = debtor?.name?:"noname",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )
                // Optional visual indicator for active debt
                val active = (debtor?.debt?.active == true)
                val indicatorColor = if (!active)
                    MaterialTheme.colorScheme.error
                else
                    MaterialTheme.colorScheme.primary

                Box(
                    modifier = Modifier
                        .size(12.dp)
                        .background(indicatorColor, CircleShape)
                )
            }

            // 🔹 Debts List
            val debts = debtor?.debt?.itemsOwed?.keys?.map { it }

            //Arranging all bottles into one display unit on the debt card
            //Details on bottle types are reserved for record details screen

            val noOfBottles = getNoOfBottles(debtor)
            var cash by remember { mutableStateOf(moneyFormat(0.0)) }
            val otherDebtTypes = mutableMapOf<String, String>()

            debts?.forEach { type ->
                val amountOwed = debtor.debt?.itemsOwed?.let { it[type]}
                if (checkType(type) == DebtType.Cash) {
                    cash = moneyFormat( amountOwed )
                }
                if (!isBottle(type) && checkType(type) != DebtType.Cash){
                    otherDebtTypes[type] = amountOwed?.toInt().toString()
                }
            }

            Column (
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                if (cash!= moneyFormat(0.0)) DisplayText("Cash owed", cash)
                if (noOfBottles>0) DisplayText("Bottles owed", noOfBottles.toString())
                otherDebtTypes.forEach {
                    DisplayText(it.key, it.value)
                }

            }

            // 🔹 Footer with Date
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(
                    onClick = {}
                ) {
                    
                }

                Text(
                    text = "${debtor?.debt?.dateAdded}, ${debtor?.debt?.timeAdded}",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.outline
                )
            }
        }
    }
}

@Composable
fun DisplayText(type: String, amount: String){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(
            text = type,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = amount,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium
        )
    }
}

fun isBottle(type: String): Boolean{
    val bottleTypes = BottleType.entries.toList()
    val bottlesList = bottleTypes.map { it.name.lowercase() }
    return bottlesList.contains(type.lowercase())
}

fun checkType(type: String): DebtType{
//    val debtTypes = DebtType.entries.toList()
//    val debtList = debtTypes.map { it.name.lowercase() }
    val bottleTypes = BottleType.entries.toList()
    val bottlesList = bottleTypes.map { it.name.lowercase() }
    val isBottle = bottlesList.contains(type.lowercase())
    val check = if (isBottle) DebtType.Bottle else{
        when(type){
            "Cash" -> DebtType.Cash
            "Empty" -> DebtType.Empty
            "Plastic" -> DebtType.Plastic
            "Fulls" -> DebtType.Fulls
            "FullBottle" -> DebtType.FullBottle
            else -> DebtType.Bottle
        }
    }
    return check
}
fun getNoOfBottles(debtor: Debtor?): Int{
    var noOfBottles = 0
    val bottleTypes = BottleType.entries.toList()
    val bottlesList = bottleTypes.map { it.name.lowercase() }
    val debtList = debtor?.debt?.itemsOwed
    debtList?.forEach { (debtType, amountOwed) ->
        if (debtType.lowercase() in bottlesList) noOfBottles += amountOwed.toInt()
    }
    return  noOfBottles

}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun ShowDebtCard(){
    DebtCard(
        vm = viewModel(),
        debtor = Debtor(
            id = "id",
            timeStamp = 456L,
            name = "chuks",
            debt = Debt(
                timeAdded = TimeAndDate().GetTimeString(),
                dateAdded = TimeAndDate().GetDateString(),
                //lastEdited = "",
                itemsOwed = mutableMapOf(
                    DebtType.Cash.name to 6300.0,
                    "nawa" to 550.5
                )

            )
        ),
        navC = rememberNavController()
    )
}
