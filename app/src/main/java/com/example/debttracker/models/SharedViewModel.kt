package com.example.debttracker.models

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.debttracker.root
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SharedViewModel: ViewModel() {
    var selectedRecord by mutableStateOf<Debtor?>(null)
    private val _debtors = MutableStateFlow<List<Debtor?>>(emptyList())
    val debtors: StateFlow<List<Debtor?>> = getDebtorList()

    private var _rows: MutableStateFlow<List<BottleRow>> = MutableStateFlow(
        listOf(BottleRow("","")))
    val rows: StateFlow<List<BottleRow>> = _rows
    private var _newDebt: MutableStateFlow<Debt> = MutableStateFlow(Debt())
    val newDebt: StateFlow<Debt> = _newDebt

    fun clearDetails(){
       _rows.value = _rows.value.map{
        it.copy(qty = "", type = "")
       }
    }

    fun setNewDebt(debt: Debt){
        _newDebt.value = debt
    }

    fun updateRowQty(index: Int, qty: String){
        _rows.value = _rows.value.toMutableList().also {
         it[index] = it[index].copy(qty = qty)
        }
    }

    fun updateRowType(index: Int,type: String){
        _rows.value = _rows.value.toMutableList().also {
            it[index] = it[index].copy(type = type)
        }
    }

    fun addNewRow(row: BottleRow): MutableStateFlow<List<BottleRow>>{
        val newRow = _rows.value + row
        _rows.value = newRow
        return _rows
    }
    fun removeRow(index: Int): MutableStateFlow<List<BottleRow>>{
        val newRows = if (_rows.value.size>1){
            _rows.value - _rows.value[index]
        } else {
            clearDetails()
            _rows.value
        }
        _rows.value =newRows
        return _rows
    }


    fun updateNewDebt(
        item: String,
        quantity: Double
        ){
        _newDebt.value.itemsOwed?.set(item, quantity)
    }

    private fun getDebtorList(): MutableStateFlow<List<Debtor?>>{
        getDBDebtRecords {
            debtMap ->
            _debtors.value = debtMap
        }

        return _debtors
    }

    private fun getDBDebtRecords(onListReady: (List<Debtor?>) -> Unit){
        root.orderByChild("timeStamp").addValueEventListener( object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                val debtors = snapshot.children.mapNotNull{
                    it.getValue(Debtor::class.java)
                }.sortedByDescending { it.timeStamp }
                onListReady(debtors)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("Database_Error!",  "$error")
            }

        }
        )
    }
}