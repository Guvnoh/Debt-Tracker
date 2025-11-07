package com.example.debttracker.models

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.debttracker.formatters.TimeAndDate

enum class DebtType{
    EMPTY_BOTTLE,
    FULLS,
    FULL_BOTTLE,
    PLASTIC,
    EMPTY_CRATE,
    MONEY
}

class DebtClass(
    var itemsOwed: MutableMap<String, Double> = mutableMapOf(),
){
    var timeAdded: String? = null
    var dateAdded: String? = null
    var active: Boolean = true

    fun getDebtTypeString(type: String): String{
        val string =
            when(type) {
                DebtType.EMPTY_CRATE.name -> "Empties"
                DebtType.MONEY.name -> "Cash"
                DebtType.EMPTY_BOTTLE.name -> "Empty bottles"
                DebtType.PLASTIC.name -> "Plastic(s)"
                DebtType.FULLS.name -> "Fulls"
                DebtType.FULL_BOTTLE.name -> "Full bottle(s)"
                else -> "error"
            }
        return string
    }

    fun getDebtTypeCount(): Int{
        var count = 0
        itemsOwed.forEach{ _ -> count+=1}
        return count
    }
    fun checkDebtType(debtType: DebtType): Boolean{
        val debtsList = itemsOwed.map { it.key }
        return debtsList.contains(debtType.name)
    }
    fun getCashBalance(): Double?{
        val debtsList = itemsOwed.map { it.key }
        val cashOwed: Double? = if(debtsList.contains(DebtType.MONEY.name)){
            itemsOwed[DebtType.MONEY.name]
        } else{
            0.0
        }
        return cashOwed
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun addDebt(debtType: DebtType, amount: Double ){
        val key = debtType.name
        val existingAmount:Double? = itemsOwed[key]
        this.dateAdded = TimeAndDate().GetDateString()
        this.timeAdded = TimeAndDate().GetTimeString()
        if (existingAmount!=null){
            val newAmount = existingAmount + amount
            itemsOwed[key] = newAmount
        }else{
            itemsOwed[key] = amount
        }
    }
    fun payDebt(debtType: DebtType, amount: Double){
        var balance = 0.0
        if (itemsOwed[debtType.name]!= null){
           balance = itemsOwed[debtType.name]!! - amount
        }
        if (balance != 0.0){
            itemsOwed[debtType.name] = balance
        }else{
            itemsOwed.remove(debtType.name)
        }
    }
    fun clearDebt(){
        this.itemsOwed = mutableMapOf()
        this.active = false
    }
}