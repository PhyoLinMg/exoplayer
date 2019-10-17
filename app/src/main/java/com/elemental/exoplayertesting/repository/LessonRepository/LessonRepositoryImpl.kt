package com.elemental.exoplayertesting.repository.LessonRepository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.elemental.atantat.network.ConnectivityInterceptorImpl
import com.elemental.atantat.network.NoConnectivityException
import com.elemental.atantat.network.services.MainService
import com.elemental.exoplayertesting.Utils.DataLoadState
import com.elemental.exoplayertesting.data.Lesson
import com.elemental.exoplayertesting.data.Lessons
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class LessonRepositoryImpl(val context: Context) : LessonRepository {
    override fun cancelJob() {
        mJob.cancel()
    }

    override fun getlessons(): LiveData<List<Lesson>> {
        dataLoadState.postValue(DataLoadState.LOADED)
        return lessons
    }

    private val api: MainService = MainService.invoke(ConnectivityInterceptorImpl(context))
    private val mJob: Job = Job()
    private val dataLoadState: MutableLiveData<DataLoadState> = MutableLiveData()
    private val lessons: MutableLiveData<List<Lesson>> = MutableLiveData()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default + mJob
    override fun getDataLoadState(): LiveData<DataLoadState> {
        return dataLoadState
    }

    override fun loadlessons() {
        dataLoadState.postValue(DataLoadState.LOADING)
        launch {
            try {
                val response=api.fetchLessons().await()
                Log.d("response",response.body()!!.toString())
                when {
                    response.isSuccessful ->  {
                        lessons.postValue(response.body()!!.lessons)
                        dataLoadState.postValue(DataLoadState.LOADED)
                    }
                }
            } catch (e: NoConnectivityException) {
                Log.e("MY_ERROR", "No Internet Connection But You can use offline")
                dataLoadState.postValue(DataLoadState.FAILED)
            } catch (e: Throwable) {
                Log.e("MY_ERROR", "I don't know! $e")
                dataLoadState.postValue(DataLoadState.FAILED)
            }
        }
        }

    }


