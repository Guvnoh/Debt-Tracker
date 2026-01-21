package com.example.debttracker

import com.google.firebase.Firebase
import com.google.firebase.database.database

object DatabaseRefs {
    private val db = Firebase.database
    val root = db.getReference("Boma").child("testDebtRecords6")
}