package com.elemental.exoplayertesting

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.elemental.exoplayertesting.player.AndroidPlayer
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private enum class PlayMode { SINGLE_VIDEO, PLAY_LIST }
    private var playMode = PlayMode.SINGLE_VIDEO
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        startDemoOfAndroidPlayer()

    }
    private fun startDemoOfAndroidPlayer(){
        val videoUrl = "https://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4"
        val uri = Uri.parse(videoUrl)
        val uriList = arrayOf<Uri>(uri,uri,uri,uri)

        when(playMode){
            PlayMode.SINGLE_VIDEO -> playVideo(uri)
            PlayMode.PLAY_LIST -> playVideoList(uriList)
        }
    }
    private fun playVideo(uri: Uri){
        //Here the videoView is
        AndroidPlayer(this, videoView).play(uri)
    }

    private fun playVideoList(uriList: Array<Uri>){
        AndroidPlayer(this, videoView).play(uriList)
    }
}
