package com.yakubovskyi.android_labs.lab4

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.SeekBar
import android.widget.TextView
import com.yakubovskyi.android_labs.R
import java.lang.NullPointerException

class AudioFragment(private val music: Uri?) : Fragment() {

    private var mediaPlayer: MediaPlayer? = null
    private lateinit var seekBar: SeekBar
    private var minutes: Int = 0
    private var seconds: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inf = inflater.inflate(R.layout.fragment_audio, container, false)
        val buttonPlay = inf.findViewById<ImageButton>(R.id.buttonPlay)
        val buttonStop = inf.findViewById<ImageButton>(R.id.buttonStop)
        seekBar = inf.findViewById(R.id.seekBar)
        seekBar.isEnabled = false

        buttonPlay.setOnClickListener {
            if (mediaPlayer == null) {
                mediaPlayer = MediaPlayer.create(context, music)
                mediaPlayer!!.isLooping = true
                mediaPlayer!!.start()
                buttonPlay.setImageResource(R.drawable.pause)
                startSeekBar(inf)
                seekBar.isEnabled = true
            } else {
                if (mediaPlayer!!.isPlaying) {
                    mediaPlayer!!.pause()
                    buttonPlay.setImageResource(R.drawable.play)
                } else {
                    mediaPlayer!!.start()
                    buttonPlay.setImageResource(R.drawable.pause)
                    startSeekBar(inf)
                }
            }
        }

        buttonStop.setOnClickListener {
            if (mediaPlayer != null) {
                mediaPlayer!!.stop()
                mediaPlayer!!.release()
                mediaPlayer = null
                buttonPlay.setImageResource(R.drawable.play)
                seekBar.progress = 0
                inf.findViewById<TextView>(R.id.timeText).text = "00:00"
                seekBar.isEnabled = false
            }
        }

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                if (p2 && mediaPlayer != null) {
                    mediaPlayer?.seekTo(p1)
                    inf.findViewById<TextView>(R.id.timeText).text = String.format("%02d:%02d",
                        p1/1000/60,
                        p1/1000%60)
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }

        })

        return inf
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mediaPlayer != null) {
            mediaPlayer!!.stop()
            mediaPlayer!!.release()
            mediaPlayer = null
        }
    }

    private fun startSeekBar(inf: View) {
        seekBar.max = mediaPlayer!!.duration

        Thread {
            try {
                while (mediaPlayer!!.isPlaying) {
                    seekBar.progress = mediaPlayer!!.currentPosition

                    activity?.runOnUiThread {
                        inf.findViewById<TextView>(R.id.timeText).text = String.format("%02d:%02d", minutes, seconds)
                    }
                    seconds = mediaPlayer!!.currentPosition/1000%60
                    minutes = mediaPlayer!!.currentPosition/1000/60

                    Thread.sleep(1000)
                }
            } catch (e: NullPointerException) {
                seekBar.progress = 0
            }
        }.start()
    }
}