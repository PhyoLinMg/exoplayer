package com.elemental.exoplayertesting.repository.HomeRepository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.elemental.atantat.network.ConnectivityInterceptorImpl
import com.elemental.atantat.network.NoConnectivityException
import com.elemental.atantat.network.services.MainService
import com.elemental.exoplayertesting.Utils.DataLoadState
import com.elemental.exoplayertesting.data.Video
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class HomeRepositoryImpl(context: Context) :
    HomeRepository {
    private val mJob: Job = Job()
    private val dataLoadState: MutableLiveData<DataLoadState> = MutableLiveData()
    private val models: MutableLiveData<List<Video>> = MutableLiveData()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default + mJob

    private val api: MainService = MainService.invoke(ConnectivityInterceptorImpl(context))

    override fun getDataLoadState(): LiveData<DataLoadState> {
        return dataLoadState
    }

    override fun loadApi() {
        dataLoadState.postValue(DataLoadState.LOADING)
        launch {
            try {
                val response=api.fetchVideos().await()
                Log.d("response",response.body().toString())
                when {
                    response.isSuccessful ->  {
                       models.postValue(response.body()!!.videos)

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

    override fun getApi(): LiveData<List<Video>> {
        dataLoadState.postValue(DataLoadState.LOADED)
        return models
    }

    override fun cancelJob() {
        mJob.cancel()
    }
}