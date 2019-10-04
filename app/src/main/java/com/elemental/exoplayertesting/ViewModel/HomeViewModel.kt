package com.elemental.exoplayertesting.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.elemental.exoplayertesting.Utils.DataLoadState
import com.elemental.exoplayertesting.data.Video
import com.elemental.exoplayertesting.repository.HomeRepository

class HomeViewModel(private val homeRepository: HomeRepository):ViewModel() {
    fun loadApi(){
        return homeRepository.loadApi()
    }
    fun getApi(): LiveData<List<Video>> {
        return homeRepository.getApi()
    }


    fun getLoadState(): LiveData<DataLoadState> {
        return homeRepository.getDataLoadState()
    }
    fun cancelJob(){
        homeRepository.cancelJob()
    }


}