package com.example.sepapp.util

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter


class TimeHelper {
    companion object {
        fun parseDateTimeStringToLocalMillis(dateTime: String): Long? {
            /*val localDateTime = LocalDateTime.parse(
                dateTime,
                DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")
            )*/
            val timeFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            val localDateTime = timeFormat.parse(dateTime)
            return localDateTime?.time
        }
    }
}