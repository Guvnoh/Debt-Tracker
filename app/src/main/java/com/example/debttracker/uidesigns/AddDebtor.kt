package com.example.debttracker.uidesigns

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.debttracker.formatters.TimeAndDate
import com.example.debttracker.models.AddBottleRow
import com.example.debttracker.models.BottomNavItem
import com.example.debttracker.models.Debt
import com.example.debttracker.models.DebtType
import com.example.debttracker.models.Debtor
import com.example.debttracker.models.SharedViewModel
import com.example.debttracker.root
import com.google.firebase.database.ServerValue

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable

fun AddDebt(navController: NavController, viewModel: SharedViewModel){
    var name by remember { mutableStateOf("") }
    var cash by remember { mutableStateOf("") }
    var empties by remember { mutableStateOf("") }
    //val bottlesList = remember { mutableStateListOf<Pair<String, String>>()}
    var fulls by remember { mutableStateOf("") }
    var fullBottle by remember { mutableStateOf("") }
    var plastic by remember { mutableStateOf("") }
    val rows = viewModel.rows.collectAsState()
    val debt = viewModel.newDebt.collectAsState()

    val entriesList = listOf(
        name, cash, empties, fulls, fullBottle, plastic
    )

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            elevation = CardDefaults.cardElevation(6.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    "Add Debt Record",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 10.dp)
                )

                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Debtor's name") },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                )

                OutlinedTextField(
                    value = cash,
                    onValueChange = { cash = it },
                    label = { Text("Enter cash amount") },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                )

                //bottles go here
                val rowList =  rows.value
                rowList.forEach {
                    AddBottleRow(it, viewModel)
                }



                OutlinedTextField(
                    value = empties,
                    onValueChange = { empties = it },
                    label = { Text("Enter no. of empties") },
                    maxLines = 1,
                    modifier = Modifier
                        .fillMaxWidth()
                )
                OutlinedTextField(
                    value = fulls,
                    onValueChange = { fulls = it },
                    label = { Text("Enter no. of fulls") },
                    maxLines = 1,
                    modifier = Modifier
                        .fillMaxWidth()
                )
                OutlinedTextField(
                    value = fullBottle,
                    onValueChange = { fullBottle = it },
                    label = { Text("Enter no. of full bottles") },
                    maxLines = 1,
                    modifier = Modifier
                        .fillMaxWidth()
                )
                OutlinedTextField(
                    value = plastic,
                    onValueChange = { plastic = it },
                    label = { Text("Enter no. of plastics") },
                    maxLines = 1,
                    modifier = Modifier
                        .fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                Button(
                    onClick = {
                        val debtorName: String

                        if (name.isBlank()) {
                            Toast.makeText(context, "Enter name!", Toast.LENGTH_SHORT).show()
                        } else {
                            debtorName = name
                            val dateAdded = TimeAndDate().GetDateString()
                            val timeAdded = TimeAndDate().GetTimeString()
//                            viewModel.setNewDebt( Debt(timeAdded =timeAdded, dateAdded=dateAdded))
//                            entriesList.forEach { entry ->
//
//                                //sorted cash makes sure cash entries with commas don't cause errors
//                                val sortedAmount = if (entry!=name )entry.replace("[, ]".toRegex(), "")else entry
//                                //This ensures that the 'name' entries retain their original format
//                                //while the other entries e.g cash and empties are formatted
//                                //to remove commas and unnecessary spaces
//
//// this section excludes the name from items added to items owed
//                                if (entry!=name && entry.isNotBlank()){
//                                    when(entry){
//                                        cash -> viewModel.updateNewDebt(
//                                            DebtType.Cash.name,
//                                            sortedAmount.toDoubleOrNull()?:0.0
//                                        )
//                                        empties -> viewModel.updateNewDebt(
//                                            DebtType.Empty.name,
//                                            sortedAmount.toDoubleOrNull()?:0.0
//                                        )
//                                        fullBottle -> viewModel.updateNewDebt(
//                                            DebtType.Full_Bottle.name,
//                                            sortedAmount.toDoubleOrNull()?:0.0
//                                        )
//                                        plastic -> viewModel.updateNewDebt(
//                                            DebtType.Plastic.name,
//                                            sortedAmount.toDoubleOrNull()?:0.0
//                                        )
//                                        fulls -> viewModel.updateNewDebt(
//                                            DebtType.Fulls.name,
//                                            sortedAmount.toDoubleOrNull()?:0.0
//                                        )
//                                    }
//                                }
//                                val bottlesOwed = viewModel.rows.value
//                                if (bottlesOwed.isNotEmpty()){
//                                    bottlesOwed.forEach {
//                                        bottle ->
//                                        //bottle is a pair with the qty(double) as the first input
//                                        //and the type as the second
//                                     if ((bottle.qty.toDoubleOrNull()?:0.0)>0){
//                                         val bottleQty = bottle.qty
//                                         var bottleType = bottle.type
//                                         bottleType = sanitizeKey( bottleType )
//
//                                         bottleQty.toDoubleOrNull()?.let{
//                                             if(it>0.0){
//                                                 viewModel.updateNewDebt(
//                                                     bottleType,
//                                                     bottleQty.toDoubleOrNull()?:0.0
//                                                 )
//                                             }
//                                         }
//                                     }
//                                    }
//                                }
//                            }

                            //start
                            val items = mutableMapOf<String, Double>()

                            fun addItem(key: String, value: String) {
                                val n = value.replace(",", "").toDoubleOrNull()
                                if (n != null && n > 0) items[key] = n
                            }

                            addItem("Cash", cash)
                            addItem("Empty", empties)
                            addItem("Fulls", fulls)
                            addItem("Full_Bottle", fullBottle)
                            addItem("Plastic", plastic)

                            viewModel.rows.value.forEach { row ->
                                val qty = row.qty.toDoubleOrNull() ?: 0.0
                                if (qty > 0) items[sanitizeKey(row.type)] = qty
                            }

                            val debt = Debt(
                                dateAdded = dateAdded,
                                timeAdded = timeAdded,
                                itemsOwed = items
                            )

                            saveRecord(
                                Debtor(
                                    name = debtorName,
                                    debt = debt
                                )
                            )


                            //finish

                            navController.navigate(BottomNavItem.Records.route)

                            Toast.makeText(context, "Debt record added!", Toast.LENGTH_SHORT).show()

                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Add to Records", fontSize = 16.sp)
                }

            }
        }
    }

}



fun saveRecord(debtor: Debtor){
    val recordRoot = root.push()
    val key = recordRoot.key
//    debtor.id = key.key
//    debtor.timeStamp = ServerValue.TIMESTAMP
    val safeName = debtor.name?.let { sanitizeKey(it) }
    //safeDebt?.lastEdited = ServerValue.TIMESTAMP.toString()
    val debtorToMap =
        mutableMapOf(
        "id" to key,
        "timeStamp" to ServerValue.TIMESTAMP,
        "name" to safeName,
        "debt" to debtor.debt
    )

    recordRoot.setValue(debtorToMap)

}

fun safeMap(original: MutableMap<String, Any?>): MutableMap<String, Any?> {
    return original.mapKeys { entry ->
        sanitizeKey(entry.key)
    }.toMutableMap()
}
fun sanitizeKey(key: String): String {
    return key.replace(".", "_")
        .replace("#", "_")
        .replace("$", "_")
        .replace("[", "_")
        .replace("]", "_")
        .replace("/", "_")
}



@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun AddDebtPreview(){
    val fvm = SharedViewModel()
    AddDebt(
        navController = rememberNavController(),
        viewModel = fvm)
}