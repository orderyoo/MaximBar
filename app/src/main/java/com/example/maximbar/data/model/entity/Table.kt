package com.example.maximbar.data.model.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Table(
    val id: Int,
    val seats: Int,
): Parcelable