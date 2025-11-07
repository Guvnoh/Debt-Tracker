package com.example.debttracker

import com.google.firebase.Firebase
import com.google.firebase.database.database

val DB = Firebase.database
val root = DB.getReference("Boma").child("DebtRecords")