package com.example.debttracker.models

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.debttracker.ui.theme.ActiveGreen
import com.example.debttracker.ui.theme.InactiveRed


@Composable
fun Options(
    setInactive: (DebtRecord) -> Unit,
    setActive: (DebtRecord) -> Unit,
    onDelete: (DebtRecord) -> Unit,
    debtRecord: DebtRecord
) {
    var expanded by remember { mutableStateOf(false) }
    val isActive = debtRecord.debt?.active != false

    Box {
        IconButton(
            onClick = { expanded = true }
        ) {
            Icon(
                imageVector = Icons.Default.MoreVert, "options"
            )
        }
        MaterialTheme(
            shapes = MaterialTheme.shapes.copy(
                extraSmall = RoundedCornerShape(25.dp)
            ),
            content = {
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                ) {
                    // Show Deactivate only if active, Activate only if inactive
                    if (isActive) {
                        DropdownMenuItem(
                            text = { Text(text = "Deactivate") },
                            leadingIcon = {
                                Icon(
                                    Icons.Default.Warning,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            },
                            onClick = {
                                expanded = false
                                setInactive(debtRecord)
                            }
                        )
                    } else {
                        DropdownMenuItem(
                            text = { Text(text = "Activate") },
                            leadingIcon = {
                                Icon(
                                    Icons.Default.CheckCircle,
                                    contentDescription = null,
                                    tint = ActiveGreen
                                )
                            },
                            onClick = {
                                expanded = false
                                setActive(debtRecord)
                            }
                        )
                    }
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = "Delete",
                                color = InactiveRed
                            )
                        },
                        leadingIcon = {
                            Icon(
                                Icons.Default.Delete,
                                contentDescription = null,
                                tint = InactiveRed
                            )
                        },
                        onClick = {
                            expanded = false
                            onDelete(debtRecord)
                        }
                    )
                }
            }
        )
    }
}
