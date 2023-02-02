package com.example.kotlinyoutube

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinyoutube.MainViewHolder.Companion.KEY_ID
import com.example.kotlinyoutube.MainViewHolder.Companion.KEY_NAME
import com.google.gson.GsonBuilder
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class CourseLessonActivity : AppCompatActivity() {
    private lateinit var main_recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title = intent.getStringExtra(KEY_NAME)

        main_recyclerView = this.findViewById(R.id.main_recyclerView)

        fetchJSON()
    }

    private fun fetchJSON() {
        val url = "https://api.letsbuildthatapp.com/youtube/course_detail?id=" + intent.getStringExtra(KEY_ID)

        val request = Request.Builder().url(url).get().build()
        val client = OkHttpClient.Builder().build()
        client.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d(ERROR_MSG, "$e")
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                Log.d(CHECK_MSG, "Body in detail: $body")

                val json = GsonBuilder().create()
                val courseDetails = json.fromJson(body, Array<CourseLessonDetail>::class.java)

                runOnUiThread { main_recyclerView.adapter = CourseLessonAdapter(courseDetails) }
            }
        })
    }
}

class CourseLessonDetail(val name: String, val number: Int, val duration: String, val imageUrl: String, val link: String)