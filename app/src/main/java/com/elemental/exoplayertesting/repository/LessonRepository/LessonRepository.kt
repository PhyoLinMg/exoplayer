package com.elemental.exoplayertesting.repository.LessonRepository

import androidx.lifecycle.LiveData
import com.elemental.exoplayertesting.Utils.DataLoadState
import com.elemental.exoplayertesting.data.Lesson
import kotlinx.coroutines.CoroutineScope

interface LessonRepository:CoroutineScope {
    fun getDataLoadState(): LiveData<DataLoadState>
    fun loadlessons()
    fun getlessons(): LiveData<List<Lesson>>
    fun cancelJob()
}