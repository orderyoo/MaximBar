package com.example.maximbar.ui.screen.placingAnOrder

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun RowCategory(
    listCategory: List<String>,
    onCategorySelected: (String) -> Unit
){

    var selectedCategory by remember { mutableStateOf(listCategory[0]) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        listCategory.forEach { item -> 
            Button(
                modifier = Modifier.padding(1.dp),
                onClick = {
                    selectedCategory = item
                    onCategorySelected(item)
                },
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    containerColor =  if (selectedCategory == item) Color.Blue else Color.Gray,

                ),
                shape = RoundedCornerShape(5.dp),
                content = { Text(text = item) }
            ) 
        }
    }
}