package com.example.debttracker.models

data class Debtor(
    var id: String? = null,
    var timeStamp: Long? = null,
    var name: String? = null,
    var debt: Debt? = null,
    var oldRecords: List<Debt>? = null
)
