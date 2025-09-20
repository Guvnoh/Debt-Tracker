package com.example.test2.uidesigns

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.test2.models.Debt
import com.example.test2.models.DebtType
import com.example.test2.navigation.BottomNav
import java.math.BigDecimal

@Composable
fun MainScreen(){
    Records()

}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview(){
    MainScreen()
}
