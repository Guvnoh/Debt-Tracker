package com.example.debttracker.uidesigns

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.debttracker.models.BottomNavItem
import com.example.debttracker.models.SharedViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Records(vm: SharedViewModel, navController: NavController){
    val debtRecords by vm.debtors.collectAsState()

    if (debtRecords.isEmpty()){
        Column (
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally){
            Text(
                text = "No debt recorded yet!",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.primary
            )
            Button(onClick = {
                navController.navigate(BottomNavItem.Add_Record.route) {
                    launchSingleTop = true
                }}) {
                Text("Add new record")
            }
        }

    }else{
        LazyColumn(
            modifier = Modifier
                .background(Color.White)
        ) {

            items(debtRecords){
                    debtor ->
                DebtCard(vm = vm, debtor = debtor, navC = navController)
            }
        }
    }


}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun ShowRecords(){
    Records(vm = viewModel(), navController = rememberNavController())
}