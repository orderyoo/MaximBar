package com.example.maximbar.ui.screen.authorization

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showBackground = true)
@Composable
fun Authorization(){

    var login by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Регистрация",
                modifier = Modifier.padding(5.dp),
                fontSize = 24.sp
            )

            OutlinedTextField(
                value = login,
                onValueChange = { login = it },
                modifier = Modifier.padding(5.dp),
                label = { Text(text = "Имя") },
                shape = RoundedCornerShape(10.dp)
            )

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier.padding(5.dp),
                label = { Text(text = "Пароль") },
                shape = RoundedCornerShape(10.dp)
            )

            Button(
                onClick = { },
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.padding(5.dp),
                content = {Text(text = "Зарегистрироваться")}
            )

        }
    }

}