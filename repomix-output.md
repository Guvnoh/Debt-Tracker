This file is a merged representation of the entire codebase, combined into a single document by Repomix.

# File Summary

## Purpose
This file contains a packed representation of the entire repository's contents.
It is designed to be easily consumable by AI systems for analysis, code review,
or other automated processes.

## File Format
The content is organized as follows:
1. This summary section
2. Repository information
3. Directory structure
4. Repository files (if enabled)
5. Multiple file entries, each consisting of:
  a. A header with the file path (## File: path/to/file)
  b. The full contents of the file in a code block

## Usage Guidelines
- This file should be treated as read-only. Any changes should be made to the
  original repository files, not this packed version.
- When processing this file, use the file path to distinguish
  between different files in the repository.
- Be aware that this file may contain sensitive information. Handle it with
  the same level of security as you would the original repository.

## Notes
- Some files may have been excluded based on .gitignore rules and Repomix's configuration
- Binary files are not included in this packed representation. Please refer to the Repository Structure section for a complete list of file paths, including binary files
- Files matching patterns in .gitignore are excluded
- Files matching default ignore patterns are excluded
- Files are sorted by Git change count (files with more changes are at the bottom)

# Directory Structure
```
.gitignore
app/.gitignore
app/build.gradle.kts
app/google-services (1).json
app/google-services.json
app/proguard-rules.pro
app/src/androidTest/java/com/example/debttracker/ExampleInstrumentedTest.kt
app/src/main/AndroidManifest.xml
app/src/main/ic_launcher-playstore.png
app/src/main/java/com/example/debttracker/DatabaseRefs.kt
app/src/main/java/com/example/debttracker/formatters/HalfAndQuarter.kt
app/src/main/java/com/example/debttracker/formatters/MoneyFormatter.kt
app/src/main/java/com/example/debttracker/formatters/TextFormatter.kt
app/src/main/java/com/example/debttracker/formatters/TimeFormatter.kt
app/src/main/java/com/example/debttracker/MainActivity.kt
app/src/main/java/com/example/debttracker/models/AddDebtorViewModel.kt
app/src/main/java/com/example/debttracker/models/BottleType.kt
app/src/main/java/com/example/debttracker/models/BottomNav.kt
app/src/main/java/com/example/debttracker/models/CustomAlertDialog.kt
app/src/main/java/com/example/debttracker/models/Debt.kt
app/src/main/java/com/example/debttracker/models/Debtor.kt
app/src/main/java/com/example/debttracker/models/DebtType.kt
app/src/main/java/com/example/debttracker/models/EmptyType.kt
app/src/main/java/com/example/debttracker/models/ItemRow.kt
app/src/main/java/com/example/debttracker/models/OptionsMenu.kt
app/src/main/java/com/example/debttracker/navigation/Navigation.kt
app/src/main/java/com/example/debttracker/repositories/RecordsRepository.kt
app/src/main/java/com/example/debttracker/ScreenClass.kt
app/src/main/java/com/example/debttracker/ui/theme/Color.kt
app/src/main/java/com/example/debttracker/ui/theme/Theme.kt
app/src/main/java/com/example/debttracker/ui/theme/Type.kt
app/src/main/java/com/example/debttracker/uidesigns/AddDebtor.kt
app/src/main/java/com/example/debttracker/uidesigns/DebtCard.kt
app/src/main/java/com/example/debttracker/uidesigns/RecordCard.kt
app/src/main/java/com/example/debttracker/uidesigns/Records.kt
app/src/main/java/com/example/debttracker/uidesigns/UpdateDebtStatus.kt
app/src/main/java/com/example/debttracker/viewmodels/recordsViewmodel.kt
app/src/main/res/drawable/bgimg.jpeg
app/src/main/res/drawable/ic_launcher_background.xml
app/src/main/res/drawable/ic_launcher_foreground.xml
app/src/main/res/mipmap-anydpi-v26/ic_launcher_round.xml
app/src/main/res/mipmap-anydpi-v26/ic_launcher.xml
app/src/main/res/mipmap-hdpi/ic_launcher_foreground.webp
app/src/main/res/mipmap-hdpi/ic_launcher_round.webp
app/src/main/res/mipmap-hdpi/ic_launcher.webp
app/src/main/res/mipmap-mdpi/ic_launcher_foreground.webp
app/src/main/res/mipmap-mdpi/ic_launcher_round.webp
app/src/main/res/mipmap-mdpi/ic_launcher.webp
app/src/main/res/mipmap-xhdpi/ic_launcher_foreground.webp
app/src/main/res/mipmap-xhdpi/ic_launcher_round.webp
app/src/main/res/mipmap-xhdpi/ic_launcher.webp
app/src/main/res/mipmap-xxhdpi/ic_launcher_foreground.webp
app/src/main/res/mipmap-xxhdpi/ic_launcher_round.webp
app/src/main/res/mipmap-xxhdpi/ic_launcher.webp
app/src/main/res/mipmap-xxxhdpi/ic_launcher_foreground.webp
app/src/main/res/mipmap-xxxhdpi/ic_launcher_round.webp
app/src/main/res/mipmap-xxxhdpi/ic_launcher.webp
app/src/main/res/values/colors.xml
app/src/main/res/values/strings.xml
app/src/main/res/values/themes.xml
app/src/main/res/xml/backup_rules.xml
app/src/main/res/xml/data_extraction_rules.xml
app/src/test/java/com/example/debttracker/ExampleUnitTest.kt
build.gradle.kts
gradle.properties
gradle/libs.versions.toml
gradle/wrapper/gradle-wrapper.jar
gradle/wrapper/gradle-wrapper.properties
gradlew
gradlew.bat
settings.gradle.kts
```

# Files

## File: app/src/main/java/com/example/debttracker/DatabaseRefs.kt
```kotlin
package com.example.debttracker

import com.google.firebase.Firebase
import com.google.firebase.database.database

object DatabaseRefs {
    private val db = Firebase.database
    val root = db.getReference("Boma").child("testDebtRecords6")
}
```

## File: app/src/main/java/com/example/debttracker/models/AddDebtorViewModel.kt
```kotlin
package com.example.debttracker.models

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AddDebtorViewModel: ViewModel() {


    private var _bottleRows: MutableStateFlow<List<ItemRow>> = MutableStateFlow(
        listOf(ItemRow("","")))
    val bottleRows: StateFlow<List<ItemRow>> = _bottleRows

    private var _emptiesRows: MutableStateFlow<List<ItemRow>> = MutableStateFlow(
        listOf(ItemRow("","")))
    val emptiesRows: StateFlow<List<ItemRow>> = _bottleRows

    private var _newDebt: MutableStateFlow<Debt> = MutableStateFlow(Debt())
    val newDebt: StateFlow<Debt> = _newDebt

    fun checkItem(list: String){

    }

    fun clearDetails(item: ItemType){
        if ( item== ItemType.empties ){
            _emptiesRows.value = _emptiesRows.value.map {
                it.copy(qty = "", type = "")
            }
        }else{
            _bottleRows.value = _bottleRows.value.map{
                it.copy(qty = "", type = "")
            }
        }
    }

    fun setNewDebt(debt: Debt){
        _newDebt.value = debt
    }

    fun updateRowQty(index: Int, qty: String, list: String){
        if (list == "empties"){
            val newList = _emptiesRows.value.toMutableList().also {
                it[index] = it[index].copy(qty = qty)
            }
            _emptiesRows.value = newList
        } else if (list == "bottles"){
            val newList = _bottleRows.value.toMutableList().also {
                it[index] = it[index].copy(qty = qty)
            }
            _bottleRows.value = newList
        }

    }

    fun updateRowType(index: Int, type: ItemType, name: String=""){
        if (type == ItemType.empties){
            val newList = _emptiesRows.value.toMutableList().also {
                it[index] = it[index].copy(type = name)
            }
            _emptiesRows.value = newList
        } else if (type == ItemType.bottles){
            val newList = _bottleRows.value.toMutableList().also {
                it[index] = it[index].copy(type = name)
            }
            _bottleRows.value = newList
        }
    }

    fun addNewRow(row: ItemRow, item: ItemType): MutableStateFlow<List<ItemRow>>{
        return if (item == ItemType.bottles){
            val newRowList = _bottleRows.value + row
            _bottleRows.value = newRowList
            _bottleRows
        } else {
            val newRowList = _emptiesRows.value + row
            _emptiesRows.value = newRowList
             _emptiesRows
        }
    }
    fun removeRow(index: Int, item: ItemType): MutableStateFlow<List<ItemRow>>{
        return when(item){
            ItemType.bottles -> {
                if (_bottleRows.value.size >1){
                    val newList = _bottleRows.value - _bottleRows.value[index]
                    _bottleRows.value = newList
                    _bottleRows
                }else {
                    clearDetails(item)
                    _bottleRows
                }
            }else -> {
                if (_emptiesRows.value.size >1){
                    val newList = _emptiesRows.value - _emptiesRows.value[index]
                    _emptiesRows.value = newList
                    _emptiesRows
                }else{
                    clearDetails(item)
                    _emptiesRows
                }
            }
        }
    }


    fun updateNewDebt(
        item: String,
        quantity: Double
        ){
        _newDebt.value.itemsOwed?.set(item, quantity)
    }




}
```

## File: app/src/main/java/com/example/debttracker/models/EmptyType.kt
```kotlin
package com.example.debttracker.models

enum class EmptyType {
    Cocacola,
    Hero,
    Nbl,
    Guinness,
    Orijin,
}
```

## File: app/src/main/java/com/example/debttracker/models/ItemRow.kt
```kotlin
package com.example.debttracker.models

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddItemRow(
    index: Int,
    vm: AddDebtorViewModel,
    item: ItemType,

) {

    var expanded by remember { mutableStateOf(false) }
    //val row = vm.bottleRows.collectAsState().value[index]
    val empties = EmptyType.entries.map { it.toString().lowercase() }
    val bottles = BottleType.entries.map { it.toString() }
    val itemsList = remember { mutableStateOf(listOf<String>())}
    if ( item == ItemType.empties){
        itemsList.value = empties
    }else itemsList.value = bottles
    val row = when(item){
        ItemType.bottles -> vm.bottleRows.collectAsState().value[index]
        else ->  vm.emptiesRows.collectAsState().value[index]
    }


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)     // TextField decides height
            .padding(6.dp)
            .border(1.dp, Color.Gray.copy(alpha = 0.4f), shape = RectangleShape)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        // ===============================
        // Quantity Field
        // ===============================
        OutlinedTextField(
            value = row.qty,
            onValueChange = {
                if (itemsList == empties){
                    vm.updateRowQty(index, it, "empties")
                }else {
                    vm.updateRowQty(index, it, "bottles" )
                }
            },
            label = { Text("Qty") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .weight(0.6f)
                .fillMaxHeight()
        )

        Spacer(modifier = Modifier.size(8.dp))

        // ===============================
        // Bottle Type (Dropdown)
        // ===============================
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
            modifier = Modifier.weight(1f)
        ) {

            if (itemsList == empties){
                vm.updateRowType(index, ItemType.empties,)
            }else {
                vm.updateRowType(index, ItemType.bottles )
            }
            OutlinedTextField(
                value = row.type,
                onValueChange = {

                },
                label = {
                    Text(
                        text = "Item Type",
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,

                    ) },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded) },
                singleLine = true,
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxHeight()
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                if (itemsList == bottles){
                    BottleType.entries.forEach { selected ->
                        DropdownMenuItem(
                            enabled = row.qty.toDoubleOrNull() != null,
                            text = { Text(selected.name) },
                            onClick = {
                                vm.updateRowType(index, ItemType.bottles, selected.name)
                                expanded = false
                            }
                        )
                    }
                }else{
                    EmptyType.entries.forEach { selected ->
                        DropdownMenuItem(
                            enabled = row.qty.toDoubleOrNull() != null,
                            text = { Text(selected.name) },
                            onClick = {
                                vm.updateRowType(index, ItemType.empties, selected.name)
                                expanded = false
                            }
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.size(10.dp))

//Column for "add" and "remove" icon buttons
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .width(48.dp),                // FIX: prevents overlapping
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
//Remove button
            IconButton(
                onClick = { vm.removeRow(index, item) },
                modifier = Modifier
                    .size(32.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Remove",
                    tint = Color.Red
                )
            }
//Add button
            IconButton(
                onClick = { vm.addNewRow(ItemRow("", ""), item) },
                modifier = Modifier
                    .size(32.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.AddCircle,
                    contentDescription = "Add",
                    tint = Color(0xFF2ECC71)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShowRow(){
    AddItemRow(
        index = 0,
        viewModel(),
        item = ItemType.bottles
    )
}
data class ItemRow(
    val qty: String,
    val type: String
)
enum class ItemType {
    bottles,
    empties
}
```

## File: app/src/main/java/com/example/debttracker/repositories/RecordsRepository.kt
```kotlin
package com.example.debttracker.repositories

import android.util.Log
import com.example.debttracker.DatabaseRefs
import com.example.debttracker.models.Debtor
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

object RecordsRepository {
    val recordsDB = DatabaseRefs.root

    fun deactivateRecord(debt: Debtor){
        recordsDB.child(debt.id?:"").child("debt").child("active").setValue(false)

    }
    fun reactivateRecord(debt: Debtor){
        recordsDB.child(debt.id?:"").child("debt").child("active").setValue(true)

    }
    fun removeDebtFromDB(key: String){
        //deletes an entire record using the id
        recordsDB.child(key).removeValue()
    }
    fun removeSingleDebt(debtor: Debtor, debt: String){
        //gets the id of the debt record
        val debtId = debtor.id?.let { recordsDB.child(it) }
        //finds and removes a single item from a list of debt items in one record
        debtId?.child("debt")?.child("itemsOwed")?.child(debt)?.removeValue()
    }
    fun deleteDebtRecord(key: String){
        recordsDB.child(key).removeValue()
    }
    fun updateDebtRecord(debt: Debtor){
        val oldDebtList = debt.oldRecords?: emptyList()
        val newDebtList = oldDebtList+debt.debt
        recordsDB.child(debt.id!!).child("oldRecords").setValue(newDebtList)
        deleteDebtRecord(debt.id?:"errorNoId")
    }
    fun getDBDebtRecords(onListReady: (List<Debtor?>) -> Unit){
        recordsDB.orderByChild("timeStamp").addValueEventListener( object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                val debtors = snapshot.children.mapNotNull{
                    it.getValue(Debtor::class.java)
                }.sortedByDescending { it.timeStamp }
                onListReady(debtors)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("Database_Error!",  "$error")
            }

        }
        )
    }
}
```

## File: app/src/main/java/com/example/debttracker/ScreenClass.kt
```kotlin
package com.example.debttracker

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.ui.graphics.vector.ImageVector

open class Screen(
    val icon: ImageVector? = null,
    val route: String,
    val title: String
){
    data object UpdateDebtScreen: Screen(title = "Update Debt", route = "updateDebt")
    data object AddRecordScreen: Screen(title = "Add Record", route = "addRecord", icon = Icons.Filled.AddCircle )
}
```

## File: app/src/main/java/com/example/debttracker/uidesigns/RecordCard.kt
```kotlin
package com.example.debttracker.uidesigns

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.debttracker.Screen
import com.example.debttracker.models.AddDebtorViewModel
import com.example.debttracker.models.Debtor
import com.example.debttracker.viewmodels.RecordsViewmodel

@Composable
fun RecordCard(
    vm: RecordsViewmodel,
    debtor: Debtor,
    navController: NavController
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            Text(
                text = debtor.name.toString(),
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(Modifier.height(4.dp))

            Text(
                text = "Added: ${debtor.debt?.dateAdded ?: "N/A"}",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )

            Spacer(Modifier.height(10.dp))

            Button(
                onClick = {
                    // Navigate to a detailed record page
                    // navController.navigate("record_details/${debtor.id}")
                    vm.selectedRecord = debtor
                    navController.navigate(Screen.UpdateDebtScreen.route) {
                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("View")
            }
        }
    }
}
```

## File: app/src/main/java/com/example/debttracker/viewmodels/recordsViewmodel.kt
```kotlin
package com.example.debttracker.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.debttracker.models.Debtor
import com.example.debttracker.repositories.RecordsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RecordsViewmodel:  ViewModel(){
    var selectedRecord by mutableStateOf<Debtor?>(null)
    private val _debtors = MutableStateFlow<List<Debtor?>>(emptyList())
    val debtors: StateFlow<List<Debtor?>> = _debtors
    private val repo = RecordsRepository

    init {
        getDebtorList()
    }

    private fun getDebtorList(){
        repo.getDBDebtRecords {
                debtMap ->
            _debtors.value = debtMap
        }
    }


}
```

## File: .gitignore
```
*.iml
.gradle
/local.properties
/.idea/caches
/.idea/libraries
/.idea/modules.xml
/.idea/workspace.xml
/.idea/navEditor.xml
/.idea/assetWizardSettings.xml
.DS_Store
/build
/captures
.externalNativeBuild
.cxx
local.properties
```

## File: app/.gitignore
```
/build
```

## File: app/google-services (1).json
```json
{
  "project_info": {
    "project_number": "1036788853215",
    "firebase_url": "https://freemann-firms-default-rtdb.firebaseio.com",
    "project_id": "freemann-firms",
    "storage_bucket": "freemann-firms.firebasestorage.app"
  },
  "client": [
    {
      "client_info": {
        "mobilesdk_app_id": "1:1036788853215:android:a8ba0424ce8c523c7cfc6a",
        "android_client_info": {
          "package_name": "com.example.debttracker"
        }
      },
      "oauth_client": [],
      "api_key": [
        {
          "current_key": "AIzaSyBvT3qj25i69EE63fUFWwDE4XHx-07x7k8"
        }
      ],
      "services": {
        "appinvite_service": {
          "other_platform_oauth_client": []
        }
      }
    },
    {
      "client_info": {
        "mobilesdk_app_id": "1:1036788853215:android:cb4340cf04dc0b277cfc6a",
        "android_client_info": {
          "package_name": "com.guvnoh.binl"
        }
      },
      "oauth_client": [],
      "api_key": [
        {
          "current_key": "AIzaSyBvT3qj25i69EE63fUFWwDE4XHx-07x7k8"
        }
      ],
      "services": {
        "appinvite_service": {
          "other_platform_oauth_client": []
        }
      }
    },
    {
      "client_info": {
        "mobilesdk_app_id": "1:1036788853215:android:2d49ef49181dd99f7cfc6a",
        "android_client_info": {
          "package_name": "com.guvnoh.boma"
        }
      },
      "oauth_client": [],
      "api_key": [
        {
          "current_key": "AIzaSyBvT3qj25i69EE63fUFWwDE4XHx-07x7k8"
        }
      ],
      "services": {
        "appinvite_service": {
          "other_platform_oauth_client": []
        }
      }
    },
    {
      "client_info": {
        "mobilesdk_app_id": "1:1036788853215:android:a91464966917adc97cfc6a",
        "android_client_info": {
          "package_name": "com.guvnoh.freemannfirms"
        }
      },
      "oauth_client": [],
      "api_key": [
        {
          "current_key": "AIzaSyBvT3qj25i69EE63fUFWwDE4XHx-07x7k8"
        }
      ],
      "services": {
        "appinvite_service": {
          "other_platform_oauth_client": []
        }
      }
    }
  ],
  "configuration_version": "1"
}
```

## File: app/google-services.json
```json
{
  "project_info": {
    "project_number": "1036788853215",
    "firebase_url": "https://freemann-firms-default-rtdb.firebaseio.com",
    "project_id": "freemann-firms",
    "storage_bucket": "freemann-firms.firebasestorage.app"
  },
  "client": [
    {
      "client_info": {
        "mobilesdk_app_id": "1:1036788853215:android:a8ba0424ce8c523c7cfc6a",
        "android_client_info": {
          "package_name": "com.example.debttracker"
        }
      },
      "oauth_client": [],
      "api_key": [
        {
          "current_key": "AIzaSyBvT3qj25i69EE63fUFWwDE4XHx-07x7k8"
        }
      ],
      "services": {
        "appinvite_service": {
          "other_platform_oauth_client": []
        }
      }
    },
    {
      "client_info": {
        "mobilesdk_app_id": "1:1036788853215:android:cb4340cf04dc0b277cfc6a",
        "android_client_info": {
          "package_name": "com.guvnoh.binl"
        }
      },
      "oauth_client": [],
      "api_key": [
        {
          "current_key": "AIzaSyBvT3qj25i69EE63fUFWwDE4XHx-07x7k8"
        }
      ],
      "services": {
        "appinvite_service": {
          "other_platform_oauth_client": []
        }
      }
    },
    {
      "client_info": {
        "mobilesdk_app_id": "1:1036788853215:android:2d49ef49181dd99f7cfc6a",
        "android_client_info": {
          "package_name": "com.guvnoh.boma"
        }
      },
      "oauth_client": [],
      "api_key": [
        {
          "current_key": "AIzaSyBvT3qj25i69EE63fUFWwDE4XHx-07x7k8"
        }
      ],
      "services": {
        "appinvite_service": {
          "other_platform_oauth_client": []
        }
      }
    },
    {
      "client_info": {
        "mobilesdk_app_id": "1:1036788853215:android:a91464966917adc97cfc6a",
        "android_client_info": {
          "package_name": "com.guvnoh.freemannfirms"
        }
      },
      "oauth_client": [],
      "api_key": [
        {
          "current_key": "AIzaSyBvT3qj25i69EE63fUFWwDE4XHx-07x7k8"
        }
      ],
      "services": {
        "appinvite_service": {
          "other_platform_oauth_client": []
        }
      }
    }
  ],
  "configuration_version": "1"
}
```

## File: app/proguard-rules.pro
```
# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
```

## File: app/src/androidTest/java/com/example/debttracker/ExampleInstrumentedTest.kt
```kotlin
package com.example.debttracker

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.test2", appContext.packageName)
    }
}
```

## File: app/src/main/AndroidManifest.xml
```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools">
    <uses-permission android:name="android.permission.INTERNET"/>

    <application
        android:allowBackup="true"
        android:dataExtractionRules="@xml/data_extraction_rules"
        android:fullBackupContent="@xml/backup_rules"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/Theme.Test2"
        tools:targetApi="31">
        <activity
            android:name=".MainActivity"
            android:exported="true"
            android:label="@string/app_name"
            android:theme="@style/Theme.Test2">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>

</manifest>
```

## File: app/src/main/java/com/example/debttracker/formatters/HalfAndQuarter.kt
```kotlin
package com.example.debttracker.formatters

fun halfAndQuarter(num: Double): String{
    //converts 0.5 to display as '½', same for 0.25 (quarter)
    //also converts them when they have integer companions e.g '1½' etc...
    val integerPart = (num).toInt()
    return if (num % 1 == 0.5){
        if(integerPart==0){
            "½"
        } else "$integerPart½"
    }else if (num % 1 == 0.25){
        if(integerPart==0){
            "¼"
        } else "$integerPart¼"
    }
    else if (num>num.toInt()){
        num.toString()
    }else num.toInt().toString()
}
```

## File: app/src/main/java/com/example/debttracker/formatters/MoneyFormatter.kt
```kotlin
package com.example.debttracker.formatters

import android.icu.text.DecimalFormat
import java.math.BigDecimal

fun moneyFormat(num: Double?): String{
    val format = DecimalFormat("#,###")
    var formattedNumber = format.format(num)
    formattedNumber = "₦$formattedNumber"
    return formattedNumber
}
```

## File: app/src/main/java/com/example/debttracker/formatters/TextFormatter.kt
```kotlin
package com.example.debttracker.formatters

import java.util.Locale

fun capitaliseFirst(text: String): String{
    val formattedText = text.replaceFirstChar { if (it. isLowerCase()) it. titlecase(
        Locale. getDefault()) else it. toString() }
    return formattedText
}

fun textToDisplay(text: String): String{
    val displayText = when(text){
        "Cocacola" -> "Cocacola bottles"
        "Hero12" -> "Hero(x12)"
        "Hero20" -> "Hero(x20)"
        "Hero24" -> "Hero(x24)"
        "Nbl12" -> "Nbl(x12)"
        "Nbl20" -> "Nbl(x20)"
        "Nbl24" -> "Nbl(x24)"
        "Guinness12" -> "Guinness(x12)"
        "Guinness18" -> "Guinness(x18)"
        "Guinness24" -> "Guinness(x24)"
        else -> "error"
    }
    return displayText
}
```

## File: app/src/main/java/com/example/debttracker/formatters/TimeFormatter.kt
```kotlin
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
```

## File: app/src/main/java/com/example/debttracker/models/OptionsMenu.kt
```kotlin
package com.example.debttracker.models

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp


@Composable
fun Options(
    setInactive: (Debtor) -> Unit,
    setActive: (Debtor) -> Unit,
    onDelete: (Debtor) -> Unit,
    debtor: Debtor
){
    var expanded by remember { mutableStateOf(false) }
    Box(
    ){
        IconButton(
            onClick = {expanded = true}
        ) {
            Icon(
                imageVector = Icons.Default.MoreVert, "options"
            )
        }
        MaterialTheme(
            shapes = MaterialTheme.shapes.copy(
                extraSmall = RoundedCornerShape(25.dp)  // ← DROPDOWN MENU SHAPE
            ),
            content = {
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },

                    ) {
                    DropdownMenuItem(
                        text = {
                            Text(text = "Deactivate")
                        },
                        onClick = {
                            expanded = false
                            setInactive(debtor)
                        }
                    )
                    DropdownMenuItem(
                        text = {
                            Text(text = "Activate")
                        },
                        onClick = {
                            expanded = false
                            setActive(debtor)
                        }
                    )
                    DropdownMenuItem(
                        text = {
                            Text(text = "Delete")
                        },
                        onClick = {
                            expanded = false
                            onDelete(debtor)
                        }
                    )
                    DropdownMenuItem(
                        text = {
                            Text(text = "Edit")
                        },
                        onClick = {
                            expanded = false
                            setInactive(debtor)
                        }
                    )
                }
            }
        )

    }

}
```

## File: app/src/main/java/com/example/debttracker/ui/theme/Color.kt
```kotlin
package com.example.debttracker.ui.theme

import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)
```

## File: app/src/main/java/com/example/debttracker/ui/theme/Theme.kt
```kotlin
package com.example.debttracker.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xff2ab7ca),
    secondary = Color(0xff7da2c8),
    onPrimary = Color.Black,
    onSecondary = Color.White,
    background = Color(0xff121212),
    onBackground = Color(0xffeaeaea),
    surface = Color(0xFF1e1e1e),
    onSurface = Color(0xffeaeaea),
    error = Color(0xffcf6679)
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xff2ab7ca),//Purple40,
    secondary = Color(0xffa1c6ea),//PurpleGrey40,
   // tertiary = Pink40,

    // Other default colors to override
    background = Color(0xfff8f9fa),//Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onTertiary = Color.White,
    onBackground = Color(0xff2d2d2d),//Color(0xFF1C1B1F),
    onSurface = Color(0xff2d2d2d),//Color(0xFF1C1B1F),
    error = Color(0xffb00020)

)

@Composable
fun DebtTrackerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
```

## File: app/src/main/java/com/example/debttracker/ui/theme/Type.kt
```kotlin
package com.example.debttracker.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)
```

## File: app/src/main/res/drawable/ic_launcher_foreground.xml
```xml
<vector xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:aapt="http://schemas.android.com/aapt"
    android:width="108dp"
    android:height="108dp"
    android:viewportWidth="108"
    android:viewportHeight="108">
    <path android:pathData="M31,63.928c0,0 6.4,-11 12.1,-13.1c7.2,-2.6 26,-1.4 26,-1.4l38.1,38.1L107,108.928l-32,-1L31,63.928z">
        <aapt:attr name="android:fillColor">
            <gradient
                android:endX="85.84757"
                android:endY="92.4963"
                android:startX="42.9492"
                android:startY="49.59793"
                android:type="linear">
                <item
                    android:color="#44000000"
                    android:offset="0.0" />
                <item
                    android:color="#00000000"
                    android:offset="1.0" />
            </gradient>
        </aapt:attr>
    </path>
    <path
        android:fillColor="#FFFFFF"
        android:fillType="nonZero"
        android:pathData="M65.3,45.828l3.8,-6.6c0.2,-0.4 0.1,-0.9 -0.3,-1.1c-0.4,-0.2 -0.9,-0.1 -1.1,0.3l-3.9,6.7c-6.3,-2.8 -13.4,-2.8 -19.7,0l-3.9,-6.7c-0.2,-0.4 -0.7,-0.5 -1.1,-0.3C38.8,38.328 38.7,38.828 38.9,39.228l3.8,6.6C36.2,49.428 31.7,56.028 31,63.928h46C76.3,56.028 71.8,49.428 65.3,45.828zM43.4,57.328c-0.8,0 -1.5,-0.5 -1.8,-1.2c-0.3,-0.7 -0.1,-1.5 0.4,-2.1c0.5,-0.5 1.4,-0.7 2.1,-0.4c0.7,0.3 1.2,1 1.2,1.8C45.3,56.528 44.5,57.328 43.4,57.328L43.4,57.328zM64.6,57.328c-0.8,0 -1.5,-0.5 -1.8,-1.2s-0.1,-1.5 0.4,-2.1c0.5,-0.5 1.4,-0.7 2.1,-0.4c0.7,0.3 1.2,1 1.2,1.8C66.5,56.528 65.6,57.328 64.6,57.328L64.6,57.328z"
        android:strokeWidth="1"
        android:strokeColor="#00000000" />
</vector>
```

## File: app/src/main/res/values/colors.xml
```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <color name="purple_200">#FFBB86FC</color>
    <color name="purple_500">#FF6200EE</color>
    <color name="purple_700">#FF3700B3</color>
    <color name="teal_200">#FF03DAC5</color>
    <color name="teal_700">#FF018786</color>
    <color name="black">#FF000000</color>
    <color name="white">#FFFFFFFF</color>
</resources>
```

## File: app/src/main/res/values/themes.xml
```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>

    <style name="Theme.Test2" parent="android:Theme.Material.Light.NoActionBar" />
</resources>
```

## File: app/src/main/res/xml/backup_rules.xml
```xml
<?xml version="1.0" encoding="utf-8"?><!--
   Sample backup rules file; uncomment and customize as necessary.
   See https://developer.android.com/guide/topics/data/autobackup
   for details.
   Note: This file is ignored for devices older that API 31
   See https://developer.android.com/about/versions/12/backup-restore
-->
<full-backup-content>
    <!--
   <include domain="sharedpref" path="."/>
   <exclude domain="sharedpref" path="device.xml"/>
-->
</full-backup-content>
```

## File: app/src/main/res/xml/data_extraction_rules.xml
```xml
<?xml version="1.0" encoding="utf-8"?><!--
   Sample data extraction rules file; uncomment and customize as necessary.
   See https://developer.android.com/about/versions/12/backup-restore#xml-changes
   for details.
-->
<data-extraction-rules>
    <cloud-backup>
        <!-- TODO: Use <include> and <exclude> to control what is backed up.
        <include .../>
        <exclude .../>
        -->
    </cloud-backup>
    <!--
    <device-transfer>
        <include .../>
        <exclude .../>
    </device-transfer>
    -->
</data-extraction-rules>
```

## File: app/src/test/java/com/example/debttracker/ExampleUnitTest.kt
```kotlin
package com.example.debttracker

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
}
```

## File: gradlew
```
#!/usr/bin/env sh

#
# Copyright 2015 the original author or authors.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      https://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#

##############################################################################
##
##  Gradle start up script for UN*X
##
##############################################################################

# Attempt to set APP_HOME
# Resolve links: $0 may be a link
PRG="$0"
# Need this for relative symlinks.
while [ -h "$PRG" ] ; do
    ls=`ls -ld "$PRG"`
    link=`expr "$ls" : '.*-> \(.*\)$'`
    if expr "$link" : '/.*' > /dev/null; then
        PRG="$link"
    else
        PRG=`dirname "$PRG"`"/$link"
    fi
done
SAVED="`pwd`"
cd "`dirname \"$PRG\"`/" >/dev/null
APP_HOME="`pwd -P`"
cd "$SAVED" >/dev/null

APP_NAME="Gradle"
APP_BASE_NAME=`basename "$0"`

# Add default JVM options here. You can also use JAVA_OPTS and GRADLE_OPTS to pass JVM options to this script.
DEFAULT_JVM_OPTS='"-Xmx64m" "-Xms64m"'

# Use the maximum available, or set MAX_FD != -1 to use that value.
MAX_FD="maximum"

warn () {
    echo "$*"
}

die () {
    echo
    echo "$*"
    echo
    exit 1
}

# OS specific support (must be 'true' or 'false').
cygwin=false
msys=false
darwin=false
nonstop=false
case "`uname`" in
  CYGWIN* )
    cygwin=true
    ;;
  Darwin* )
    darwin=true
    ;;
  MINGW* )
    msys=true
    ;;
  NONSTOP* )
    nonstop=true
    ;;
esac

CLASSPATH=$APP_HOME/gradle/wrapper/gradle-wrapper.jar


# Determine the Java command to use to start the JVM.
if [ -n "$JAVA_HOME" ] ; then
    if [ -x "$JAVA_HOME/jre/sh/java" ] ; then
        # IBM's JDK on AIX uses strange locations for the executables
        JAVACMD="$JAVA_HOME/jre/sh/java"
    else
        JAVACMD="$JAVA_HOME/bin/java"
    fi
    if [ ! -x "$JAVACMD" ] ; then
        die "ERROR: JAVA_HOME is set to an invalid directory: $JAVA_HOME

Please set the JAVA_HOME variable in your environment to match the
location of your Java installation."
    fi
else
    JAVACMD="java"
    which java >/dev/null 2>&1 || die "ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.

Please set the JAVA_HOME variable in your environment to match the
location of your Java installation."
fi

# Increase the maximum file descriptors if we can.
if [ "$cygwin" = "false" -a "$darwin" = "false" -a "$nonstop" = "false" ] ; then
    MAX_FD_LIMIT=`ulimit -H -n`
    if [ $? -eq 0 ] ; then
        if [ "$MAX_FD" = "maximum" -o "$MAX_FD" = "max" ] ; then
            MAX_FD="$MAX_FD_LIMIT"
        fi
        ulimit -n $MAX_FD
        if [ $? -ne 0 ] ; then
            warn "Could not set maximum file descriptor limit: $MAX_FD"
        fi
    else
        warn "Could not query maximum file descriptor limit: $MAX_FD_LIMIT"
    fi
fi

# For Darwin, add options to specify how the application appears in the dock
if $darwin; then
    GRADLE_OPTS="$GRADLE_OPTS \"-Xdock:name=$APP_NAME\" \"-Xdock:icon=$APP_HOME/media/gradle.icns\""
fi

# For Cygwin or MSYS, switch paths to Windows format before running java
if [ "$cygwin" = "true" -o "$msys" = "true" ] ; then
    APP_HOME=`cygpath --path --mixed "$APP_HOME"`
    CLASSPATH=`cygpath --path --mixed "$CLASSPATH"`

    JAVACMD=`cygpath --unix "$JAVACMD"`

    # We build the pattern for arguments to be converted via cygpath
    ROOTDIRSRAW=`find -L / -maxdepth 1 -mindepth 1 -type d 2>/dev/null`
    SEP=""
    for dir in $ROOTDIRSRAW ; do
        ROOTDIRS="$ROOTDIRS$SEP$dir"
        SEP="|"
    done
    OURCYGPATTERN="(^($ROOTDIRS))"
    # Add a user-defined pattern to the cygpath arguments
    if [ "$GRADLE_CYGPATTERN" != "" ] ; then
        OURCYGPATTERN="$OURCYGPATTERN|($GRADLE_CYGPATTERN)"
    fi
    # Now convert the arguments - kludge to limit ourselves to /bin/sh
    i=0
    for arg in "$@" ; do
        CHECK=`echo "$arg"|egrep -c "$OURCYGPATTERN" -`
        CHECK2=`echo "$arg"|egrep -c "^-"`                                 ### Determine if an option

        if [ $CHECK -ne 0 ] && [ $CHECK2 -eq 0 ] ; then                    ### Added a condition
            eval `echo args$i`=`cygpath --path --ignore --mixed "$arg"`
        else
            eval `echo args$i`="\"$arg\""
        fi
        i=`expr $i + 1`
    done
    case $i in
        0) set -- ;;
        1) set -- "$args0" ;;
        2) set -- "$args0" "$args1" ;;
        3) set -- "$args0" "$args1" "$args2" ;;
        4) set -- "$args0" "$args1" "$args2" "$args3" ;;
        5) set -- "$args0" "$args1" "$args2" "$args3" "$args4" ;;
        6) set -- "$args0" "$args1" "$args2" "$args3" "$args4" "$args5" ;;
        7) set -- "$args0" "$args1" "$args2" "$args3" "$args4" "$args5" "$args6" ;;
        8) set -- "$args0" "$args1" "$args2" "$args3" "$args4" "$args5" "$args6" "$args7" ;;
        9) set -- "$args0" "$args1" "$args2" "$args3" "$args4" "$args5" "$args6" "$args7" "$args8" ;;
    esac
fi

# Escape application args
save () {
    for i do printf %s\\n "$i" | sed "s/'/'\\\\''/g;1s/^/'/;\$s/\$/' \\\\/" ; done
    echo " "
}
APP_ARGS=`save "$@"`

# Collect all arguments for the java command, following the shell quoting and substitution rules
eval set -- $DEFAULT_JVM_OPTS $JAVA_OPTS $GRADLE_OPTS "\"-Dorg.gradle.appname=$APP_BASE_NAME\"" -classpath "\"$CLASSPATH\"" org.gradle.wrapper.GradleWrapperMain "$APP_ARGS"

exec "$JAVACMD" "$@"
```

## File: gradlew.bat
```batch
@rem
@rem Copyright 2015 the original author or authors.
@rem
@rem Licensed under the Apache License, Version 2.0 (the "License");
@rem you may not use this file except in compliance with the License.
@rem You may obtain a copy of the License at
@rem
@rem      https://www.apache.org/licenses/LICENSE-2.0
@rem
@rem Unless required by applicable law or agreed to in writing, software
@rem distributed under the License is distributed on an "AS IS" BASIS,
@rem WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@rem See the License for the specific language governing permissions and
@rem limitations under the License.
@rem

@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  Gradle startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%

@rem Resolve any "." and ".." in APP_HOME to make it shorter.
for %%i in ("%APP_HOME%") do set APP_HOME=%%~fi

@rem Add default JVM options here. You can also use JAVA_OPTS and GRADLE_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS="-Xmx64m" "-Xms64m"

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if "%ERRORLEVEL%" == "0" goto execute

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto execute

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\gradle\wrapper\gradle-wrapper.jar


@rem Execute Gradle
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %GRADLE_OPTS% "-Dorg.gradle.appname=%APP_BASE_NAME%" -classpath "%CLASSPATH%" org.gradle.wrapper.GradleWrapperMain %*

:end
@rem End local scope for the variables with windows NT shell
if "%ERRORLEVEL%"=="0" goto mainEnd

:fail
rem Set variable GRADLE_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
if  not "" == "%GRADLE_EXIT_CONSOLE%" exit 1
exit /b 1

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
```

## File: app/build.gradle.kts
```kotlin
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "com.example.debttracker"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.debttracker"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.activity.ktx)
    implementation(libs.firebase.database)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
```

## File: app/src/main/java/com/example/debttracker/models/BottleType.kt
```kotlin
package com.example.debttracker.models

enum class BottleType {
        Cocacola,
        Hero,
        Flying_Fish,
        Beta_malt,
        Nbl12,
        Nbl20,
        Amstel,
        Maltina,
        Orijin,
        Medium_stout,
        Small_Stout,
}
```

## File: app/src/main/java/com/example/debttracker/models/BottomNav.kt
```kotlin
package com.example.debttracker.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Warning
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.debttracker.Screen

open class BottomNavItem (val route: String, val icon: ImageVector, val title: String) {
    data object Records : BottomNavItem("records", Icons.AutoMirrored.Filled.List, "Records")
    data object AddRecord : BottomNavItem(
        Screen.AddRecordScreen.route,
        Screen.AddRecordScreen.icon?:Icons.Filled.Warning,
        Screen.AddRecordScreen.title)
    //data object UpdateRecord : BottomNavItem("updateRecord", Icons.Filled.Edit, "Update Record")
    //data object Update_Record : BottomNavItem("updateRecord", Icons.Filled.Edit, "Update Record")
}
```

## File: app/src/main/java/com/example/debttracker/models/CustomAlertDialog.kt
```kotlin
package com.example.debttracker.models

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.debttracker.Screen
import com.example.debttracker.repositories.RecordsRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomAlertDialog(
    debtor: Debtor,
    debt: String,
    navController: NavController,
    caseKey: DialogKey,
    dismissDialog: () -> Unit,


    ){
    //var deleteRecordDialog by remember {  mutableStateOf(false) }
    BasicAlertDialog(
        onDismissRequest = {}
    ) {
        Surface(
            shape = MaterialTheme.shapes.large,
            tonalElevation = AlertDialogDefaults.TonalElevation,
        ){
            Column (
                modifier = Modifier.padding(24.dp)
            ){
                Text(
                    text = "Delete debt record?",
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = "Are you sure you want to delete this debt record?",
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(24.dp))

                Row (
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextButton(onClick = {dismissDialog()}) {
                        Text(text = "Cancel")

                    }
                    TextButton(onClick = {
                        when (caseKey ){
                            DialogKey.debtCard -> {
                                RecordsRepository.removeSingleDebt(debtor = debtor, debt = debt)
                                navController.popBackStack()
//                                navController.navigate(Screen.UpdateDebtScreen.route)
                                navController.navigate(Screen.UpdateDebtScreen.route) {
                                    popUpTo(navController.graph.findStartDestination().id){
                                        inclusive = true
                                    }
                                    launchSingleTop = true
                                }
                            }
                            DialogKey.topAppBar -> {
                                RecordsRepository.removeDebtFromDB(key=debtor.id.toString())
                                navController.popBackStack()
                            }
                        }
                        dismissDialog()

                    }) {
                        Text(text = "Continue")
                    }
                }
            }
        }
    }
}

enum class DialogKey{
    topAppBar,
    debtCard
}
```

## File: app/src/main/java/com/example/debttracker/models/Debtor.kt
```kotlin
package com.example.debttracker.models

data class Debtor(
    var id: String? = null,
    var timeStamp: Long? = null,
    var name: String? = null,
    var debt: Debt? = null,
    var oldRecords: List<Debt>? = null
)
```

## File: app/src/main/res/drawable/ic_launcher_background.xml
```xml
<?xml version="1.0" encoding="utf-8"?>
<vector xmlns:android="http://schemas.android.com/apk/res/android"
    android:width="108dp"
    android:height="108dp"
    android:viewportWidth="108"
    android:viewportHeight="108">
  <group android:scaleX="0.6"
      android:scaleY="0.6"
      android:translateX="21.6"
      android:translateY="21.6">
      <path android:fillColor="#3DDC84"
            android:pathData="M0,0h108v108h-108z"/>
      <path android:fillColor="#00000000" android:pathData="M9,0L9,108"
            android:strokeColor="#33FFFFFF" android:strokeWidth="0.8"/>
      <path android:fillColor="#00000000" android:pathData="M19,0L19,108"
            android:strokeColor="#33FFFFFF" android:strokeWidth="0.8"/>
      <path android:fillColor="#00000000" android:pathData="M29,0L29,108"
            android:strokeColor="#33FFFFFF" android:strokeWidth="0.8"/>
      <path android:fillColor="#00000000" android:pathData="M39,0L39,108"
            android:strokeColor="#33FFFFFF" android:strokeWidth="0.8"/>
      <path android:fillColor="#00000000" android:pathData="M49,0L49,108"
            android:strokeColor="#33FFFFFF" android:strokeWidth="0.8"/>
      <path android:fillColor="#00000000" android:pathData="M59,0L59,108"
            android:strokeColor="#33FFFFFF" android:strokeWidth="0.8"/>
      <path android:fillColor="#00000000" android:pathData="M69,0L69,108"
            android:strokeColor="#33FFFFFF" android:strokeWidth="0.8"/>
      <path android:fillColor="#00000000" android:pathData="M79,0L79,108"
            android:strokeColor="#33FFFFFF" android:strokeWidth="0.8"/>
      <path android:fillColor="#00000000" android:pathData="M89,0L89,108"
            android:strokeColor="#33FFFFFF" android:strokeWidth="0.8"/>
      <path android:fillColor="#00000000" android:pathData="M99,0L99,108"
            android:strokeColor="#33FFFFFF" android:strokeWidth="0.8"/>
      <path android:fillColor="#00000000" android:pathData="M0,9L108,9"
            android:strokeColor="#33FFFFFF" android:strokeWidth="0.8"/>
      <path android:fillColor="#00000000" android:pathData="M0,19L108,19"
            android:strokeColor="#33FFFFFF" android:strokeWidth="0.8"/>
      <path android:fillColor="#00000000" android:pathData="M0,29L108,29"
            android:strokeColor="#33FFFFFF" android:strokeWidth="0.8"/>
      <path android:fillColor="#00000000" android:pathData="M0,39L108,39"
            android:strokeColor="#33FFFFFF" android:strokeWidth="0.8"/>
      <path android:fillColor="#00000000" android:pathData="M0,49L108,49"
            android:strokeColor="#33FFFFFF" android:strokeWidth="0.8"/>
      <path android:fillColor="#00000000" android:pathData="M0,59L108,59"
            android:strokeColor="#33FFFFFF" android:strokeWidth="0.8"/>
      <path android:fillColor="#00000000" android:pathData="M0,69L108,69"
            android:strokeColor="#33FFFFFF" android:strokeWidth="0.8"/>
      <path android:fillColor="#00000000" android:pathData="M0,79L108,79"
            android:strokeColor="#33FFFFFF" android:strokeWidth="0.8"/>
      <path android:fillColor="#00000000" android:pathData="M0,89L108,89"
            android:strokeColor="#33FFFFFF" android:strokeWidth="0.8"/>
      <path android:fillColor="#00000000" android:pathData="M0,99L108,99"
            android:strokeColor="#33FFFFFF" android:strokeWidth="0.8"/>
      <path android:fillColor="#00000000" android:pathData="M19,29L89,29"
            android:strokeColor="#33FFFFFF" android:strokeWidth="0.8"/>
      <path android:fillColor="#00000000" android:pathData="M19,39L89,39"
            android:strokeColor="#33FFFFFF" android:strokeWidth="0.8"/>
      <path android:fillColor="#00000000" android:pathData="M19,49L89,49"
            android:strokeColor="#33FFFFFF" android:strokeWidth="0.8"/>
      <path android:fillColor="#00000000" android:pathData="M19,59L89,59"
            android:strokeColor="#33FFFFFF" android:strokeWidth="0.8"/>
      <path android:fillColor="#00000000" android:pathData="M19,69L89,69"
            android:strokeColor="#33FFFFFF" android:strokeWidth="0.8"/>
      <path android:fillColor="#00000000" android:pathData="M19,79L89,79"
            android:strokeColor="#33FFFFFF" android:strokeWidth="0.8"/>
      <path android:fillColor="#00000000" android:pathData="M29,19L29,89"
            android:strokeColor="#33FFFFFF" android:strokeWidth="0.8"/>
      <path android:fillColor="#00000000" android:pathData="M39,19L39,89"
            android:strokeColor="#33FFFFFF" android:strokeWidth="0.8"/>
      <path android:fillColor="#00000000" android:pathData="M49,19L49,89"
            android:strokeColor="#33FFFFFF" android:strokeWidth="0.8"/>
      <path android:fillColor="#00000000" android:pathData="M59,19L59,89"
            android:strokeColor="#33FFFFFF" android:strokeWidth="0.8"/>
      <path android:fillColor="#00000000" android:pathData="M69,19L69,89"
            android:strokeColor="#33FFFFFF" android:strokeWidth="0.8"/>
      <path android:fillColor="#00000000" android:pathData="M79,19L79,89"
            android:strokeColor="#33FFFFFF" android:strokeWidth="0.8"/>
  </group>
</vector>
```

## File: app/src/main/res/mipmap-anydpi-v26/ic_launcher_round.xml
```xml
<?xml version="1.0" encoding="utf-8"?>
<adaptive-icon xmlns:android="http://schemas.android.com/apk/res/android">
    <background android:drawable="@drawable/ic_launcher_background"/>
    <foreground android:drawable="@mipmap/ic_launcher_foreground"/>
</adaptive-icon>
```

## File: app/src/main/res/mipmap-anydpi-v26/ic_launcher.xml
```xml
<?xml version="1.0" encoding="utf-8"?>
<adaptive-icon xmlns:android="http://schemas.android.com/apk/res/android">
    <background android:drawable="@drawable/ic_launcher_background"/>
    <foreground android:drawable="@mipmap/ic_launcher_foreground"/>
</adaptive-icon>
```

## File: app/src/main/res/values/strings.xml
```xml
<resources>
    <string name="app_name">Debt Tracker</string>
</resources>
```

## File: build.gradle.kts
```kotlin
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.google.gms.google.services) apply false
}
```

## File: gradle.properties
```
# Project-wide Gradle settings.
# IDE (e.g. Android Studio) users:
# Gradle settings configured through the IDE *will override*
# any settings specified in this file.
# For more details on how to configure your build environment visit
# http://www.gradle.org/docs/current/userguide/build_environment.html
# Specifies the JVM arguments used for the daemon process.
# The setting is particularly useful for tweaking memory settings.
org.gradle.jvmargs=-Xmx2048m -Dfile.encoding=UTF-8
# When configured, Gradle will run in incubating parallel mode.
# This option should only be used with decoupled projects. For more details, visit
# https://developer.android.com/r/tools/gradle-multi-project-decoupled-projects
# org.gradle.parallel=true
# AndroidX package structure to make it clearer which packages are bundled with the
# Android operating system, and which are packaged with your app's APK
# https://developer.android.com/topic/libraries/support-library/androidx-rn
android.useAndroidX=true
# Kotlin code style for this project: "official" or "obsolete":
kotlin.code.style=official
# Enables namespacing of each library's R class so that its R class includes only the
# resources declared in the library itself and none from the library's dependencies,
# thereby reducing the size of the R class for that library
android.nonTransitiveRClass=true
org.gradle.caching=false
org.gradle.daemon=false
org.gradle.user.home=.gradle
```

## File: gradle/libs.versions.toml
```toml
#[versions]
#agp = "8.9.0"
#kotlin = "2.0.21"
#coreKtx = "1.15.0"
#junit = "4.13.2"
#junitVersion = "1.2.1"
#espressoCore = "3.6.1"
#lifecycleRuntimeKtx = "2.8.7"
#activityCompose = "1.9.3"
#composeBom = "2024.10.00"
#navigationCompose = "2.8.3"
#activityKtx = "1.9.3"

[versions]
agp = "8.8.0"
kotlin = "2.0.0"
coreKtx = "1.13.1"
junit = "4.13.2"
junitVersion = "1.1.5"
espressoCore = "3.5.1"
lifecycleRuntimeKtx = "2.8.6"
activityCompose = "1.9.2"
composeBom = "2024.04.01"
navigationCompose = "2.8.3"
activityKtx = "1.9.2"
googleGmsGoogleServices = "4.4.4"
firebaseDatabase = "22.0.1"


[libraries]
androidx-core-ktx = { group = "androidx.core", name = "core-ktx", version.ref = "coreKtx" }
androidx-navigation-compose = { module = "androidx.navigation:navigation-compose", version.ref = "navigationCompose" }
junit = { group = "junit", name = "junit", version.ref = "junit" }
androidx-junit = { group = "androidx.test.ext", name = "junit", version.ref = "junitVersion" }
androidx-espresso-core = { group = "androidx.test.espresso", name = "espresso-core", version.ref = "espressoCore" }
androidx-lifecycle-runtime-ktx = { group = "androidx.lifecycle", name = "lifecycle-runtime-ktx", version.ref = "lifecycleRuntimeKtx" }
androidx-activity-compose = { group = "androidx.activity", name = "activity-compose", version.ref = "activityCompose" }
androidx-compose-bom = { group = "androidx.compose", name = "compose-bom", version.ref = "composeBom" }
androidx-ui = { group = "androidx.compose.ui", name = "ui" }
androidx-ui-graphics = { group = "androidx.compose.ui", name = "ui-graphics" }
androidx-ui-tooling = { group = "androidx.compose.ui", name = "ui-tooling" }
androidx-ui-tooling-preview = { group = "androidx.compose.ui", name = "ui-tooling-preview" }
androidx-ui-test-manifest = { group = "androidx.compose.ui", name = "ui-test-manifest" }
androidx-ui-test-junit4 = { group = "androidx.compose.ui", name = "ui-test-junit4" }
androidx-material3 = { group = "androidx.compose.material3", name = "material3" }
androidx-activity-ktx = { group = "androidx.activity", name = "activity-ktx", version.ref = "activityKtx" }
firebase-database = { group = "com.google.firebase", name = "firebase-database", version.ref = "firebaseDatabase" }

[plugins]
android-application = { id = "com.android.application", version.ref = "agp" }
kotlin-android = { id = "org.jetbrains.kotlin.android", version.ref = "kotlin" }
kotlin-compose = { id = "org.jetbrains.kotlin.plugin.compose", version.ref = "kotlin" }
google-gms-google-services = { id = "com.google.gms.google-services", version.ref = "googleGmsGoogleServices" }
```

## File: gradle/wrapper/gradle-wrapper.properties
```
#Tue Jun 17 15:38:55 WAT 2025
distributionBase=GRADLE_USER_HOME
distributionPath=wrapper/dists
distributionUrl=file:/C:/Users/machine/Desktop/DebtTrackerCompose/.gradle/wrapper/dists/gradle-8.10.2-bin.zip
zipStoreBase=GRADLE_USER_HOME
zipStorePath=wrapper/dists
```

## File: settings.gradle.kts
```kotlin
pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Debt Tracker"
include(":app")
```

## File: app/src/main/java/com/example/debttracker/MainActivity.kt
```kotlin
package com.example.debttracker

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.debttracker.models.AddDebtorViewModel
import com.example.debttracker.navigation.BottomNav
import com.example.debttracker.ui.theme.DebtTrackerTheme
import com.example.debttracker.viewmodels.RecordsViewmodel

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val addDebtorViewModel: AddDebtorViewModel = viewModel()
            val recordsViewmodel: RecordsViewmodel = viewModel()
            DebtTrackerTheme {
                BottomNav(
                    addDebtorViewModel = addDebtorViewModel,
                    recordsViewModel = recordsViewmodel
                    )
            }
        }
    }
}
```

## File: app/src/main/java/com/example/debttracker/models/Debt.kt
```kotlin
package com.example.debttracker.models

data class Debt(
    var timeAdded: String? = null,
    var dateAdded: String? = null,
    //var lastEdited: String? = null,
    var active: Boolean = true,
    var itemsOwed: MutableMap<String, Double>? = mutableMapOf()
)
```

## File: app/src/main/java/com/example/debttracker/models/DebtType.kt
```kotlin
package com.example.debttracker.models

enum class DebtType {
    Cash,
    Bottle,
    Empty,
    Plastic,
    Fulls,
    FullBottle
}
```

## File: app/src/main/java/com/example/debttracker/navigation/Navigation.kt
```kotlin
package com.example.debttracker.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.debttracker.Screen
import com.example.debttracker.models.BottomNavItem
import com.example.debttracker.models.AddDebtorViewModel
import com.example.debttracker.uidesigns.AddDebt
import com.example.debttracker.uidesigns.ClearDebt
import com.example.debttracker.uidesigns.Records
import com.example.debttracker.viewmodels.RecordsViewmodel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BottomNav(recordsViewModel: RecordsViewmodel, addDebtorViewModel: AddDebtorViewModel ) {
    val navController = rememberNavController()
    val items = listOf(
        BottomNavItem.AddRecord,
        BottomNavItem.Records,
    )


    Scaffold(
        bottomBar = {
            NavigationBar(
                tonalElevation = 4.dp,
                containerColor = MaterialTheme.colorScheme.surface
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                items.forEach { item ->
                    val selected = currentRoute == item.route
                    NavigationBarItem(
                        selected = selected,
                        onClick = {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.title,
                                tint = if (selected) {
                                    MaterialTheme.colorScheme.primary
                                } else {
                                    MaterialTheme.colorScheme.onSurfaceVariant
                                }
                            )
                        },
                        label = {
                            Text(
                                text = item.title,
                                style = MaterialTheme.typography.labelSmall,
                                color = if (selected) {
                                    MaterialTheme.colorScheme.primary
                                } else {
                                    MaterialTheme.colorScheme.onSurfaceVariant
                                }
                            )
                        },
                        alwaysShowLabel = true
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            startDestination = BottomNavItem.Records.route,
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(BottomNavItem.AddRecord.route) {
                AddDebt(navController, addDebtorViewModel)
            }
            composable(BottomNavItem.Records.route) {
                Records(vm = recordsViewModel, navController = navController)
            }
//            composable(BottomNavItem.Update_Record.route) {
//                Records(vm = viewModel, navController = navController)
//            }
            composable(Screen.UpdateDebtScreen.route) {
                ClearDebt(navController = navController, debtor = recordsViewModel.selectedRecord)
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun ShowBottomBar(){
    val fvm = AddDebtorViewModel()
    val rvm = RecordsViewmodel()
    BottomNav(addDebtorViewModel = fvm, recordsViewModel =  rvm )
}
```

## File: app/src/main/java/com/example/debttracker/uidesigns/Records.kt
```kotlin
package com.example.debttracker.uidesigns

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.rememberNavController
import com.example.debttracker.Screen
import com.example.debttracker.models.BottomNavItem
import com.example.debttracker.models.Debtor
import com.example.debttracker.models.AddDebtorViewModel
import com.example.debttracker.viewmodels.RecordsViewmodel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Records(vm: RecordsViewmodel, navController: NavController) {

    val debtRecords by vm.debtors.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F7FA))   // Soft modern background
            .padding(horizontal = 16.dp)
    ) {

        // -------------------------------------------------------------
        // Header
        // -------------------------------------------------------------
        Text(
            "Your Records",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            ),
            modifier = Modifier.padding(vertical = 16.dp)
        )

        // -------------------------------------------------------------
        // Empty State Layout
        // -------------------------------------------------------------
        if (debtRecords.isEmpty()) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // Optional: Place any illustration you want here
                // (or remove Image block entirely if not needed)
                Image(
                    painter = painterResource(id = android.R.drawable.ic_menu_info_details),
                    contentDescription = "Empty",
                    modifier = Modifier.size(120.dp),
                    contentScale = ContentScale.Fit
                )

                Spacer(Modifier.height(24.dp))

                Text(
                    "No Records Yet",
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(Modifier.height(6.dp))

                Text(
                    "Start by adding your first debtor record.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )

                Spacer(Modifier.height(30.dp))

                Button(
                    onClick = {
                        navController.navigate(BottomNavItem.AddRecord.route) {
                            launchSingleTop = true
                        }
                    },
                    modifier = Modifier.padding(12.dp)
                ) {
                    Text("Add New Record")
                }
            }
        }

        // -------------------------------------------------------------
        // Records List
        // -------------------------------------------------------------
        else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {

                items(debtRecords) { debtor ->
                    RecordCard(
                        vm = vm,
                        debtor = debtor?: Debtor(),
                        navController = navController
                    )
                }

                item { Spacer(Modifier.height(10.dp)) }
            }
        }
    }
}



@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun PreviewRecords() {
    Records(viewModel(), rememberNavController())
}
```

## File: app/src/main/java/com/example/debttracker/uidesigns/UpdateDebtStatus.kt
```kotlin
package com.example.debttracker.uidesigns

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.debttracker.formatters.TimeAndDate
import com.example.debttracker.formatters.halfAndQuarter
import com.example.debttracker.formatters.moneyFormat
import com.example.debttracker.models.BottleType
import com.example.debttracker.models.CustomAlertDialog
import com.example.debttracker.models.Debt
import com.example.debttracker.models.DebtType
import com.example.debttracker.models.Debtor
import com.example.debttracker.models.Options
import com.example.debttracker.models.DialogKey
import com.example.debttracker.repositories.RecordsRepository

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun ClearDebtDemo(){
    val new = Debt(
            timeAdded = TimeAndDate().GetTimeString(),
            dateAdded = TimeAndDate().GetDateString(),
            itemsOwed = mutableMapOf(
                DebtType.Cash.name to 6300.0
            )
        )
    val debtor = Debtor(name = "Mama Ejima", debt = new)
    ClearDebt(debtor = debtor, navController = rememberNavController())
}
@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ClearDebt(
    debtor: Debtor?,
    navController: NavController,
) {
    var deleteRecordItemDialog by remember { mutableStateOf(false) }
    var deleteEntireRecordDialog by remember { mutableStateOf(false) }
    var debtToDelete by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.height(120.dp),
                title = {
                    Row (
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        Column(
                        ) {
                            Text(
                                "Debt Details",
                                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                            )
                            debtor?.let {
                                Text(
                                    text = it.name ?: "Unknown",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                                Text(
                                    text = "Recorded: ${it.debt?.dateAdded}, ${it.debt?.timeAdded}",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = Color.Gray,
                                )
                            }
                        }
                        Options(
                            setInactive = {RecordsRepository.deactivateRecord(debtor?:Debtor()) },
                            setActive = { RecordsRepository.reactivateRecord(debtor?:Debtor()) },
                            onDelete = {
                                deleteEntireRecordDialog = true
                            },
                            debtor = debtor?:Debtor()
                        )
                        if (deleteEntireRecordDialog) {
                            debtor?.let {
                                CustomAlertDialog(
                                    debtor=it,
                                    debt=debtToDelete,
                                    navController=navController,
                                    caseKey = DialogKey.topAppBar
                                ) {
                                    deleteEntireRecordDialog = false
                                }
                            }
                        }

                    }
                }
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            // --------------------------------------------------------
            // No debt record
            // --------------------------------------------------------
            if (debtor == null || debtor.debt?.itemsOwed.isNullOrEmpty()) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "No debt record found.",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.error
                    )
                }
                return@Column
            }

            // --------------------------------------------------------
            // Debt items
            // --------------------------------------------------------
            fun getBottleName(name: String): String{
                var bottleName = name.replace("_", " ")
                val type = bottleName.takeLast(2).toIntOrNull()?:""
                if (type!=""){
                    bottleName = "${bottleName.dropLast(2)}(x$type)"
                }
                return bottleName
            }
            val bottleTypeList = BottleType.entries.toList().map { it.name }
            debtor.debt?.itemsOwed?.forEach { (type, amount) ->
                val bottleName = getBottleName(type)

                val label = when (type) {
                    DebtType.Cash.name -> "Cash Owed"
                    DebtType.Bottle.name -> "Bottles Owed"
                    DebtType.Empty.name -> "Empties Balance"
                    DebtType.Fulls.name -> "Fulls Owed"
                    DebtType.FullBottle.name -> "Full Bottles Owed"
                    DebtType.Plastic.name -> "Plastics Owed"
                    else -> if (bottleTypeList.contains(type)) "$bottleName bottles owed" else "$type owed"
                }

                val displayAmount = if (type == DebtType.Cash.name) moneyFormat(amount)
                else halfAndQuarter(amount)

                Card(
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    elevation = CardDefaults.cardElevation(4.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Column(
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text(
                                text = label,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Text(
                                text = displayAmount,
                                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium)
                            )
                        }

                        // ------------------------------
                        // Actions
                        // ------------------------------
                        Options(
                            setInactive = { RecordsRepository.deactivateRecord(debtor) },
                            setActive = { RecordsRepository.reactivateRecord(debtor) },
                            onDelete = {
                                deleteRecordItemDialog = true
                                debtToDelete = type
                                       },
                            debtor = debtor
                        )
                    }
                }
            }
        }

        // --------------------------------------------------------
        // Delete Confirmation Dialog
        // --------------------------------------------------------
        if (deleteRecordItemDialog) {
            debtor?.let {
                CustomAlertDialog(
                    debtor=it,
                    debt=debtToDelete,
                    navController=navController,
                    caseKey = DialogKey.debtCard
                ) {
                    deleteRecordItemDialog = false
                }
            }
        }
    }
}
```

## File: app/src/main/java/com/example/debttracker/uidesigns/AddDebtor.kt
```kotlin
package com.example.debttracker.uidesigns

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.debttracker.DatabaseRefs
import com.example.debttracker.formatters.TimeAndDate
import com.example.debttracker.models.AddItemRow
import com.example.debttracker.models.BottomNavItem
import com.example.debttracker.models.Debt
import com.example.debttracker.models.Debtor
import com.example.debttracker.models.ItemType
import com.example.debttracker.models.AddDebtorViewModel
import com.google.firebase.database.ServerValue

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddDebt(navController: NavController, viewModel: AddDebtorViewModel) {

    var name by remember { mutableStateOf("") }
    var cash by remember { mutableStateOf("") }
    var empties by remember { mutableStateOf("") }
    var fulls by remember { mutableStateOf("") }
    var fullBottle by remember { mutableStateOf("") }
    var plastic by remember { mutableStateOf("") }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        //=======================
        //   PAGE HEADER
        //=======================
        Text(
            text = "Add Debt Record",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.primary
        )

        //=======================
        //   DEBTOR INFO CARD
        //=======================
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(4.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                Text(
                    "Debtor Information",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Debtor Name") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                OutlinedTextField(
                    value = cash,
                    onValueChange = { cash = it },
                    label = { Text("Cash Amount") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }
        }

        //=======================
        //   BOTTLE SECTION CARD
        //=======================
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(4.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                Text(
                    "Bottle Records",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                viewModel.bottleRows.collectAsState().value.forEachIndexed { index, bottleRow ->
                    AddItemRow(index, viewModel, ItemType.bottles)
                }


            }
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(4.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                Text(
                    "Empties Records",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                viewModel.emptiesRows.collectAsState().value.forEachIndexed { index, emptiesRow ->
                    AddItemRow(index, viewModel, ItemType.empties)
                }


            }
        }


        //=======================
        //   EXTRA ITEMS CARD
        //=======================
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(4.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                Text(
                    "Other Items",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )


                OutlinedTextField(
                    value = fulls,
                    onValueChange = { fulls = it },
                    label = { Text("Fulls") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                OutlinedTextField(
                    value = fullBottle,
                    onValueChange = { fullBottle = it },
                    label = { Text("Full Bottles") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                OutlinedTextField(
                    value = plastic,
                    onValueChange = { plastic = it },
                    label = { Text("Plastics") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }
        }

        //=======================
        //   SUBMIT BUTTON
        //=======================
        Button(
            onClick = {
                if (name.isBlank()) {
                    Toast.makeText(context, "Please enter a name.", Toast.LENGTH_SHORT).show()
                    return@Button
                }
                //handleSave(name, cash, empties, fulls, fullBottle, plastic, viewModel, navController, context)
                val debtorName: String

                if (name.isBlank()) {
                    Toast.makeText(context, "Enter name!", Toast.LENGTH_SHORT).show()
                } else {
                    debtorName = name
                    val dateAdded = TimeAndDate().GetDateString()
                    val timeAdded = TimeAndDate().GetTimeString()

                    //start
                    val items = mutableMapOf<String, Double>()

                    fun addItem(key: String, value: String) {
                        val n = value.replace(",", "").toDoubleOrNull()
                        if (n != null && n > 0) items[key] = n
                    }

                    addItem("Cash", cash)
                    addItem("Empty", empties)
                    addItem("Fulls", fulls)
                    addItem("Full_Bottle", fullBottle)
                    addItem("Plastic", plastic)

                    viewModel.bottleRows.value.forEach { row ->
                        val qty = row.qty.toDoubleOrNull() ?: 0.0
                        if (qty > 0) items[sanitizeKey(row.type)] = qty
                    }

                    val debt = Debt(
                        dateAdded = dateAdded,
                        timeAdded = timeAdded,
                        itemsOwed = items
                    )

                    saveRecord(
                        Debtor(
                            name = debtorName,
                            debt = debt
                        )
                    )

                    navController.navigate(BottomNavItem.Records.route)

                    Toast.makeText(context, "Debt record added!", Toast.LENGTH_SHORT).show()

                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(14.dp)
        ) {
            Text("Add to Records", fontSize = 17.sp, fontWeight = FontWeight.SemiBold)
        }
    }
}

fun saveRecord(debtor: Debtor){
    val recordRoot = DatabaseRefs.root.push()
    val key = recordRoot.key
//    debtor.id = key.key
//    debtor.timeStamp = ServerValue.TIMESTAMP
    val safeName = debtor.name?.let { sanitizeKey(it) }
    //safeDebt?.lastEdited = ServerValue.TIMESTAMP.toString()
    val debtorToMap =
        mutableMapOf(
        "id" to key,
        "timeStamp" to ServerValue.TIMESTAMP,
        "name" to safeName,
        "debt" to debtor.debt
    )

    recordRoot.setValue(debtorToMap)

}

fun safeMap(original: MutableMap<String, Any?>): MutableMap<String, Any?> {
    return original.mapKeys { entry ->
        sanitizeKey(entry.key)
    }.toMutableMap()
}
fun sanitizeKey(key: String): String {
    return key.replace(".", "_")
        .replace("#", "_")
        .replace("$", "_")
        .replace("[", "_")
        .replace("]", "_")
        .replace("/", "_")
}
```

## File: app/src/main/java/com/example/debttracker/uidesigns/DebtCard.kt
```kotlin
package com.example.debttracker.uidesigns

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.rememberNavController
import com.example.debttracker.Screen
import com.example.debttracker.formatters.TimeAndDate
import com.example.debttracker.formatters.moneyFormat
import com.example.debttracker.models.BottleType
import com.example.debttracker.models.Debt
import com.example.debttracker.models.DebtType
import com.example.debttracker.models.Debtor
import com.example.debttracker.models.AddDebtorViewModel
import com.example.debttracker.viewmodels.RecordsViewmodel


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DebtCard(
    vm: RecordsViewmodel,
    debtor: Debtor?,
    navC: NavController
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 8.dp),
        shape = CardDefaults.shape,
        elevation = CardDefaults.cardElevation(6.dp),
        onClick = {
            vm.selectedRecord = debtor
            navC.navigate(Screen.UpdateDebtScreen.route) {
                popUpTo(navC.graph.findStartDestination().id)
                launchSingleTop = true
            }
        },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                // 🔹 Debtor Name
                Text(
                    text = debtor?.name?:"noname",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )
                // Optional visual indicator for active debt
                val active = (debtor?.debt?.active == true)
                val indicatorColor = if (!active)
                    MaterialTheme.colorScheme.error
                else
                    MaterialTheme.colorScheme.primary

                Box(
                    modifier = Modifier
                        .size(12.dp)
                        .background(indicatorColor, CircleShape)
                )
            }

            // 🔹 Debts List
            val debts = debtor?.debt?.itemsOwed?.keys?.map { it }

            //Arranging all bottles into one display unit on the debt card
            //Details on bottle types are reserved for record details screen

            val noOfBottles = getNoOfBottles(debtor)
            var cash by remember { mutableStateOf(moneyFormat(0.0)) }
            val otherDebtTypes = mutableMapOf<String, String>()

            debts?.forEach { type ->
                val amountOwed = debtor.debt?.itemsOwed?.let { it[type]}
                if (checkType(type) == DebtType.Cash) {
                    cash = moneyFormat( amountOwed )
                }
                if (!isBottle(type) && checkType(type) != DebtType.Cash){
                    otherDebtTypes[type] = amountOwed?.toInt().toString()
                }
            }

            Column (
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                if (cash!= moneyFormat(0.0)) DisplayText("Cash owed", cash)
                if (noOfBottles>0) DisplayText("Bottles owed", noOfBottles.toString())
                otherDebtTypes.forEach {
                    DisplayText(it.key, it.value)
                }

            }

            // 🔹 Footer with Date
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(
                    onClick = {}
                ) {
                    
                }

                Text(
                    text = "${debtor?.debt?.dateAdded}, ${debtor?.debt?.timeAdded}",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.outline
                )
            }
        }
    }
}

@Composable
fun DisplayText(type: String, amount: String){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(
            text = type,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = amount,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium
        )
    }
}

fun isBottle(type: String): Boolean{
    val bottleTypes = BottleType.entries.toList()
    val bottlesList = bottleTypes.map { it.name.lowercase() }
    return bottlesList.contains(type.lowercase())
}

fun checkType(type: String): DebtType{
//    val debtTypes = DebtType.entries.toList()
//    val debtList = debtTypes.map { it.name.lowercase() }
    val bottleTypes = BottleType.entries.toList()
    val bottlesList = bottleTypes.map { it.name.lowercase() }
    val isBottle = bottlesList.contains(type.lowercase())
    val check = if (isBottle) DebtType.Bottle else{
        when(type){
            "Cash" -> DebtType.Cash
            "Empty" -> DebtType.Empty
            "Plastic" -> DebtType.Plastic
            "Fulls" -> DebtType.Fulls
            "FullBottle" -> DebtType.FullBottle
            else -> DebtType.Bottle
        }
    }
    return check
}
fun getNoOfBottles(debtor: Debtor?): Int{
    var noOfBottles = 0
    val bottleTypes = BottleType.entries.toList()
    val bottlesList = bottleTypes.map { it.name.lowercase() }
    val debtList = debtor?.debt?.itemsOwed
    debtList?.forEach { (debtType, amountOwed) ->
        if (debtType.lowercase() in bottlesList) noOfBottles += amountOwed.toInt()
    }
    return  noOfBottles

}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun ShowDebtCard(){
    DebtCard(
        vm = viewModel(),
        debtor = Debtor(
            id = "id",
            timeStamp = 456L,
            name = "chuks",
            debt = Debt(
                timeAdded = TimeAndDate().GetTimeString(),
                dateAdded = TimeAndDate().GetDateString(),
                //lastEdited = "",
                itemsOwed = mutableMapOf(
                    DebtType.Cash.name to 6300.0,
                    "nawa" to 550.5
                )

            )
        ),
        navC = rememberNavController()
    )
}
```
