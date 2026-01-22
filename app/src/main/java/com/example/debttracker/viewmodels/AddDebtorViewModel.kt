package com.example.debttracker.viewmodels

import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.debttracker.DatabaseRefs
import com.example.debttracker.formatters.TimeAndDate
import com.example.debttracker.formatters.halfAndQuarter
import com.example.debttracker.formatters.moneyFormat
import com.example.debttracker.models.Debt
import com.example.debttracker.models.RowData
import com.example.debttracker.models.RowType
import com.google.firebase.database.ServerValue
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AddDebtorViewModel: ViewModel() {

    private var _customerName: MutableState<String> = mutableStateOf("")
    val customerName: State<String> = _customerName

    private var _cash: MutableState<String> = mutableStateOf("")
    val cash: State<String> = _cash

    private var _bottleRows: MutableStateFlow<List<RowData>> = MutableStateFlow(
        listOf(RowData("", "")))
    val bottleRows: StateFlow<List<RowData>> = _bottleRows

    private var _emptiesRows: MutableStateFlow<List<RowData>> = MutableStateFlow(
        listOf(RowData("", "")))
    val emptiesRows: StateFlow<List<RowData>> = _emptiesRows

    private var _plasticRows: MutableStateFlow<List<RowData>> = MutableStateFlow(
        listOf(RowData("", "")))
    val plasticRows: StateFlow<List<RowData>> = _plasticRows

    private var _newDebt: MutableStateFlow<Debt> = MutableStateFlow(Debt())
    val newDebt: StateFlow<Debt> = _newDebt

    fun setCustomerName(name: String){
        _customerName.value = name
    }

    fun setCashAmount(amount:String){
        _cash.value = amount
    }

    private fun clearDetails(index: Int, type: RowType){
        if ( type == RowType.Empties){
            val newList = _emptiesRows.value.toMutableList()
            newList[index] = RowData(qty = "", name = "")
            _emptiesRows.value = newList
        }else{
            val newList = _bottleRows.value.toMutableList()
            newList[index] = RowData(qty = "", name = "")
            _bottleRows.value = newList
        }
    }
    fun clearAll(){
        _customerName.value = ""
        _cash.value = ""
        _emptiesRows.value = listOf(RowData("",""))
        _bottleRows.value = listOf(RowData("",""))
        _plasticRows.value = listOf(RowData("",""))
    }

//    fun setNewDebt(debt: Debt){
//        _newDebt.value = debt
//    }


    fun updateRowQty(rowType: RowType, index: Int, qty: String){
        when (rowType){
            RowType.Empties -> {
                val newList = _emptiesRows.value.toMutableList().also {
                    it[index] = it[index].copy(qty = qty)
                }
                _emptiesRows.value = newList
            }

            RowType.Bottles -> {
                val newList = _bottleRows.value.toMutableList().also {
                    it[index] = it[index].copy(qty = qty)
                }
                _bottleRows.value = newList
            }

            RowType.Plastics -> {
                val newList = _plasticRows.value.toMutableList().also {
                    it[index] = it[index].copy(qty = qty)
                }
                _plasticRows.value = newList
            }
        }


    }

    fun updateRowName(rowType: RowType, name: String, index: Int){
        when (rowType) {
            RowType.Empties -> {
                val newList = _emptiesRows.value.toMutableList().also {
                    it[index] = it[index].copy(name = name)
                }
                _emptiesRows.value = newList
            }
            RowType.Bottles -> {
                val newList = _bottleRows.value.toMutableList().also {
                    it[index] = it[index].copy(name = name)
                }
                _bottleRows.value = newList
            }
            RowType.Plastics -> {
                val newList = _plasticRows.value.toMutableList().also {
                    it[index] = it[index].copy(name = name)
                }
                _plasticRows.value = newList
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun createRecord(context: Context ): Debt {
        if (_customerName.value.isBlank()) {
            Toast.makeText(context, "Please enter a name.", Toast.LENGTH_SHORT).show()
            return Debt()
        } else {
            val dateAdded = TimeAndDate().GetDateString()
            val timeAdded = TimeAndDate().GetTimeString()

            //start
            val items = mutableMapOf<String, String>()

            fun addItem(key: String, value: String) {
                //removes commas (from cash), then checks and removes null or zero values
                //creates key value pair (name of item & qty owed) and adds them to items map (items owed)
                val n = value.replace(",", "").toDoubleOrNull()
                if (n != null && n > 0) items[key] = moneyFormat(n)
            }

            addItem("Cash", cash.value)

            fun addRowItems(row: List<RowData>, suffix: String){
                row.forEach {
                    val qty = it.qty.toDoubleOrNull()?:0.0
                    if (qty>0) items["${it.name} $suffix"] = halfAndQuarter(qty)
                }

            }
            addRowItems(_bottleRows.value, "bottles")
            addRowItems(_emptiesRows.value, "empties")
            addRowItems(_plasticRows.value, "plastics")

            val debt = Debt(
                dateAdded = dateAdded,
                timeAdded = timeAdded,
                itemsOwed = items
            )
            return debt

        }
    }
    fun saveRecord(debt: Debt, context: Context){
        val recordRoot = DatabaseRefs.root.push()
        val key = recordRoot.key
        val debtorToMap =
            mutableMapOf(
                "id" to key,
                "timeStamp" to ServerValue.TIMESTAMP,
                "name" to _customerName.value,
                "debt" to debt
            )
        recordRoot.setValue(debtorToMap)
        Toast.makeText(context, "Debt record added!", Toast.LENGTH_SHORT).show()

    }

    fun addNewRow(rowType: RowType){
        val row = RowData(qty = "", name = "")
        when (rowType){
            RowType.Bottles -> {
                val newRowList = _bottleRows.value + row
                _bottleRows.value = newRowList
            }
            RowType.Empties -> {
                val newRowList = _emptiesRows.value + row
                _emptiesRows.value = newRowList
            }
            RowType.Plastics -> {
                val newRowList = _plasticRows.value + row
                _plasticRows.value = newRowList
            }
        }
    }

    fun removeRow(index: Int, rowType: RowType){
        when(rowType){

            RowType.Bottles -> {
                if (_bottleRows.value.size >1){
                    val newList = _bottleRows.value - _bottleRows.value[index]
                    _bottleRows.value = newList

                }else clearDetails(index, rowType)
            }

            RowType.Empties -> {
                if (_emptiesRows.value.size >1){
                    val newList = _emptiesRows.value - _emptiesRows.value[index]
                    _emptiesRows.value = newList

                }else clearDetails(index, rowType)
            }

            RowType.Plastics -> {
                if (_plasticRows.value.size>1){
                    val newList = _plasticRows.value - _plasticRows.value[index]
                    _plasticRows.value = newList

                }else clearDetails(index, rowType)
            }
        }
    }


    fun updateNewDebt(
        item: String,
        quantity: String
        ){
        _newDebt.value.itemsOwed?.set(item, quantity)
    }




}