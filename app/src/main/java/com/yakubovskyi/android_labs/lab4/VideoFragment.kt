package com.yakubovskyi.android_labs.lab4

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.VideoView
import com.yakubovskyi.android_labs.R

class VideoFragment(private val packageName: String, private val uri: Uri?) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inf = inflater.inflate(R.layout.fragment_video, container, false)
        val videoView = inf.findViewById<VideoView>(R.id.videoView)
        // MediaController
        val mediaController = MediaController(context)
        mediaController.setAnchorView(videoView)
        // VideoView
        videoView.setVideoURI(uri)
        videoView.setMediaController(mediaController)
        videoView.requestFocus()
        videoView.start()
        return inf
    }
}