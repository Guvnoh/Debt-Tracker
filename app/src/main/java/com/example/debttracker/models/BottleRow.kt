package com.example.debttracker.models

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.first

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddBottleRow(row: BottleRow, vm: SharedViewModel){
    var expanded by remember { mutableStateOf(false) }
    val rows = vm.rows.collectAsState()
    val index = rows.value.indexOf(row)
    var qty by remember { mutableStateOf(row.qty) }
    var type by remember { mutableStateOf(row.type) }
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {

            //val bottleQty by remember { mutableStateOf(bottleRow.qty) }
            //var bottleType by remember { mutableStateOf(bottleRow.type) }
            //var bottles by remember { mutableStateOf("") }
            //bottle type is set for the row
            //as the entire row represents one type of bottle owed
            //multiple bottle types are added by adding new rows

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                OutlinedTextField(
                    value = qty,
                    onValueChange = {
                        qty = it
                        //rows[index] = rows[index].copy(qty = it)
                        row.qty = it
                        vm.updateRowQty(index, it)

                    },
                    label = {
                        Text(
                            text = "Enter no. of bottles",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    modifier = Modifier
                        .wrapContentWidth()
                        .weight(1f)
                )


                OutlinedTextField(
                    value = type,
                    onValueChange = {
//                            type = it
//                            bottleRow.type = it
                        //viewModel.updateRowType(index, it)
                        row.type = it
                        vm.updateRowType(index, it)
                    },
                    label = { Text("Bottle Type") },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                    },
                    singleLine = true,
                    modifier = Modifier
                        .menuAnchor()
                        .weight(1f)
                )

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {

                    BottleType.entries.forEach { selectedBottle ->
                        DropdownMenuItem(
                            enabled = row.qty.toDoubleOrNull() != null,
                            text = { Text(selectedBottle.name) },
                            onClick = {
                                type = selectedBottle.name
                                vm.updateRowType(index,type)
                                expanded = false
                                // rows[index] = rows[index].copy(type = selectedBottle.name)

                                //this should change the existing bottle type to the selected option
                                // from the dropdown menu if not already done by onchange in the type text field...
                                // row.type = selectedBottle.name
                            }
                        )
                    }
                }
                Column(
                    modifier = Modifier.wrapContentHeight()
                ) {
                    IconButton(
                        onClick = {
                            //if(rows.size>1) rows.removeAt(rows.lastIndex)

                            vm.removeRow(row, index)
                        },
                        modifier = Modifier.wrapContentHeight()
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = "",

                            )
                    }

                    IconButton(
                        onClick = {
                            vm.addNewRow(BottleRow("", ""))
                        },
                        modifier = Modifier.wrapContentHeight()
                    ) {
                        Icon(
                            imageVector = Icons.Filled.AddCircle,
                            "",
                        )
                    }
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun ShowRow(){
    AddBottleRow(
        BottleRow("",""),
        viewModel()
    )
}
data class BottleRow(
    var qty: String,
    var type: String
)