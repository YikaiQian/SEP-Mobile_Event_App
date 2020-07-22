package com.example.sepapp.data

import androidx.room.*

/**
 * The Data access object is a persistence layer providing an interface to the session database
 * Supported Operations : Get All, Insert, Delete
 */

@Dao
interface SepSessionDao {
    @Query("SELECT * FROM sepSessions")
    fun getAllSessions():List<SepSession>

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSession(sepSession: SepSession)

    @Delete
    suspend fun deleteSession(sepSession:SepSession)
    
}