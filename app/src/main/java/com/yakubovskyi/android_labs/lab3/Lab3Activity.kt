package com.yakubovskyi.android_labs.lab3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.yakubovskyi.android_labs.R

class Lab3Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lab3)

        val database = ResultDatabase.getDatabase(this)

        findViewById<Button>(R.id.buttonAdd).setOnClickListener {
            Thread {
                val Num1: Int = findViewById<EditText>(R.id.editTextNumber).text.toString().toInt()
                val Num2: Int = findViewById<EditText>(R.id.editTextNumber2).text.toString().toInt()
                if (findViewById<RadioButton>(R.id.radioButton4).isChecked) {
                    val Res: Int = Num1 - Num2
                    val result = Result(null, Res.toString())
                    database.resultDao().insert(result)
                    runOnUiThread {
                        Toast.makeText(this, getText(R.string.successful), Toast.LENGTH_SHORT)
                            .show()
                    }
                } else if (findViewById<RadioButton>(R.id.radioButton3).isChecked) {
                    val Res: Int = Num1 + Num2
                    val result = Result(null, Res.toString())
                    database.resultDao().insert(result)
                    runOnUiThread {
                        Toast.makeText(this, getText(R.string.successful), Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }.start()
        }
        findViewById<Button>(R.id.buttonDatabase).setOnClickListener {
            Thread {
                if (database.resultDao().getCount() == 0) {
                    runOnUiThread {
                        Toast.makeText(this, getText(R.string.empty), Toast.LENGTH_SHORT).show()
                    }
                } else {
                    startActivity(Intent(this, DatabaseActivity::class.java))
                }
            }.start()
        }
        findViewById<Button>(R.id.buttonClear).setOnClickListener {
            Thread {
                database.resultDao().clearTable()
            }.start()
        }
    }
}