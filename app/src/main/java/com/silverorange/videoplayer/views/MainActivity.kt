package com.silverorange.videoplayer.views

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import androidx.databinding.DataBindingUtil
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.silverorange.videoplayer.R
import com.silverorange.videoplayer.databinding.ActivityMainBinding
import com.silverorange.videoplayer.viewModels.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )
    }

    private val viewModel by viewModel<MainViewModel>()
    private val btnPlay by lazy { binding.root.findViewById<ImageButton>(R.id.exo_play) }
    private val btnPause by lazy { binding.root.findViewById<ImageButton>(R.id.exo_pause) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.playerView.controllerAutoShow = false

//        viewModel.currentVideo.observe(this) {
//            val player = ExoPlayer.Builder(this).build()
//            binding.playerView.player = player
//            player.setMediaItem(MediaItem.fromUri(it.url))
//            player.prepare()
//            player.addListener(object : Player.Listener {
//                override fun onIsPlayingChanged(isPlaying: Boolean) {
//                    btnPlay.isInvisible = isPlaying
//                    btnPause.isInvisible = !isPlaying
//                    super.onIsPlayingChanged(isPlaying)
//
//                }
//
//            })
//        }

        viewModel.videos.observe(this) { videos ->
            val player = ExoPlayer.Builder(this).build()
            binding.playerView.player = player
            videos.forEachIndexed { index, video ->
                player.addMediaItem(
                    MediaItem.Builder().setUri(video.url).setTag(index).build()
                )
            }
            player.prepare()
            player.addListener(object : Player.Listener {
                override fun onIsPlayingChanged(isPlaying: Boolean) {
                    btnPlay.isInvisible = isPlaying
                    btnPause.isInvisible = !isPlaying
                    super.onIsPlayingChanged(isPlaying)

                }

                override fun onMediaItemTransition(mediaItem: MediaItem?, reason: Int) {
                    (mediaItem?.localConfiguration?.tag as? Int)?.let { tag ->
                        viewModel.currentVideo.postValue(
                            videos[tag]
                        )
                    }
                    super.onMediaItemTransition(mediaItem, reason)
                }

            })
        }
        btnPlay.setOnClickListener { binding.playerView.player?.play() }
        btnPause.setOnClickListener { binding.playerView.player?.pause() }

    }
}