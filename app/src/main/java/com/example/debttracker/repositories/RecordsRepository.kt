package com.example.debttracker.repositories

import android.util.Log
import com.example.debttracker.DatabaseRefs
import com.example.debttracker.models.DebtRecord
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

object RecordsRepository {
    val recordsDB = DatabaseRefs.root

    fun deactivateRecord(debt: DebtRecord){
        recordsDB.child(debt.id?:"").child("debt").child("active").setValue(false)

    }
    fun reactivateRecord(debt: DebtRecord){
        recordsDB.child(debt.id?:"").child("debt").child("active").setValue(true)

    }

    fun removeSingleDebt(debtRecord: DebtRecord, debt: String){
        //gets the id of the debt record
        val debtId = debtRecord.id?.let { recordsDB.child(it) }
        //finds and removes a single item from a list of debt items in one record
        debtId?.child("debt")?.child("itemsOwed")?.child(debt)?.removeValue()
    }
    fun deleteDebtRecord(key: String){
        //deletes an entire record using the id
        recordsDB.child(key).removeValue()
    }
    fun updateDebtRecord(debt: DebtRecord){
        val oldDebtList = debt.oldRecords?: emptyList()
        val newDebtList = oldDebtList+debt.debt
        recordsDB.child(debt.id!!).child("oldRecords").setValue(newDebtList)
        deleteDebtRecord(debt.id?:"errorNoId")
    }

    fun reduceItemQuantity(debtRecord: DebtRecord, itemKey: String, newValue: String) {
        val id = debtRecord.id ?: return
        val ref = recordsDB.child(id)

        // Snapshot current debt to oldRecords
        val oldDebtList = debtRecord.oldRecords ?: emptyList()
        val newDebtList = oldDebtList + debtRecord.debt
        ref.child("oldRecords").setValue(newDebtList)

        // Update or remove the item
        if (newValue.isBlank() || newValue == "0" || newValue == "₦0") {
            ref.child("debt").child("itemsOwed").child(itemKey).removeValue()
        } else {
            ref.child("debt").child("itemsOwed").child(itemKey).setValue(newValue)
        }
    }
    fun getDBDebtRecords(onListReady: (List<DebtRecord?>) -> Unit){
        recordsDB.orderByChild("timeStamp").addValueEventListener( object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                val debtRecords = snapshot.children.mapNotNull{
                    it.getValue(DebtRecord::class.java)
                }.sortedByDescending { it.timeStamp }
                onListReady(debtRecords)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("Database_Error!",  "$error")
            }

        }
        )
    }
}