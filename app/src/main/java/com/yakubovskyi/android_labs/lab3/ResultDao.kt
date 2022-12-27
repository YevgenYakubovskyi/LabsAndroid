package com.yakubovskyi.android_labs.lab3

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ResultDao {
    @Insert
    fun insert(result: Result)

    @Query("DELETE FROM results")
    fun clearTable()

    @Query("SELECT * FROM results")
    fun getAllResults() : Flow<List<Result>>

    @Query("SELECT count(*) FROM results")
    fun getCount() : Int
}