package com.example.test2.uidesigns

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.test2.formatters.moneyFormat
import com.example.test2.models.Debt
import com.example.test2.models.DebtType
import java.math.BigDecimal

@Composable
fun ClearDebt(debt: Debt){
    Column (
        modifier = Modifier.fillMaxSize()
    ){
        Text(
            text = "Update debt status."
        )
        val debtTypeList = debt.itemsOwed.map{it.key}
        val debtMap = debt.itemsOwed
        debtTypeList.forEach{
            val amount = debtMap[it]
            when(it){
                DebtType.MONEY -> Text(text = "Cash owed: ${moneyFormat(amount)}")
                DebtType.EMPTY_BOTTLE -> Text("Bottles owed: $amount")
                DebtType.EMPTIES -> Text("Empties balance: $amount")
                else -> Text("stuff owed: $amount")

            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun ClearDebtDemo(){
    val new = Debt("Mama Ejima")
    new.addDebt(DebtType.MONEY, BigDecimal(8500))
    new.addDebt(DebtType.MONEY, BigDecimal(8500))
    new.payDebt(DebtType.MONEY, BigDecimal(10000))
    new.addDebt(DebtType.EMPTY_BOTTLE, BigDecimal(6))
    ClearDebt(new)
}