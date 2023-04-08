package com.example.demo_youtube

import android.R
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.demo_youtube.databinding.ActivityMainBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import java.util.regex.Matcher
import java.util.regex.Pattern


class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


//        val videoUrl  = "https://www.youtube.com/watch?v=CeFQO9MQNqs&list=RDCeFQO9MQNqs&start_radio=1&ab_channel=Badshah"   // song
        val videoUrl  = "https://www.youtube.com/watch?v=Q5ZLPJlrJVI&ab_channel=Cocomelon-NurseryRhymes"                        // live

        lifecycle.addObserver(binding.youtubeView)
//
//        val options: IFramePlayerOptions = IFramePlayerOptions.Builder().controls(0).build()
//        binding.youtubeView.initialize(listener, options)

//        val customPlayerUi: View = binding.youtubeView.inflateCustomPlayerUi(R.layout.custom_player_ui)

//        val customUiView: View = binding.youtubeView.inflateCustomPlayerUI(R.layout.custom_player_ui)


        binding.youtubeView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                val videoId =   getVideoId(videoUrl)
                youTubePlayer.loadVideo(videoId.toString(), 0f)
            }
        })

    }

    private fun getVideoId(youtubeVideoUrl: String?): String? {
        var videoId: String? = null
        val regex =
            "^((?:https?:)?//)?((?:www|m)\\.)?((?:youtube\\.com|youtu.be|youtube-nocookie.com))(/(?:[\\w\\-]+\\?v=|feature=|watch\\?|e/|embed/|v/)?)([\\w\\-]+)(\\S+)?\$"
        val pattern: Pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE)
        val matcher: Matcher = pattern.matcher(youtubeVideoUrl)
        if (matcher.matches()) {
            videoId = matcher.group(5)
        }
        return videoId
    }
}
