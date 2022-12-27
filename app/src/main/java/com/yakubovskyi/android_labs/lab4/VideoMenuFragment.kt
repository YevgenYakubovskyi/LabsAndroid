package com.yakubovskyi.android_labs.lab4

import android.net.Uri
import android.os.Bundle
import android.provider.DocumentsContract
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import com.yakubovskyi.android_labs.R

class VideoMenuFragment(private val packageName: String) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inf = inflater.inflate(R.layout.fragment_video_menu, container, false)
        val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) {
            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.frameContent, VideoFragment(packageName, it))
                .commit()
        }
        inf.findViewById<Button>(R.id.buttonVideoExample).setOnClickListener {
            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.frameContent, VideoFragment(packageName, Uri.parse("android.resource://$packageName/${R.raw.video}")))
                .commit()
        }
        inf.findViewById<Button>(R.id.buttonVideoLocal).setOnClickListener {
            getContent.launch("video/*")
        }
        inf.findViewById<Button>(R.id.buttonVideoLink).setOnClickListener {

            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.frameContent, VideoLinkFragment())
                .commit()
        }
        return inf
    }

}