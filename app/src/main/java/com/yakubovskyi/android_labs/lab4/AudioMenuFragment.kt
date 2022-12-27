package com.yakubovskyi.android_labs.lab4

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import com.yakubovskyi.android_labs.R

class AudioMenuFragment(private val packageName: String) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inf = inflater.inflate(R.layout.fragment_audio_menu, container, false)

        val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) {
            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.frameContent, AudioFragment(it))
                .commit()
        }

        inf.findViewById<Button>(R.id.buttonAudioExample).setOnClickListener {
            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.frameContent, AudioFragment(Uri.parse("android.resource://$packageName/${R.raw.sexyback}")))
                .commit()
        }

        inf.findViewById<Button>(R.id.buttonAudioLocal).setOnClickListener {
            getContent.launch("audio/*")
        }

        inf.findViewById<Button>(R.id.buttonAudioLink).setOnClickListener {
            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.frameContent, AudioLinkFragment())
                .commit()
        }

        return inf
    }

}