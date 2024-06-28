package com.example.maximbar.ui.screen.main

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DataDialogBox(
    visible: MutableState<Boolean>,
    datePickerState: DatePickerState,
    viewModel: TableReservationVM
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
    ){
        val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        DatePickerDialog(
            onDismissRequest = { visible.value = false},
            confirmButton = {
                Button(
                    onClick = { visible.value = false
                        try {
                            viewModel.filterData = dateFormat.format(Date(datePickerState.selectedDateMillis!!))
                        } catch (e: Exception){
                            println("хуйня твой прога")
                        }
                       },
                    content = { Text(text = "Сохранить") }
                )
        }) {
            DatePicker(
                state = datePickerState,
                modifier = Modifier.padding(5.dp)
            )
        }
    }
}