package com.example.test2.uidesigns

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.test2.models.Debt
import com.example.test2.models.DebtType
import com.example.test2.navigation.BottomNav
import java.math.BigDecimal

@Composable
fun Records(){
    Scaffold(
        bottomBar = { BottomNav() }
    ) {

        LazyColumn(
            modifier = Modifier
                .padding(it)
                .background(Color.White)
        ) {
            val new = Debt("Mama Ejima")
            new.addDebt(DebtType.MONEY, BigDecimal(8500))
            new.addDebt(DebtType.MONEY, BigDecimal(8500))
            new.payDebt(DebtType.MONEY, BigDecimal(10000))
            val listOfDebtors: List<Debt> = listOf(
                new)
            items(listOfDebtors){
                    Debtor ->
                DebtCard(Debtor)
            }
        }
    }
}