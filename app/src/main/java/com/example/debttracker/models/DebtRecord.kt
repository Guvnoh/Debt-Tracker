package com.example.debttracker.models

data class DebtRecord(
    var id: String? = null,
    val ref: String? = null,
    var timeStamp: Long? = null,
    var name: String? = null,
    var notes: String? = null,
    var debt: Debt? = null,
    var oldRecords: List<Debt>? = null
)
