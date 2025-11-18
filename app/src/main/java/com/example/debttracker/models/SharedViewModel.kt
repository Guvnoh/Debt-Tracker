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


    fun updateDebtRecord(
        item: String,
        quantity: Double
        ){


        selectedRecord?.debt?.itemsOwed?.set(item, quantity)


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