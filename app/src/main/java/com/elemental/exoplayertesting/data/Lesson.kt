package com.elemental.exoplayertesting.data


import com.google.gson.annotations.SerializedName

data class Lesson(
    @SerializedName("name")
    val name: String,
    @SerializedName("video")
    val video: String
)