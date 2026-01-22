package com.example.debttracker.models

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.debttracker.viewmodels.AddDebtorViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddItemRow(
    index: Int,
    vm: AddDebtorViewModel,
    rowType: RowType,

    ) {

    var expanded by remember { mutableStateOf(false) }

    val activeRow = when(rowType){
        RowType.Bottles -> vm.bottleRows.collectAsState().value[index]
        RowType.Empties ->  vm.emptiesRows.collectAsState().value[index]
        RowType.Plastics ->  vm.plasticRows.collectAsState().value[index]
    }
    val dropDownMenu = when(rowType){
        RowType.Bottles -> BottleType.entries.toList()
        RowType.Empties -> EmptyType.entries.toList()
        RowType.Plastics -> PlasticType.entries.toList()
    }


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .padding(6.dp)
            .border(1.dp, Color.Gray.copy(alpha = 0.4f), shape = RectangleShape)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        // Quantity Field
        OutlinedTextField(
            value = activeRow.qty,
            onValueChange = {
                vm.updateRowQty(rowType = rowType, index = index, qty = it)
            },
            label = { Text("Qty") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .weight(0.6f)
                .fillMaxHeight()
        )

        Spacer(modifier = Modifier.size(8.dp))

        // Row name selection (Dropdown) e.g "amstel" for bottles or "Guinness" for empties
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
            modifier = Modifier.weight(1f)
        ) {

            OutlinedTextField(
                value = activeRow.name, //starts off as an empty string
                onValueChange = {
                    vm.updateRowName(rowType = rowType, name = it, index = index)
                },
                label = {
                    Text(
                        text = rowType.name,
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
                dropDownMenu.forEach { selected ->
                    DropdownMenuItem(
                        enabled = activeRow.qty.toDoubleOrNull() != null,
                        text = { Text(selected.name) },
                        onClick = {
                            vm.updateRowName(rowType = rowType, name = selected.name, index = index)
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
                .width(48.dp),   //prevents overlapping
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
//Remove button
            IconButton(
                onClick = { vm.removeRow(index, rowType) },
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
                onClick = { vm.addNewRow(rowType) },
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
    AddItemRow(
        index = 0,
        viewModel(),
        rowType = RowType.Bottles
    )
}

data class RowData(
    val qty: String,
    val name: String
)

enum class RowType {
    Bottles,
    Empties,
    Plastics
}