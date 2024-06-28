package com.example.maximbar.ui.screen.main

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt

@SuppressLint("AutoboxingStateCreation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterDialogBox(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    viewModel: TableReservationVM,
    openDateDialogBox: MutableState<Boolean>
) {

    var checkedIsOccupied by remember { mutableStateOf(viewModel.filterOccupied.value) }
    var sliderPosition by remember { mutableStateOf(viewModel.filterSeats.value.toFloat()) }

    AlertDialog(
        title = {
            Text(text = "Сортировка столиков")

        },
        text = {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically){
                    Text(
                        text = "Свободные столики",
                        modifier = Modifier.weight(0.75f),
                        fontSize = 18.sp
                    )
                    Checkbox(
                        checked = checkedIsOccupied,
                        onCheckedChange = {
                            checkedIsOccupied = it
                        }
                    )

                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Дата",
                        modifier = Modifier.weight(0.75f),
                        fontSize = 18.sp
                    )
                    Button(
                       onClick = { openDateDialogBox.value = true},
                       content = { Icon(imageVector = Icons.Filled.Edit, contentDescription = null) },
                       shape = RoundedCornerShape(5.dp)
                    )

                }
                Column() {

                    val numberOfSteps = 8
                    val stepSize = 1f / (numberOfSteps - 1)
                    Text(text = "Количество посадочных мест: ${sliderPosition.toInt()}")
                    Slider(
                        value = sliderPosition,
                        onValueChange = {
                            sliderPosition = (it / stepSize).roundToInt() * stepSize
                        },
                        valueRange = 0f..8f,
                        steps = numberOfSteps - 2,
                    )
                }
            }
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                    viewModel.filterOccupied.value = checkedIsOccupied
                    viewModel.filterSeats.intValue = sliderPosition.toInt()
                },
                content = { Text("Сохранить") }
            )
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text("Отмена")
            }
        }
    )
}
