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

class CourseLessonAdapter(val courseDetails: Array<CourseLessonDetail>) : RecyclerView.Adapter<CourseLessonViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseLessonViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.course_lesson_row, parent, false)
        return CourseLessonViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CourseLessonViewHolder, position: Int) {
        val courseLessonImageView = holder.itemView.findViewById<ImageView>(R.id.course_detail_row_imageUrl_imageView)
        Picasso.with(holder.itemView.context).load(Uri.parse(courseDetails[position].imageUrl)).into(courseLessonImageView)
        holder.itemView.findViewById<TextView>(R.id.course_detail_row_name_textView).text = courseDetails[position].name
        holder.itemView.findViewById<TextView>(R.id.course_detail_row_duration_textView).text = courseDetails[position].duration
        holder.courseDetail = courseDetails[position]
    }

    override fun getItemCount(): Int {
        return courseDetails.size
    }
}

class CourseLessonViewHolder(itemView: View, var courseDetail: CourseLessonDetail? = null) : RecyclerView.ViewHolder(itemView) {
    companion object {
        const val COURSE_KEY_LINK = "COURSE_KEY_LINK"
    }

    init {
        itemView.setOnClickListener {
            val intent = Intent(itemView.context, CourseLessonDetailActivity::class.java)
            intent.putExtra(COURSE_KEY_LINK, courseDetail?.link)
            itemView.context.startActivity(intent)
        }
    }
}