package com.example.debttracker

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.debttracker.navigation.BottomNav
import com.example.debttracker.ui.theme.DebtTrackerTheme
import com.example.debttracker.viewmodels.AddDebtorViewModel
import com.example.debttracker.viewmodels.RecordsViewmodel

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val addDebtorViewModel: AddDebtorViewModel = viewModel()
            val recordsViewmodel: RecordsViewmodel = viewModel()
            DebtTrackerTheme {
                BottomNav(
                    addDebtorViewModel = addDebtorViewModel,
                    recordsViewModel = recordsViewmodel
                    )
            }
        }
    }
}

