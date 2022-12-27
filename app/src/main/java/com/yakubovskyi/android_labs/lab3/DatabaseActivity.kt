package com.yakubovskyi.android_labs.lab3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.asLiveData
import com.yakubovskyi.android_labs.R


class DatabaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_database)
        val database = ResultDatabase.getDatabase(this)
        database.resultDao().getAllResults().asLiveData().observe(this) { list ->
            list.forEach {
                findViewById<TextView>(R.id.textViewList).append(it.result + "\n")
            }
        }
    }
}