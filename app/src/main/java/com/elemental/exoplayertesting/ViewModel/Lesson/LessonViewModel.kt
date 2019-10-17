package com.elemental.exoplayertesting.ViewModel.Lesson

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.elemental.exoplayertesting.Utils.DataLoadState
import com.elemental.exoplayertesting.data.Lesson
import com.elemental.exoplayertesting.repository.LessonRepository.LessonRepository

class LessonViewModel(val lessonRepository: LessonRepository):ViewModel() {

    fun loadLessons(){
        return lessonRepository.loadlessons()
    }
    fun getLessons(): LiveData<List<Lesson>> {
        return lessonRepository.getlessons()
    }


    fun getLoadState(): LiveData<DataLoadState> {
        return lessonRepository.getDataLoadState()
    }
    fun cancelJob(){
        lessonRepository.cancelJob()
    }

}