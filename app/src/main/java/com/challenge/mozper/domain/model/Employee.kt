package com.challenge.mozper.domain.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class Employee(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "first_name") val firstName: String?,
    @ColumnInfo(name = "image") val image: String?,
    @ColumnInfo(name = "last_name") val lastName: String?,
    @ColumnInfo(name = "rating") val rating: Double?
): Parcelable
