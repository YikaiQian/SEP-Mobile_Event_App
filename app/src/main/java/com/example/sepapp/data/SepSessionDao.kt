package com.example.sepapp.data

import androidx.room.*

@Dao
interface SepSessionDao {
    @Query("SELECT * FROM sepSessions")
    fun getAllSessions():List<SepSession>

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSession(sepSession: SepSession)

    @Delete
    suspend fun deleteSession(sepSession:SepSession)
    
}