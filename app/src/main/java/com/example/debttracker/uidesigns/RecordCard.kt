package com.example.debttracker.uidesigns

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.debttracker.models.DebtRecord
import com.example.debttracker.models.SelectableCard
import com.example.debttracker.ui.theme.ActiveGreen
import com.example.debttracker.ui.theme.InactiveRed
import com.example.debttracker.viewmodels.RecordsViewmodel

@Composable
fun RecordCard(
    vm: RecordsViewmodel,
    debtRecord: DebtRecord,
    navController: NavController,
    selected: Boolean,
    selectedItems: MutableList<DebtRecord>,
) {
    val isActive = debtRecord.debt?.active != false

    SelectableCard(
        modifier = Modifier,
        onClick = {
            vm.onRecordCardClick(
                navController = navController, selectedItems = selectedItems, record = debtRecord
            )
        },
        onLongPress = { vm.onCardLongClick(selectedItems, debtRecord) },
        selected = selected
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Name row with status indicator
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)
                ) {
                    // Status dot
                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .clip(CircleShape)
                            .background(if (isActive) ActiveGreen else InactiveRed)
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(
                        text = debtRecord.name.toString(),
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.primary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                // Status label
                Text(
                    text = if (isActive) "Active" else "Inactive",
                    style = MaterialTheme.typography.labelSmall,
                    color = if (isActive) ActiveGreen else InactiveRed
                )
            }

            Spacer(Modifier.height(4.dp))

            val hasBeenUpdated = !debtRecord.oldRecords.isNullOrEmpty()
            Text(
                text = if (hasBeenUpdated) "Updated: ${debtRecord.debt?.dateAdded ?: "N/A"}"
                       else "Added: ${debtRecord.debt?.dateAdded ?: "N/A"}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(start = 18.dp)
            )

            // Item preview - show first 3 items
            val items = debtRecord.debt?.itemsOwed
            if (!items.isNullOrEmpty()) {
                Spacer(Modifier.height(8.dp))

                // 10.dp (dot) + 8.dp (spacer) to align with the name text
                val itemStartPadding = Modifier.padding(start = 18.dp)

                // Show cash prominently if present
                val cashAmount = items["Cash"]
                if (cashAmount != null) {
                    Text(
                        text = "$cashAmount",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
                        color = MaterialTheme.colorScheme.tertiary,
                        modifier = itemStartPadding
                    )
                }

                // Show other items (up to 3)
                val otherItems = items.filter { it.key != "Cash" }.entries.take(3)
                if (otherItems.isNotEmpty()) {
                    val preview = otherItems.joinToString(" | ") { "${it.key}: ${it.value}" }
                    Text(
                        text = preview,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = itemStartPadding
                    )
                }

                // Show how many more items if > 3
                val remaining = items.size - (if (cashAmount != null) 1 else 0) - otherItems.size
                if (remaining > 0) {
                    Text(
                        text = "+$remaining more items",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
                        modifier = itemStartPadding
                    )
                }
            }
        }
    }
}
