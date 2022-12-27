package com.yakubovskyi.android_labs.lab2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.yakubovskyi.android_labs.R

class Lab2ResultFragment(private var res: String) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val inf = inflater.inflate(R.layout.fragment_lab2_result, container, false)

        inf.findViewById<TextView>(R.id.textResult).text = res
        inf.findViewById<Button>(R.id.buttonCancel).setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
        }

        return inf
    }
}