package com.example.debttracker.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.debttracker.models.DebtRecord
import com.example.debttracker.repositories.RecordsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RecordsViewmodel:  ViewModel(){
    var selectedRecord by mutableStateOf<DebtRecord?>(null)
    private val _debtors = MutableStateFlow<List<DebtRecord?>>(emptyList())
    val debtors: StateFlow<List<DebtRecord?>> = _debtors
    private val repo = RecordsRepository

    init {
        getDebtorList()
    }

    private fun getDebtorList(){
        repo.getDBDebtRecords {
                debtMap ->
            _debtors.value = debtMap
        }
    }


}