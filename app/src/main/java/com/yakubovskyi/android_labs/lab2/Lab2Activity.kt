package com.yakubovskyi.android_labs.lab2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.yakubovskyi.android_labs.R

class Lab2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lab2)

        val numberOne = findViewById<EditText>(R.id.editTextNumber)
        val numberTwo = findViewById<EditText>(R.id.editTextNumber2)
        val Sum = findViewById<RadioButton>(R.id.radioButton3)
        val Dec = findViewById<RadioButton>(R.id.radioButton4)

        findViewById<Button>(R.id.buttonOk).setOnClickListener {
            val Num1: Int = numberOne.text.toString().toInt()
            val Num2: Int = numberTwo.text.toString().toInt()
            var Res = 0
            if (Dec.isChecked) {
                Res = Num1 - Num2
            } else if (Sum.isChecked) {
                Res = Num1 + Num2
            }
            supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.frameHolder,
                    Lab2ResultFragment(getText(R.string.result_text).toString() + " " + Res)
                )
                .commit()
        }
    }
}