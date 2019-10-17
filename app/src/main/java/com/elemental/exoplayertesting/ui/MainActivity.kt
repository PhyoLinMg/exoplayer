package com.elemental.exoplayertesting.ui

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.elemental.exoplayertesting.R
import com.elemental.exoplayertesting.Utils.PlayMode
import com.elemental.exoplayertesting.player.AndroidPlayer
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var playMode = PlayMode.PLAY_LIST
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val url=intent.extras!!.getString("url")
        startPlayer(url!!)

    }
    private fun startPlayer(url:String){
        val uri=Uri.parse(url)
//        val videoUrl = "https://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4"
//        val videoUrl2="https://res.cloudinary.com/demo/video/upload/v1562834932/docs/animated_hearts.mp4"
//        val videoUrl3="https://res.cloudinary.com/demo/video/upload/v1524750367/Liverpool_vs_Roma_sum.mp4"
//        val uri = Uri.parse(videoUrl)
//        val uri1 = Uri.parse(videoUrl2)
//        val uri2 = Uri.parse(videoUrl3)
//        val uriList = arrayOf<Uri>(uri,uri1,uri2)
        playVideo(uri)


    }
    private fun playVideo(uri: Uri){
        //Here the videoView is
        AndroidPlayer(this, videoView).play(uri)
    }

    private fun playVideoList(uriList: Array<Uri>){
        AndroidPlayer(this, videoView).play(uriList)
    }
}
