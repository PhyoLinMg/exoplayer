package com.elemental.exoplayertesting.data


import com.google.gson.annotations.SerializedName

data class Video(
    @SerializedName("created_at")
    val createdAt: String,
    val format: String,
    val height: Int,
    @SerializedName("public_id")
    val publicId: String,
    val type: String,
    val version: Int,
    val width: Int
)