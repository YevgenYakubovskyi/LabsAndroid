package com.yakubovskyi.android_labs.lab4

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.MediaController
import android.widget.VideoView
import com.yakubovskyi.android_labs.R

class VideoLinkFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inf = inflater.inflate(R.layout.fragment_video_link, container, false)
        val videoView = inf.findViewById<VideoView>(R.id.videoViewLink)
        // MediaController
        val mediaController = MediaController(context)
        mediaController.setAnchorView(videoView)
        mediaController.setMediaPlayer(videoView)
        // VideoView
        videoView.setMediaController(mediaController)
        val input = inf.findViewById<EditText>(R.id.inputLink)
        input.setText("https://assets.mixkit.co/videos/preview/mixkit-going-down-a-curved-highway-through-a-mountain-range-41576-large.mp4")
        inf.findViewById<Button>(R.id.buttonLink).setOnClickListener {
            videoView.setVideoURI(Uri.parse(input.text.toString()))
            videoView.requestFocus()
            videoView.start()
        }
        return inf
    }

}