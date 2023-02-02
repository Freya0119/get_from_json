package com.example.kotlinyoutube

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class MainAdapter(private val homeFeed: HomeFeed) : RecyclerView.Adapter<MainViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.main_row, parent, false)
        return MainViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val videosImageView = holder.itemView.findViewById<ImageView>(R.id.main_row_videos_imageUrl_imageView)
        Picasso.with(holder.itemView.context).load(homeFeed.videos[position].imageUrl).into(videosImageView)
        val profileImageView = holder.itemView.findViewById<ImageView>(R.id.main_row_channel_profileImageUrl_imageView)
        Picasso.with(holder.itemView.context).load(homeFeed.videos[position].channel.profileimageUrl).into(profileImageView)
        holder.itemView.findViewById<TextView>(R.id.main_row_name_textView).text = homeFeed.videos[position].name
        holder.itemView.findViewById<TextView>(R.id.main_row_channel_name_textView).text = homeFeed.videos[position].channel.name
        holder.itemView.findViewById<TextView>(R.id.main_row_channel_numberOfViews_textView).text = "${homeFeed.videos[position].numberOfViews} views"
        holder.video = homeFeed.videos[position]
    }

    override fun getItemCount(): Int {
        return homeFeed.videos.size
    }
}

class MainViewHolder(itemView: View, var video: HomeFeed.Videos? = null) : RecyclerView.ViewHolder(itemView) {
    companion object {
        const val KEY_NAME = "ACTION_BAR"
        const val KEY_ID = "VIDEO_ID"
    }

    init {
        itemView.setOnClickListener {
            val intent = Intent(itemView.context, CourseLessonActivity::class.java)
            intent.putExtra(KEY_NAME, "${video?.name}")
            intent.putExtra(KEY_ID, "${video?.id}")
            Log.d("DDD", "${video?.id}")
            itemView.context.startActivity(intent)
        }
    }
}