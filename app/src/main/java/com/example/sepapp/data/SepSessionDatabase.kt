package com.example.sepapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


/**
 * Define the database.
 * Implement the session DAO.
 */
@Database(entities = [SepSession::class], version = 1, exportSchema = false)
abstract class SepSessionDatabase : RoomDatabase() {

    abstract fun sepSessionDao(): SepSessionDao

    companion object {
        @Volatile
        private var INSTANCE: SepSessionDatabase? = null

        /**
         * Create only one database instance. (Singleton design pattern)
         */
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