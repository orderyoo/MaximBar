package com.example.maximbar.ui.screen.main

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSavedStateRegistryOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.maximbar.data.repository.Repository

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TableReservation(
    viewModel: TableReservationVM = viewModel(
        factory = TableReservationVM.provideFactory(
            Repository(),
            owner = LocalSavedStateRegistryOwner.current
        )
    ),
    navController: NavController
){
    val openAlertDialog = remember { mutableStateOf(false) }
    val openDateDialog = remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Бронирование стола") },
                navigationIcon = { Icon(imageVector = Icons.Filled.Search, contentDescription = null)
                },
                actions = {
                    IconButton(
                        onClick = { openAlertDialog.value = true},
                        content = { Icon(imageVector = Icons.Filled.FilterAlt, contentDescription = null)}
                    )
                }
            )
        }
    ) {innerPadding ->
        Box(
            modifier = Modifier.padding(innerPadding)
        ){
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.padding(16.dp),
                contentPadding = PaddingValues(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(viewModel.filteredTables?: listOf()){
                    TableElement(
                        tableOccupation = it,
                        navController = navController
                    )
                }
            }
        }
    }

    if(openAlertDialog.value){
        FilterDialogBox(
            onDismissRequest = { openAlertDialog.value = false },
            onConfirmation = { openAlertDialog.value = false },
            viewModel = viewModel,
            openDateDialogBox = openDateDialog
        )
    }
    if(openDateDialog.value){
        DataDialogBox(
            datePickerState = datePickerState,
            visible = openDateDialog,
            viewModel = viewModel
        )
    }

}
