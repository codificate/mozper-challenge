package com.challenge.mozper.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.challenge.mozper.domain.model.Employee
import com.challenge.mozper.domain.repository.MozperDaoRepository

@Database(entities = [Employee::class], version = 1)
abstract class MozperDatabase: RoomDatabase() {
    abstract fun employeesDao(): MozperDaoRepository
}