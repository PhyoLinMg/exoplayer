package com.elemental.exoplayertesting.data


import com.google.gson.annotations.SerializedName

data class Videos(
    @SerializedName("resources")
    val videos: List<Video>,
    @SerializedName("updated_at")
    val updatedAt: String
)