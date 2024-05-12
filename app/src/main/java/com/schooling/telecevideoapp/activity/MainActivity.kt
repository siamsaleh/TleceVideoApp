package com.schooling.telecevideoapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.schooling.telecevideoapp.databinding.ActivityMainBinding
import com.schooling.telecevideoapp.viewModel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        setContentView(binding.root)

        loadLiveData()
        observeLiveData()


        /*val json =
        val videos: List<Video> = Gson().fromJson(json, Array<Video>::class.java).toList()

        val db = Room.databaseBuilder(applicationContext, VideoDatabase::class.java, "app-database").build()
        db.videoDao().insertAll(videos)

        val videoList = db.videoDao().getAllVideos()
        Log.d("VIDEO_DATA", "onCreate: $videoList")*/


        /*val apiService = Retrofit.Builder()
            .baseUrl("https://your_base_url/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)

        val database = Room.databaseBuilder(applicationContext, VideoDatabase::class.java, "video_database")
            .build()

        val repository = VideoRepository(apiService, database.videoDao())



        viewModel.videosLiveData.observe(this, Observer { videos ->
            // Update UI with videos
        })

        viewModel.fetchVideos()*/
    }

    private fun loadLiveData() {
        viewModel.loadVideoList()
    }

    private fun observeLiveData() {
        viewModel.getVideoList().observe(this){
            Log.d("VIDEOS", "observeLiveData: $it")
        }
    }
}