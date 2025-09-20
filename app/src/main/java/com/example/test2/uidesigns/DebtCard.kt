package com.example.test2.uidesigns

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.test2.formatters.moneyFormat
import com.example.test2.models.Debt
import com.example.test2.models.DebtType
import java.math.BigDecimal

@Composable
fun DebtCard(debt: Debt){
    var clearDebt by remember { mutableStateOf(false) }
    if (clearDebt){
        ClearDebt(debt)
    }
    Card (
        modifier = Modifier
            .height(100.dp)
            .fillMaxWidth()
            .fillMaxSize(),
        colors = CardDefaults.cardColors(containerColor = Color.Red),
        onClick = {
            clearDebt = !clearDebt
        }
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Row (modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically){
                Text(
                    text = debt.debtor,
                    fontSize = 20.sp,
                )
            }
            val cashOwed by remember { mutableStateOf(moneyFormat(debt.getCashBalance())) }

            if(debt.checkDebtType(DebtType.MONEY)){
                Text(
                    text = "Cash owed: $cashOwed",
                    fontSize = 20.sp
                )
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun DebtCardPreview(){
    val new = Debt("Mama Ejima")
    new.addDebt(DebtType.MONEY, BigDecimal(8500))
    new.addDebt(DebtType.MONEY, BigDecimal(8500))
    new.payDebt(DebtType.MONEY, BigDecimal(10000))
    DebtCard(new)

}