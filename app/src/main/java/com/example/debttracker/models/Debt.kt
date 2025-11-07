package com.example.debttracker.models

data class Debt(
    var timeAdded: String? = null,
    var dateAdded: String? = null,
    var active: Boolean = true,
    var itemsOwed: MutableMap<String, Double>? = null
)
