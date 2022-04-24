package com.challenge.mozper.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Employee(
    val description: String,
    val firstName: String,
    val id: Int,
    val image: String,
    val lastName: String,
    val rating: Double
): Parcelable
