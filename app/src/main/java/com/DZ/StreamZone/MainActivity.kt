package com.DZ.StreamZone

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.media3.common.MediaItem
import androidx.media3.common.MimeTypes
import androidx.media3.exoplayer.ExoPlayer
import com.DZ.StreamZone.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var player: ExoPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initPlayer()
        setupTabs()
        
        // تشغيل بث تجريبي عند فتح التطبيق (قناة مباشرة)
        playStream("https://test-streams.mux.dev/x36xhzz/x36xhzz.m3u8", isHls = true)
    }

    private fun initPlayer() {
        player = ExoPlayer.Builder(this).build()
        binding.playerView.player = player
    }

    private fun playStream(url: String, isHls: Boolean) {
        player?.let { exoPlayer ->
            val mediaItem = if (isHls) {
                MediaItem.Builder()
                    .setUri(Uri.parse(url))
                    .setMimeType(MimeTypes.APPLICATION_M3U8)
                    .build()
            } else {
                MediaItem.fromUri(url)
            }
            
            exoPlayer.setMediaItem(mediaItem)
            exoPlayer.prepare()
            exoPlayer.playWhenReady = true
        }
    }

    private fun setupTabs() {
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> {
                        // قسم القنوات المباشرة (اختبار رابط HLS)
                        playStream("https://test-streams.mux.dev/x36xhzz/x36xhzz.m3u8", isHls = true)
                    }
                    1 -> {
                        // قسم الأفلام (اختبار رابط MP4)
                        playStream("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4", isHls = false)
                    }
                    2 -> {
                        // قسم المسلسلات (اختبار رابط حلقات MP4)
                        playStream("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4", isHls = false)
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    override fun onStop() {
        super.onStop()
        player?.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        player?.release()
        player = null
    }
}