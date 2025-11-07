package com.example.debttracker.formatters

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter



@RequiresApi(Build.VERSION_CODES.O)
class TimeAndDate(){
    fun GetDateString(): String{
        val now = LocalDateTime.now()
        val dateFormat = DateTimeFormatter.ofPattern("EEE, MMM dd yyyy")
        return now.format(dateFormat)
    }
    fun GetTimeString(): String{
        val now = LocalDateTime.now()
        val timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss")
        return now.format(timeFormat)
    }
}