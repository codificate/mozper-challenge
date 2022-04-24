package com.challenge.mozper.domain.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.challenge.mozper.domain.model.User

@Dao
interface LoginDaoRepository {

    @Query("SELECT * FROM user WHERE last_session < strftime('%s', 'now') ORDER BY last_session DESC LIMIT 1")
    suspend fun getUserSession(): User?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg user: User)

}