package com.yakubovskyi.android_labs.lab4

import android.media.AudioManager
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.yakubovskyi.android_labs.R
import wseemann.media.FFmpegMediaPlayer
import java.lang.NullPointerException

class AudioLinkFragment : Fragment() {

    private var mediaPlayer: FFmpegMediaPlayer? = null
    private lateinit var seekBar: SeekBar
    private var minutes: Int = 0
    private var seconds: Int = 0
    private var music: Uri = Uri.EMPTY

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inf = inflater.inflate(R.layout.fragment_audio_link, container, false)
        val buttonPlay = inf.findViewById<ImageButton>(R.id.buttonPlay)
        val buttonStop = inf.findViewById<ImageButton>(R.id.buttonStop)
        seekBar = inf.findViewById(R.id.seekBar)
        seekBar.isEnabled = false
        buttonPlay.setOnClickListener {
            if (music != Uri.EMPTY) {
                if (mediaPlayer == null){
                    buttonPlay.setImageResource(R.drawable.pause)
                    mediaPlayer = FFmpegMediaPlayer()
                    mediaPlayer!!.isLooping = true
                    mediaPlayer!!.setAudioStreamType(AudioManager.STREAM_MUSIC)
                    mediaPlayer!!.setOnPreparedListener {
                        mediaPlayer!!.start()
                        startSeekBar(inf)
                    }

                    mediaPlayer!!.setDataSource(requireContext(), music)
                    mediaPlayer!!.prepareAsync()

                    mediaPlayer!!.setOnErrorListener { _, _, _ ->
                        buttonPlay.setImageResource(R.drawable.play)
                        Toast.makeText(context, getText(R.string.invalid_link), Toast.LENGTH_SHORT).show()
                        return@setOnErrorListener false
                    }

                    mediaPlayer!!.setOnCompletionListener {
                        mediaPlayer!!.stop()
                        mediaPlayer!!.release()
                        mediaPlayer = null
                    }

                } else {
                    if (mediaPlayer != null) {
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
            } else {
                Toast.makeText(context, getText(R.string.link_empty), Toast.LENGTH_SHORT).show()
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

        val input = inf.findViewById<EditText>(R.id.inputLink)
        input.setText("https://www.learningcontainer.com/wp-content/uploads/2020/02/Kalimba.mp3")

        inf.findViewById<Button>(R.id.buttonLink).setOnClickListener {
            music = Uri.parse(input.text.toString())
        }

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
        seekBar.isEnabled = true
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