package com.yakubovskyi.android_labs.lab1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.yakubovskyi.android_labs.R

class Lab1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lab1)

        val numberOne = findViewById<EditText>(R.id.editTextNumber)
        val numberTwo = findViewById<EditText>(R.id.editTextNumber2)
        val Sum = findViewById<RadioButton>(R.id.radioButton3)
        val Dec = findViewById<RadioButton>(R.id.radioButton4)
        val buttonOk = findViewById<Button>(R.id.buttonOk)
        val textResult = findViewById<TextView>(R.id.textResult)

        buttonOk.setOnClickListener {
            try {
                val Num1: Int = numberOne.text.toString().toInt()
                val Num2: Int = numberTwo.text.toString().toInt()
                if (Dec.isChecked) {
                    val Res: Int = Num1 - Num2
                    textResult.text = buildString {
                        append(getText(R.string.result_text).toString())
                        append(": ")
                        append(Res)
                    }
                } else if (Sum.isChecked) {
                    val Res: Int = Num1 + Num2
                    textResult.text = buildString {
                        append(getText(R.string.result_text).toString())
                        append(": ")
                        append(Res)
                    }
                }
            } catch (e: java.lang.Exception) {
                textResult.text = "Помилка. Невірно ввели дані"
            }
        }
    }
}
