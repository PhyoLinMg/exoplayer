package com.elemental.exoplayertesting.ViewModel.Lesson

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.elemental.exoplayertesting.repository.LessonRepository.LessonRepository

class LessonViewModelFactory(private val lessonRepository: LessonRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LessonViewModel(lessonRepository) as T
    }
}