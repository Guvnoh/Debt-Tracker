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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.debttracker.models.BottomNavItem
import com.example.debttracker.models.DebtClass
import com.example.debttracker.models.DebtType
import com.example.debttracker.root

@RequiresApi(Build.VERSION_CODES.O)
@Composable

fun AddDebt(navController: NavController){
    var name by remember { mutableStateOf("") }
    var cash by remember { mutableStateOf("") }
    var empties by remember { mutableStateOf("") }
    var bottles by remember { mutableStateOf("") }
    var fulls by remember { mutableStateOf("") }
    var fullBottle by remember { mutableStateOf("") }
    var plastic by remember { mutableStateOf("") }

    val entriesList = listOf(
        name, cash, empties, bottles, fulls, fullBottle, plastic
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

                OutlinedTextField(
                    value = bottles,
                    onValueChange = { bottles = it },
                    label = { Text("Enter no. of bottles") },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                )
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
                        .fillMaxWidth()
                )
                Button(
                    onClick = {
                        val debtorName: String

                        if (name.isBlank()) {
                            Toast.makeText(context, "Enter name!", Toast.LENGTH_SHORT).show()
                        } else {
                            debtorName = name
                            val debt = DebtClass()
                            entriesList.forEach {
                                //sorted cash makes sure cash entries with commas don't cause errors
                                val sortedAmount =
                                //This ensures that the 'name' entries retain their original format
                                //while the other entries e.g cash and empties are formatted
                                    //to remove commas and unnecessary spaces
                                    if (it!=name)it.replace("[, ]".toRegex(), "")else it
                                if (it.isNotBlank()){
                                    when(it){
                                        cash -> debt.addDebt(DebtType.MONEY, sortedAmount.toDoubleOrNull()?:0.0)
                                        empties -> debt.addDebt(DebtType.EMPTY_CRATE, empties.toDoubleOrNull()?:0.0)
                                        bottles -> debt.addDebt(DebtType.EMPTY_BOTTLE, bottles.toDoubleOrNull()?:0.0)
                                        fullBottle -> debt.addDebt(DebtType.FULL_BOTTLE, fullBottle.toDoubleOrNull()?:0.0)
                                        plastic -> debt.addDebt(DebtType.PLASTIC, plastic.toDoubleOrNull()?:0.0)
                                        fulls -> debt.addDebt(DebtType.FULLS, fulls.toDoubleOrNull()?:0.0)
                                    }
                                }
                            }

                            navController.navigate(BottomNavItem.Records.route)
                            saveRecord(Debtor(debtorName, debt))
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
    val key:String = debtor.name?.lowercase()?:"noname"
    root.child(key).setValue(debtor)

}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun AddDebtPreview(){
    AddDebt(navController = rememberNavController())
}