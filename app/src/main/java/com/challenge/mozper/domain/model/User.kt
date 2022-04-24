package com.challenge.mozper.domain.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

@Entity
@Parcelize
data class User(
    @PrimaryKey val email: String,
    @ColumnInfo(name = "last_session") val lastSession: Date
): Parcelable