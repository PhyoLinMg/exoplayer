package com.elemental.exoplayertesting.Adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.elemental.exoplayertesting.R
import com.elemental.exoplayertesting.Utils.inflate
import com.elemental.exoplayertesting.data.Video

import kotlinx.android.synthetic.main.home_card.view.*

class HomeAdapter(private val videos:List<Video>, val listener: OnItemClickedListener):RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(parent.inflate(R.layout.home_card))
    }

    override fun getItemCount(): Int =videos.size

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val video=videos[position]
        holder.bind(video,listener)
    }
    interface OnItemClickedListener {
        fun onItemClicked(video: Video)
    }
    inner class HomeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        private val videoName=itemView.name!!
        fun bind(video:Video,listener: OnItemClickedListener){
            itemView.setOnClickListener{
                listener.onItemClicked(video)
            }
            videoName.text=video.publicId
        }


    }
}