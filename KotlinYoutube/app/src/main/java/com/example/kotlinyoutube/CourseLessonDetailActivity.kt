package com.example.kotlinyoutube

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.widget.TextView
import android.widget.Toast
import com.example.kotlinyoutube.CourseLessonViewHolder.Companion.COURSE_KEY_LINK

class CourseLessonDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_lesson_detil)

        val link = intent.getStringExtra(COURSE_KEY_LINK)
        if (link != null) {
            this.findViewById<WebView>(R.id.lesson_detail_webView).loadUrl(link)
        } else {
            Toast.makeText(this, "連結不存在", Toast.LENGTH_SHORT).show()
        }
    }
}