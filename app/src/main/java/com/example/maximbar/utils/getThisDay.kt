package com.example.maximbar.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun getThisDay(): String {
    val currentDate = Date()
    val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
    return dateFormat.format(currentDate)
}