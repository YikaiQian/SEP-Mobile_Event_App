package com.example.sepapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

/**
 * SEP Session data class. Contains all the information of a specific session.
 * Session class also serves as the schema of database table.
 */

@Entity(tableName = "sepSessions")
data class SepSession(
    @PrimaryKey val sepSessionId:Int,
    @Json(name="sessionName") val sessionName: String,
    @Json(name="imageSrc") val imageSrc: String,
    @Json(name="description") val description: String,
    @Json(name="date") val date: String,
    @Json(name="speaker") val speaker: String
)