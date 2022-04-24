package com.challenge.mozper.domain.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.challenge.mozper.domain.model.Employee

@Dao
interface MozperDaoRepository {

    @Query("SELECT * FROM employee")
    suspend fun getAllEmployees(): List<Employee>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg employee: Employee)

}