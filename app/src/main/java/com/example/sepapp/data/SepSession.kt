package com.example.sepapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "sepSessions")
data class SepSession(
    @PrimaryKey val sepSessionId:Int,
    @Json(name="sessionName") val sessionName: String,
    val imageSrc: String,
    val description: String,
    val date: String,
    val speaker: String
)