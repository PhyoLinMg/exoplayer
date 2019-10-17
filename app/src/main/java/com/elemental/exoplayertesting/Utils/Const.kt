package com.elemental.exoplayertesting.Utils

import com.elemental.exoplayertesting.data.Video

object Const {
    const val LOCAL_API_END="http://127.0.0.1:8000/api/"
    const val ONLINE_API_END = "https://res.cloudinary.com/"

     fun createVideoUrl(video: Video) =
        "https://res.cloudinary.com/demo/video/${video.type}/v${video.version}/${video.publicId}.${video.format}"
}