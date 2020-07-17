package com.example.sepapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [SepSession::class], version = 1, exportSchema = false)
abstract class SepSessionDatabase : RoomDatabase() {
    abstract fun sepSessionDao(): SepSessionDao

    companion object {
        @Volatile
        private var INSTANCE: SepSessionDatabase? = null

        fun getDatabase(context: Context): SepSessionDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        SepSessionDatabase::class.java,
                        "sepSession.db"
                    ).build()
                }
            }
            return INSTANCE!!
        }
    }
}