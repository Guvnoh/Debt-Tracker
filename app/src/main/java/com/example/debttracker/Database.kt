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
fun removeDebtFromDB(debtor: Debtor){
    debtor.id?.let {  root.child(it).removeValue() }
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