package com.elemental.atantat.network.services


import com.elemental.atantat.network.ConnectivityInterceptor
import com.elemental.exoplayertesting.Utils.Const
import com.elemental.exoplayertesting.data.Lessons
import com.elemental.exoplayertesting.data.Videos
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


interface MainService{

    @GET("demo/video/list/samples.json")
    fun fetchVideos(): Deferred<Response<Videos>>

    @GET("lessonapi")
    fun fetchLessons():Deferred<Response<Lessons>>

    companion object {
       operator fun invoke(
           connectivityInterceptor: ConnectivityInterceptor
       ) : MainService {


           val requestInterceptor = Interceptor { chain ->
               val request = chain.request()
                   .newBuilder()
                   .build()

               return@Interceptor chain.proceed(request)
           }

           val okHttpClient = OkHttpClient.Builder()
              .addInterceptor(requestInterceptor)
               .addInterceptor(connectivityInterceptor)
               .build()


           return Retrofit.Builder()
               .baseUrl(Const.LOCAL_API_END)
               .addConverterFactory(GsonConverterFactory.create())
               .addCallAdapterFactory(CoroutineCallAdapterFactory())
               .client(okHttpClient)
               .build()
               .create(MainService::class.java)
       }
    }
}