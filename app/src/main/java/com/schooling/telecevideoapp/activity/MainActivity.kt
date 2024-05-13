package com.schooling.telecevideoapp.activity

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import androidx.work.WorkRequest
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.schooling.telecevideoapp.adapter.VideoAdapter
import com.schooling.telecevideoapp.databinding.ActivityMainBinding
import com.schooling.telecevideoapp.service.AlarmManagerBroadcast
import com.schooling.telecevideoapp.service.UploadWorker
import com.schooling.telecevideoapp.utility.CommonUtils
import com.schooling.telecevideoapp.viewModel.MainViewModel
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    private lateinit var videoAdapter: VideoAdapter

    private lateinit var uploadWorkRequest: WorkRequest

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        setContentView(binding.root)

        observeLiveData()

        // Video Table
        initializeDatabase()

        // Check Wifi available or not. Then Update DB
        workManager()

        // FCM Notification
        fcmNotification()
    }

    private fun workManager() {
        uploadWorkRequest = OneTimeWorkRequestBuilder<UploadWorker>().build()

        WorkManager
            .getInstance(this)
            .enqueue(uploadWorkRequest)

        Log.d("WORK_TAG", "workManager: Executed")
    }

    private fun fcmNotification() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("FCM_TAG", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result

            // See Token
            Log.d("FCM_TAG", token)
        })
    }

    private fun initializeDatabase() {

        // Check if the video table is empty
        viewModel.viewModelScope.launch {
            val isTableEmpty = viewModel.isVideoTableEmpty()
            if (isTableEmpty) {
                // Table is empty
                updateLiveData()/*Log.d("DATABASE_TAG", "onCreate: false")*/
            } else {
                // Table has data
                loadLiveData()/*Log.d("DATABASE_TAG", "onCreate: true")*/
            }
        }
    }

    private fun updateLiveData() {
        viewModel.updateVideoList()
    }

    private fun loadLiveData() {
        viewModel.initRoomVideoList()
    }

    private fun observeLiveData() {

        // Get video data from room
        viewModel.getAllVideosLive().observe(this) {

            // set video adapter
            videoAdapter = VideoAdapter(this, CommonUtils().initGlide(this), it)
            binding.videoRecyclerview.adapter =
                videoAdapter/*Log.d("VIDEOS", "observeLiveData: $it")*/

            // Visibility
            binding.loading.visibility = View.GONE
            binding.videoRecyclerview.visibility = View.VISIBLE
        }
    }
}