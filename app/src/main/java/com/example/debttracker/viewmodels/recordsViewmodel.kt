package com.example.debttracker.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.debttracker.Screen
import com.example.debttracker.models.DebtRecord
import com.example.debttracker.repositories.RecordsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine

class RecordsViewmodel : ViewModel() {
    var selectedRecord = mutableStateOf<DebtRecord?>(null)
    private val _debtors = MutableStateFlow<List<DebtRecord?>>(emptyList())
    val debtors: StateFlow<List<DebtRecord?>> = _debtors
    private val repo = RecordsRepository

    // Search
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    // Filtered list
    private val _filteredDebtors = MutableStateFlow<List<DebtRecord?>>(emptyList())
    val filteredDebtors: StateFlow<List<DebtRecord?>> = _filteredDebtors

    // Summary stats
    private val _totalRecords = MutableStateFlow(0)
    val totalRecords: StateFlow<Int> = _totalRecords

    private val _activeCount = MutableStateFlow(0)
    val activeCount: StateFlow<Int> = _activeCount

    private val _inactiveCount = MutableStateFlow(0)
    val inactiveCount: StateFlow<Int> = _inactiveCount

    private val _totalCashOwed = MutableStateFlow(0.0)
    val totalCashOwed: StateFlow<Double> = _totalCashOwed

    init {
        getDebtorList()
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
        applyFilter()
    }

    private fun applyFilter() {
        val query = _searchQuery.value.trim()
        val all = _debtors.value
        _filteredDebtors.value = if (query.isBlank()) {
            all
        } else {
            all.filter { record ->
                record?.name?.contains(query, ignoreCase = true) == true
            }
        }
    }

    private fun updateStats() {
        val records = _debtors.value.filterNotNull()
        _totalRecords.value = records.size
        _activeCount.value = records.count { it.debt?.active != false }
        _inactiveCount.value = records.count { it.debt?.active == false }
        _totalCashOwed.value = records.sumOf { record ->
            val cashStr = record.debt?.itemsOwed?.get("Cash") ?: "0"
            cashStr.replace(",", "").toDoubleOrNull() ?: 0.0
        }
    }

    private fun getDebtorList() {
        repo.getDBDebtRecords { debtMap ->
            _debtors.value = debtMap
            applyFilter()
            updateStats()
        }
    }

    fun deleteRecord(key: String) {
        RecordsRepository.deleteDebtRecord(key)
    }

    fun deleteRecords(list: List<DebtRecord>) {
        list.forEach {
            it.id?.let { key -> deleteRecord(key) }
        }
    }

    fun onRecordCardClick(
        selectedItems: MutableList<DebtRecord>,
        record: DebtRecord,
        navController: NavController
    ) {
        if (selectedItems.isNotEmpty() && selectedItems.contains(record)) {
            selectedItems.remove(record)
        } else if (selectedItems.isNotEmpty() && !selectedItems.contains(record)) {
            selectedItems.add(record)
        } else {
            selectedRecord.value = record
            navController.navigate(Screen.UpdateDebtScreen.route) {
                launchSingleTop = true
            }
        }
    }

    fun onCardLongClick(selectedItems: MutableList<DebtRecord>, debtRecord: DebtRecord) {
        selectedItems.add(debtRecord)
    }
}
