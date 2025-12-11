package com.example.debttracker.models

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.first

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddBottleRow(index: Int, vm: SharedViewModel) {

    var expanded by remember { mutableStateOf(false) }
    val row = vm.rows.collectAsState().value[index]


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)     // TextField decides height
            .padding(6.dp)
            .border(1.dp, Color.Gray.copy(alpha = 0.4f), shape = RectangleShape)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        // ===============================
        // Quantity Field
        // ===============================
        OutlinedTextField(
            value = row.qty,
            onValueChange = {
                vm.updateRowQty(index, it)
            },
            label = { Text("Qty") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .weight(0.6f)
                .fillMaxHeight()
        )

        Spacer(modifier = Modifier.size(8.dp))

        // ===============================
        // Bottle Type (Dropdown)
        // ===============================
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
            modifier = Modifier.weight(1f)
        ) {

            OutlinedTextField(
                value = row.type,
                onValueChange = {
                    vm.updateRowType(index, it)
                },
                label = {
                    Text(
                        text = "Bottle Type",
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,

                    ) },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded) },
                singleLine = true,
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxHeight()
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                BottleType.entries.forEach { selected ->
                    DropdownMenuItem(
                        enabled = row.qty.toDoubleOrNull() != null,
                        text = { Text(selected.name) },
                        onClick = {
                            vm.updateRowType(index, selected.name)
                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.size(10.dp))

//Column for "add" and "remove" icon buttons
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .width(48.dp),                // FIX: prevents overlapping
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
//Remove button
            IconButton(
                onClick = { vm.removeRow(index) },
                modifier = Modifier
                    .size(32.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Remove",
                    tint = Color.Red
                )
            }
//Add button
            IconButton(
                onClick = { vm.addNewRow(BottleRow("", "")) },
                modifier = Modifier
                    .size(32.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.AddCircle,
                    contentDescription = "Add",
                    tint = Color(0xFF2ECC71)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShowRow(){
    AddBottleRow(
        index = 0,
        viewModel()
    )
}
data class BottleRow(
    val qty: String,
    val type: String
)