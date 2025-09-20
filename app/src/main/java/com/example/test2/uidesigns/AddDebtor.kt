package com.example.test2.uidesigns

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import java.math.BigDecimal

@Composable

fun AddDebt(){
    var amount by remember { mutableStateOf(BigDecimal  (0)) }
    Column {
        TextField(
            value = amount.toString(),
            onValueChange = {amount = it.toBigDecimal()},
            maxLines = 1,
            )
    }

}