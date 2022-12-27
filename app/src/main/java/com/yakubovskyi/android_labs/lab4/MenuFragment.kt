package com.yakubovskyi.android_labs.lab4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.yakubovskyi.android_labs.R

class MenuFragment(private val packageName: String) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inf = inflater.inflate(R.layout.fragment_menu, container, false)
        inf.findViewById<Button>(R.id.buttonOpenVideo).setOnClickListener {
            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.frameContent, VideoMenuFragment(packageName))
                .commit()
        }
        inf.findViewById<Button>(R.id.buttonOpenAudio).setOnClickListener {
            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.frameContent, AudioMenuFragment(packageName))
                .commit()
        }
        return inf
    }
}