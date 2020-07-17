package com.example.sepapp.data

import com.squareup.moshi.Json

data class SepEvent(
    @Json(name="eventName") val eventName: String,
    val imageSrc: String,
    val description: String
)