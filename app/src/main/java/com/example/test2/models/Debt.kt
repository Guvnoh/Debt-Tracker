package com.example.test2.models

import java.math.BigDecimal

enum class DebtType{
    EMPTY_BOTTLE,
    FULLS,
    FULL_BOTTLE,
    PLASTIC,
    EMPTIES,
    MONEY
}

class Debt (
    val debtor: String,
    var itemsOwed: MutableMap<DebtType, BigDecimal> = mutableMapOf(),
){

    fun getDebtTypeCount(): Int{
        var count = 0
        itemsOwed.forEach{ _ -> count+=1}
        return count
    }
    fun checkDebtType(debtType: DebtType): Boolean{
        val debtsList = itemsOwed.map { it.key }
        return debtsList.contains(debtType)
    }
    fun getCashBalance(): BigDecimal?{
        val debtsList = itemsOwed.map { it.key }
        val cashOwed: BigDecimal? = if(debtsList.contains(DebtType.MONEY)){
            itemsOwed[DebtType.MONEY]
        } else{
            BigDecimal(0)
        }
        return cashOwed
    }
    fun addDebt(debtType: DebtType, amount: BigDecimal ){
        val existingAmount:BigDecimal? = itemsOwed[debtType]
        if (existingAmount!=null){
            val newAmount = existingAmount + amount
            itemsOwed[debtType] = newAmount
        }else{
            itemsOwed[debtType] = amount
        }
    }
    fun payDebt(debtType: DebtType, amount: BigDecimal){
        var balance = BigDecimal(0)
        if (itemsOwed[debtType]!= null){
           balance = itemsOwed[debtType]!! - amount
        }
        if (balance != BigDecimal(0)){
            itemsOwed[debtType] = balance
        }else{
            itemsOwed.remove(debtType)
        }
    }
    fun clearDebt(){
        this.itemsOwed = mutableMapOf()
    }
}