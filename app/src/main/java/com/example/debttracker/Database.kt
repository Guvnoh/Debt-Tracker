package com.example.debttracker

import com.example.debttracker.models.Debtor
import com.google.firebase.Firebase
import com.google.firebase.database.database

val DB = Firebase.database
val root = DB.getReference("Boma").child("DebtRecords6")
fun deactivateRecord(debt: Debtor){
    root.child(debt.id?:"").child("debt").child("active").setValue(false)

}
fun reactivateRecord(debt: Debtor){
    root.child(debt.id?:"").child("debt").child("active").setValue(true)

}
fun removeDebtFromDB(key: String){
    //deletes an entire record using the id
    root.child(key).removeValue()
}
fun removeSingleDebt(debtor: Debtor, debt: String){
    //gets the id of the debt record
    val debtId = debtor.id?.let { root.child(it) }
    //finds and removes a single item from a list of debt items in one record
    debtId?.child("debt")?.child("itemsOwed")?.child(debt)?.removeValue()
}
fun deleteDebtRecord(key: String){
    root.child(key).removeValue()
}
fun updateDebtRecord(debt: Debtor){
    val oldDebtList = debt.oldRecords?: emptyList()
    val newDebtList = oldDebtList+debt.debt
    root.child(debt.id!!).child("oldRecords").setValue(newDebtList)
    deleteDebtRecord(debt.id?:"errorNoId")
}