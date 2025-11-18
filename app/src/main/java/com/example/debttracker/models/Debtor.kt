package com.example.debttracker.models

data class Debtor(
    val id: String? = null,
    var timeStamp: Long? = null,
    var name: String? = null,
    val debt: Debt? = null,
    val oldRecords: List<Debt>? = null
)
