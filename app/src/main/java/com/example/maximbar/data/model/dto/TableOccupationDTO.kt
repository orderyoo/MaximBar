package com.example.maximbar.data.model.dto

import com.example.maximbar.data.model.entity.Table

data class TableOccupation(
    val table: Table,
    val isOccupied: Boolean
)