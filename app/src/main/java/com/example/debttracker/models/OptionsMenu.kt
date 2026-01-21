package com.example.debttracker.models

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
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
import androidx.compose.ui.unit.dp


@Composable
fun Options(
    setInactive: (DebtRecord) -> Unit,
    setActive: (DebtRecord) -> Unit,
    onDelete: (DebtRecord) -> Unit,
    debtRecord: DebtRecord
){
    var expanded by remember { mutableStateOf(false) }
    Box(
    ){
        IconButton(
            onClick = {expanded = true}
        ) {
            Icon(
                imageVector = Icons.Default.MoreVert, "options"
            )
        }
        MaterialTheme(
            shapes = MaterialTheme.shapes.copy(
                extraSmall = RoundedCornerShape(25.dp)  // DROPDOWN MENU SHAPE
            ),
            content = {
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },

                    ) {
                    DropdownMenuItem(
                        text = {
                            Text(text = "Deactivate")
                        },
                        onClick = {
                            expanded = false
                            setInactive(debtRecord)
                        }
                    )
                    DropdownMenuItem(
                        text = {
                            Text(text = "Activate")
                        },
                        onClick = {
                            expanded = false
                            setActive(debtRecord)
                        }
                    )
                    DropdownMenuItem(
                        text = {
                            Text(text = "Delete")
                        },
                        onClick = {
                            expanded = false
                            onDelete(debtRecord)
                        }
                    )
                    DropdownMenuItem(
                        text = {
                            Text(text = "Edit")
                        },
                        onClick = {
                            expanded = false
                            setInactive(debtRecord)
                        }
                    )
                }
            }
        )

    }

}