package com.elemental.exoplayertesting.repository.HomeRepository

import androidx.lifecycle.LiveData
import com.elemental.exoplayertesting.Utils.DataLoadState
import com.elemental.exoplayertesting.data.Video
import kotlinx.coroutines.CoroutineScope

interface HomeRepository: CoroutineScope {
    fun getDataLoadState(): LiveData<DataLoadState>
    fun loadApi()
    fun getApi(): LiveData<List<Video>>
    fun cancelJob()
}