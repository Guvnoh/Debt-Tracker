package com.example.debttracker.uidesigns

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.debttracker.Screen
import com.example.debttracker.models.DebtRecord
import com.example.debttracker.models.SelectableCard
import com.example.debttracker.viewmodels.RecordsViewmodel

@Composable
fun RecordCard(
    vm: RecordsViewmodel,
    debtRecord: DebtRecord,
    navController: NavController,
    selected: Boolean,
    selectedItems: MutableList<DebtRecord>,

) {
    SelectableCard(
        modifier = Modifier,
        onClick = {vm.onRecordCardClick(
            navController = navController, selectedItems = selectedItems, record = debtRecord
        )},
        //long press adds record to 'selected items' list ready for deletion
        onLongPress = {vm.onCardLongClick(selectedItems, debtRecord)},
        selected = selected
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            Text(
                text = debtRecord.name.toString(),
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(Modifier.height(4.dp))

            Text(
                text = "Added: ${debtRecord.debt?.dateAdded ?: "N/A"}",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )

            Spacer(Modifier.height(10.dp))

            Button(
                onClick = {
                    // Navigate to a detailed record page
                    // navController.navigate("record_details/${debtor.id}")
                    vm.selectedRecord.value = debtRecord
                    navController.navigate(Screen.UpdateDebtScreen.route) {
                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("View")
            }
        }
    }
}