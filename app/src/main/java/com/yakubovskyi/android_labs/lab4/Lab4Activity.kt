package com.yakubovskyi.android_labs.lab4


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.yakubovskyi.android_labs.R

class Lab4Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lab4)

        findViewById<Button>(R.id.buttonOpen).setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.frameContent, MenuFragment(packageName))
                .commit()
        }
    }
}