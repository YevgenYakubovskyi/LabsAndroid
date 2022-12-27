package com.yakubovskyi.android_labs.lab3

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Result::class], version = 1)
abstract class ResultDatabase : RoomDatabase() {

    abstract fun resultDao() : ResultDao

    companion object {
        @Volatile
        private var INSTANCE: ResultDatabase? = null

        fun getDatabase(context: Context): ResultDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = buildDatabase(context)
                }
            }
            return INSTANCE!!
        }

        private fun buildDatabase(context: Context): ResultDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                ResultDatabase::class.java,
                "results_database"
            ).build()
        }
    }
}