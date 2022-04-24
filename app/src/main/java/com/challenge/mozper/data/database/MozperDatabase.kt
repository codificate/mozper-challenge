package com.challenge.mozper.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.challenge.mozper.domain.model.Employee
import com.challenge.mozper.domain.model.User
import com.challenge.mozper.domain.repository.LoginDaoRepository
import com.challenge.mozper.domain.repository.MozperDaoRepository

@Database(entities = [Employee::class, User::class], version = 1)
@TypeConverters(RoomTimeConverter::class)
abstract class MozperDatabase: RoomDatabase() {
    abstract fun employeesDao(): MozperDaoRepository
    abstract fun userDao(): LoginDaoRepository
}