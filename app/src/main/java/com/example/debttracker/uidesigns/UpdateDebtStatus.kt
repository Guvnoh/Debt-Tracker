package com.example.debttracker.uidesigns

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.debttracker.formatters.TimeAndDate
import com.example.debttracker.models.CustomAlertDialog
import com.example.debttracker.models.Debt
import com.example.debttracker.models.DebtRecord
import com.example.debttracker.models.Options
import com.example.debttracker.models.DialogKey
import com.example.debttracker.models.ReduceQuantityDialog
import com.example.debttracker.repositories.RecordsRepository
import com.example.debttracker.ui.theme.ActiveGreen
import com.example.debttracker.ui.theme.InactiveRed


@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DebtDetailsScreen(
    debtRecord: DebtRecord?,
    navController: NavController,
) {
    var deleteRecordItemDialog by remember { mutableStateOf(false) }
    var deleteEntireRecordDialog by remember { mutableStateOf(false) }
    var debtToDelete by remember { mutableStateOf("") }
    var reduceItemKey by remember { mutableStateOf<String?>(null) }
    var reduceItemIsCash by remember { mutableStateOf(false) }
    val isActive = debtRecord?.debt?.active != false

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                title = {
                    Column {
                        Text(
                            "Debt Details",
                            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                        )
                        debtRecord?.let {
                            Text(
                                text = it.name ?: "Unknown",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                },
                actions = {
                    Options(
                        setInactive = { RecordsRepository.deactivateRecord(debtRecord ?: DebtRecord()) },
                        setActive = { RecordsRepository.reactivateRecord(debtRecord ?: DebtRecord()) },
                        onDelete = {
                            deleteEntireRecordDialog = true
                        },
                        debtRecord = debtRecord ?: DebtRecord()
                    )
                }
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            // Status badge + date info
            debtRecord?.let {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Status chip
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .background(
                                if (isActive) ActiveGreen.copy(alpha = 0.15f)
                                else InactiveRed.copy(alpha = 0.15f)
                            )
                            .padding(horizontal = 16.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = if (isActive) "Active" else "Inactive",
                            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                            color = if (isActive) ActiveGreen else InactiveRed
                        )
                    }

                    Text(
                        text = "${it.debt?.dateAdded ?: ""}, ${it.debt?.timeAdded ?: ""}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }

                // Item count
                val itemCount = it.debt?.itemsOwed?.size ?: 0
                Text(
                    text = "$itemCount item${if (itemCount != 1) "s" else ""} owed",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            // No debt record
            if (debtRecord == null || debtRecord.debt?.itemsOwed.isNullOrEmpty()) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "No debt record found.",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.error
                    )
                }
                return@Column
            }

            // Group items by type
            val items = debtRecord.debt?.itemsOwed ?: emptyMap()
            val cashItems = items.filter { it.key == "Cash" }
            val bottleItems = items.filter { it.key.contains("bottles", ignoreCase = true) }
            val emptyItems = items.filter { it.key.contains("empties", ignoreCase = true) }
            val plasticItems = items.filter { it.key.contains("plastics", ignoreCase = true) }
            val otherItems = items - cashItems.keys - bottleItems.keys - emptyItems.keys - plasticItems.keys

            // Cash section
            if (cashItems.isNotEmpty()) {
                DebtSectionCard(
                    title = "Cash",
                    icon = "\uD83D\uDCB0",
                    items = cashItems,
                    isCash = true,
                    debtRecord = debtRecord,
                    onDeleteItem = { type ->
                        deleteRecordItemDialog = true
                        debtToDelete = type
                    },
                    onReduceItem = { key ->
                        reduceItemKey = key
                        reduceItemIsCash = true
                    }
                )
            }

            // Bottles section
            if (bottleItems.isNotEmpty()) {
                DebtSectionCard(
                    title = "Bottles",
                    icon = "\uD83C\uDF7A",
                    items = bottleItems,
                    debtRecord = debtRecord,
                    onDeleteItem = { type ->
                        deleteRecordItemDialog = true
                        debtToDelete = type
                    },
                    onReduceItem = { key ->
                        reduceItemKey = key
                        reduceItemIsCash = false
                    }
                )
            }

            // Empties section
            if (emptyItems.isNotEmpty()) {
                DebtSectionCard(
                    title = "Empties",
                    icon = "\uD83E\uDEE7",
                    items = emptyItems,
                    debtRecord = debtRecord,
                    onDeleteItem = { type ->
                        deleteRecordItemDialog = true
                        debtToDelete = type
                    },
                    onReduceItem = { key ->
                        reduceItemKey = key
                        reduceItemIsCash = false
                    }
                )
            }

            // Plastics section
            if (plasticItems.isNotEmpty()) {
                DebtSectionCard(
                    title = "Plastics",
                    icon = "\uD83E\uDEF9",
                    items = plasticItems,
                    debtRecord = debtRecord,
                    onDeleteItem = { type ->
                        deleteRecordItemDialog = true
                        debtToDelete = type
                    },
                    onReduceItem = { key ->
                        reduceItemKey = key
                        reduceItemIsCash = false
                    }
                )
            }

            // Other items
            if (otherItems.isNotEmpty()) {
                DebtSectionCard(
                    title = "Other",
                    icon = "\uD83D\uDCE6",
                    items = otherItems,
                    debtRecord = debtRecord,
                    onDeleteItem = { type ->
                        deleteRecordItemDialog = true
                        debtToDelete = type
                    },
                    onReduceItem = { key ->
                        reduceItemKey = key
                        reduceItemIsCash = false
                    }
                )
            }

            // History section
            val oldRecords = debtRecord.oldRecords
            if (!oldRecords.isNullOrEmpty()) {
                HistorySection(oldRecords = oldRecords)
            }

            Spacer(Modifier.height(16.dp))
        }

        // Delete entire record dialog
        if (deleteEntireRecordDialog) {
            debtRecord?.let {
                CustomAlertDialog(
                    debtRecord = it,
                    debt = debtToDelete,
                    navController = navController,
                    caseKey = DialogKey.topAppBar
                ) {
                    deleteEntireRecordDialog = false
                }
            }
        }

        // Delete single item dialog
        if (deleteRecordItemDialog) {
            debtRecord?.let {
                CustomAlertDialog(
                    debtRecord = it,
                    debt = debtToDelete,
                    navController = navController,
                    caseKey = DialogKey.debtCard
                ) {
                    deleteRecordItemDialog = false
                }
            }
        }

        // Reduce quantity dialog
        reduceItemKey?.let { key ->
            debtRecord?.let { record ->
                val currentValue = record.debt?.itemsOwed?.get(key) ?: ""
                ReduceQuantityDialog(
                    debtRecord = record,
                    itemKey = key,
                    currentValue = currentValue,
                    isCash = reduceItemIsCash,
                    onDismiss = { reduceItemKey = null }
                )
            }
        }
    }
}

@Composable
private fun DebtSectionCard(
    title: String,
    icon: String,
    items: Map<String, String>,
    isCash: Boolean = false,
    debtRecord: DebtRecord,
    onDeleteItem: (String) -> Unit,
    onReduceItem: (String) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Section header
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(icon, fontSize = 20.sp)
                Spacer(Modifier.width(8.dp))
                Text(
                    title,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.primary
                )
            }

            items.forEach { (type, amount) ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = type,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            text = amount,
                            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
                            color = if (isCash) MaterialTheme.colorScheme.tertiary
                            else MaterialTheme.colorScheme.onSurface
                        )
                        IconButton(
                            onClick = { onReduceItem(type) },
                            modifier = Modifier.size(32.dp)
                        ) {
                            Icon(
                                Icons.Default.Edit,
                                contentDescription = "Edit quantity",
                                modifier = Modifier.size(18.dp),
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                        Options(
                            setInactive = { RecordsRepository.deactivateRecord(debtRecord) },
                            setActive = { RecordsRepository.reactivateRecord(debtRecord) },
                            onDelete = { onDeleteItem(type) },
                            debtRecord = debtRecord
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun HistorySection(oldRecords: List<Debt>) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            TextButton(
                onClick = { expanded = !expanded },
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "History (${oldRecords.size})",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.primary
                    )
                    Icon(
                        if (expanded) Icons.Default.KeyboardArrowUp
                        else Icons.Default.KeyboardArrowDown,
                        contentDescription = if (expanded) "Collapse" else "Expand"
                    )
                }
            }

            if (expanded) {
                oldRecords.forEachIndexed { index, debt ->
                    if (index > 0) {
                        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                    }
                    Column(
                        modifier = Modifier.padding(vertical = 4.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = "${debt.dateAdded ?: ""}, ${debt.timeAdded ?: ""}",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        debt.itemsOwed?.forEach { (itemName, value) ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 8.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = itemName,
                                    style = MaterialTheme.typography.bodySmall
                                )
                                Text(
                                    text = value,
                                    style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.SemiBold)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun ClearDebtDemo() {
    val new = Debt(
        timeAdded = TimeAndDate().GetTimeString(),
        dateAdded = TimeAndDate().GetDateString(),
        itemsOwed = mutableMapOf("Cash" to "6300")
    )
    val debtRecord = DebtRecord(name = "Mama Ejima", debt = new)
    DebtDetailsScreen(debtRecord = debtRecord, navController = rememberNavController())
}
