package com.example.maximbar.ui.screen.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.maximbar.data.model.dto.TableOccupation
import com.google.gson.Gson

@Composable
fun TableElement(
    tableOccupation: TableOccupation,
    navController: NavController
){
    val colorState = if(tableOccupation.isOccupied)
        Color.Red
    else
        Color.Green

    Column(
        modifier = Modifier
            .clickable{
                val tableJson = Gson().toJson(tableOccupation.table)
                navController.navigate("order/$tableJson")
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Card(
            modifier = Modifier
                .size(150.dp)
                .padding(5.dp),
            shape = RoundedCornerShape(15.dp),
            colors = CardDefaults.cardColors(containerColor = colorState),
            elevation = CardDefaults.cardElevation(defaultElevation = 15.dp)
        ){
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = tableOccupation.table.seats.toString(),
                    color = Color.Black,
                    fontSize = 32.sp
                )
            }
        }
        Text(text = "Номер стола ${tableOccupation.table.id}")
    }
}