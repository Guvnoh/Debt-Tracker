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
fun AddDebt(navController: NavController, viewModel: SharedViewModel) {

    var name by remember { mutableStateOf("") }
    var cash by remember { mutableStateOf("") }
    var empties by remember { mutableStateOf("") }
    var fulls by remember { mutableStateOf("") }
    var fullBottle by remember { mutableStateOf("") }
    var plastic by remember { mutableStateOf("") }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        //=======================
        //   PAGE HEADER
        //=======================
        Text(
            text = "Add Debt Record",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.primary
        )

        //=======================
        //   DEBTOR INFO CARD
        //=======================
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(4.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                Text(
                    "Debtor Information",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Debtor Name") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                OutlinedTextField(
                    value = cash,
                    onValueChange = { cash = it },
                    label = { Text("Cash Amount") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }
        }

        //=======================
        //   BOTTLE SECTION CARD
        //=======================
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(4.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                Text(
                    "Bottle Records",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                viewModel.rows.collectAsState().value.forEachIndexed { index, bottleRow ->
                    AddBottleRow(index, viewModel)
                }


            }
        }


        //=======================
        //   EXTRA ITEMS CARD
        //=======================
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(4.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                Text(
                    "Other Items",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                OutlinedTextField(
                    value = empties,
                    onValueChange = { empties = it },
                    label = { Text("Empties") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                OutlinedTextField(
                    value = fulls,
                    onValueChange = { fulls = it },
                    label = { Text("Fulls") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                OutlinedTextField(
                    value = fullBottle,
                    onValueChange = { fullBottle = it },
                    label = { Text("Full Bottles") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                OutlinedTextField(
                    value = plastic,
                    onValueChange = { plastic = it },
                    label = { Text("Plastics") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }
        }

        //=======================
        //   SUBMIT BUTTON
        //=======================
        Button(
            onClick = {
                if (name.isBlank()) {
                    Toast.makeText(context, "Please enter a name.", Toast.LENGTH_SHORT).show()
                    return@Button
                }
                //handleSave(name, cash, empties, fulls, fullBottle, plastic, viewModel, navController, context)
                val debtorName: String

                if (name.isBlank()) {
                    Toast.makeText(context, "Enter name!", Toast.LENGTH_SHORT).show()
                } else {
                    debtorName = name
                    val dateAdded = TimeAndDate().GetDateString()
                    val timeAdded = TimeAndDate().GetTimeString()

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
            shape = RoundedCornerShape(14.dp)
        ) {
            Text("Add to Records", fontSize = 17.sp, fontWeight = FontWeight.SemiBold)
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

