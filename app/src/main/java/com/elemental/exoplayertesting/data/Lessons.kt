package com.elemental.exoplayertesting.data

import com.google.gson.annotations.SerializedName


data class Lessons(
    @SerializedName("data")
    val lessons: List<Lesson>
)