package com.example.demo_youtube

import android.view.View
import android.widget.Button
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer

internal class CustomUiController(customPlayerUI: View, youTubePlayer: YouTubePlayer) {
    private var isPlaying = false

    init {
        val playPauseButton = customPlayerUI.findViewById<Button>(R.id.play_pause_button)
        playPauseButton.setOnClickListener { view: View? ->
            if (isPlaying) youTubePlayer.pause() else youTubePlayer.play()
            isPlaying = !isPlaying
        }
    }
}