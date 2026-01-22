package com.example.debttracker.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.debttracker.Screen
import com.example.debttracker.models.DebtRecord
import com.example.debttracker.repositories.RecordsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RecordsViewmodel:  ViewModel(){
    var selectedRecord = mutableStateOf<DebtRecord?>(null)
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
    fun deleteRecord(key: String){
        RecordsRepository.deleteDebtRecord(key)
    }

    fun deleteRecords(list: List<DebtRecord>){
        list.forEach {
            it.id?.let { key -> deleteRecord(key) }
        }
    }

    fun onRecordCardClick(
        selectedItems: MutableList<DebtRecord>,
        record: DebtRecord,
        navController: NavController){
        if (selectedItems.isNotEmpty() && selectedItems.contains(record)) {
            selectedItems.remove(record)
        } else if (selectedItems.isNotEmpty() && !selectedItems.contains(record)) {
            selectedItems.add(record)
        }else{
            selectedRecord.value = record
            navController.navigate(Screen.UpdateDebtScreen.route) {
                launchSingleTop = true
            }
        }
    }

    fun onCardLongClick(selectedItems: MutableList<DebtRecord>, debtRecord: DebtRecord){
        selectedItems.add(debtRecord)
    }


}