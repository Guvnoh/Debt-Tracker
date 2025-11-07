package com.example.debttracker.models

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.debttracker.root
import com.example.debttracker.uidesigns.Debtor
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SharedViewModel: ViewModel() {
    var selectedRecord by mutableStateOf<Debtor?>(null)
    private val _debtors = MutableStateFlow<List<Debtor?>>(emptyList())
    val debtors: StateFlow<List<Debtor?>> = getDebtorList()

    private fun getDebtorList(): MutableStateFlow<List<Debtor?>>{
        getDBDebtRecords {
            debtList ->
            _debtors.value = debtList
        }

        return _debtors
    }

    private fun getDBDebtRecords(onListReady: (MutableList<Debtor?>) -> Unit){
        root.addValueEventListener( object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val debtRecords = mutableListOf<Debtor?>()
                for (record in snapshot.children){
                    val debtRecord = record.getValue(Debtor::class.java)
                    debtRecords.add(debtRecord)
                }
                onListReady(debtRecords)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("Database_Error!",  "$error")
            }

        }
        )
    }
}