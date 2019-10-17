package com.elemental.exoplayertesting.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.elemental.exoplayertesting.Adapter.HomeAdapter
import com.elemental.exoplayertesting.R
import com.elemental.exoplayertesting.Utils.Const
import com.elemental.exoplayertesting.Utils.DataLoadState
import com.elemental.exoplayertesting.ViewModel.Home.HomeViewModel
import com.elemental.exoplayertesting.ViewModel.Home.HomeViewModelFactory
import com.elemental.exoplayertesting.ViewModel.Lesson.LessonViewModel
import com.elemental.exoplayertesting.ViewModel.Lesson.LessonViewModelFactory
import com.elemental.exoplayertesting.data.Lesson
import com.elemental.exoplayertesting.data.Video
import com.elemental.exoplayertesting.repository.HomeRepository.HomeRepositoryImpl
import com.elemental.exoplayertesting.repository.LessonRepository.LessonRepositoryImpl
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(), HomeAdapter.OnItemClickedListener{
    private lateinit var viewModel: HomeViewModel
    private lateinit var lessonViewModel: LessonViewModel

    private lateinit var homeAdapter: HomeAdapter
    private val videos: MutableList<Video> = ArrayList()
    private val lessons:MutableList<Lesson> =ArrayList()

    override fun onItemClicked(video: Video) {
        val intent=Intent(this,MainActivity::class.java)
        intent.putExtra("url", Const.createVideoUrl(video))
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        homeAdapter= HomeAdapter(videos,this)

        progress_circular.visibility= View.VISIBLE
        viewModel = ViewModelProviders.of(this,
            HomeViewModelFactory(
                HomeRepositoryImpl(this)
            )
        ).get(HomeViewModel::class.java)

        lessonViewModel = ViewModelProviders.of(this,
            LessonViewModelFactory(
                LessonRepositoryImpl(this)
            )
        ).get(LessonViewModel::class.java)
        viewModel.loadApi()
        lessonViewModel.loadLessons()

        lessonViewModel.getLessons().observe(this, Observer {
            lessons.addAll(it)
            Log.d("lessons",lessons.toString())
        })

        home_recycler.apply {
            layoutManager=LinearLayoutManager(applicationContext)
            setHasFixedSize(true)
            adapter=homeAdapter

        }

        viewModel.getApi().observe(this, Observer {
            videos.addAll(it)
            homeAdapter.notifyDataSetChanged()
        })

        viewModel.getLoadState().observe(this, Observer {
            when(it){
                DataLoadState.LOADING->{
                    progress_circular.visibility=View.VISIBLE
                }
                DataLoadState.LOADED->{
                    progress_circular.visibility=View.GONE
                }
                DataLoadState.FAILED->{
                    progress_circular.visibility=View.VISIBLE

                }

            }
        })


    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.cancelJob()
    }
}
