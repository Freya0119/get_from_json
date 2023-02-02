package com.example.kotlinyoutube

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore.Video
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.*
import java.io.IOException

const val ERROR_MSG = "Error_Message"
const val CHECK_MSG = "Check_Message"
class MainActivity : AppCompatActivity() {
//    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        recyclerView = this.findViewById(R.id.main_recyclerView)

        fetchJSON()
    }

    private fun fetchJSON() {
        val url = "https://api.letsbuildthatapp.com/youtube/home_feed"
        val request = Request.Builder().url(url).get().build()

        val client = OkHttpClient().newBuilder().build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d(ERROR_MSG, "error: $e")
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                Log.d(CHECK_MSG, "Body in main: $body")

                val gson = GsonBuilder().create()
                val homeFeed = gson.fromJson(body, HomeFeed::class.java)

                val recyclerView = findViewById<RecyclerView>(R.id.main_recyclerView)
                runOnUiThread { recyclerView.adapter = MainAdapter(homeFeed) }
            }
        })
    }
}

class User(val id: Int, val name: String)
class HomeFeed(val user: User, val videos: List<Videos>) {
    class Videos(val id: Int, val name: String, val link: String, val imageUrl: String, val channel: Channel, val numberOfViews: Int) {
        class Channel(val name: String, val profileimageUrl: String, val numberOfSubscribers: Int)
    }
}