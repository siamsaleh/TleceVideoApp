package com.schooling.telecevideoapp.activity

import android.Manifest
import android.Manifest.permission.POST_NOTIFICATIONS
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.schooling.telecevideoapp.R
import com.schooling.telecevideoapp.adapter.VideoAdapter
import com.schooling.telecevideoapp.databinding.ActivityMainBinding
import com.schooling.telecevideoapp.utility.CommonUtils
import com.schooling.telecevideoapp.viewModel.MainViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    private lateinit var videoAdapter: VideoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        setContentView(binding.root)

        observeLiveData()

        // Video Table
        initializeDatabase()

        // FCM Notification
        fcmNotification()
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
                updateLiveData()
                /*Log.d("DATABASE_TAG", "onCreate: false")*/
            } else {
                // Table has data
                loadLiveData()
                /*Log.d("DATABASE_TAG", "onCreate: true")*/
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
        viewModel.getAllVideosLive().observe(this){

            // set video adapter
            videoAdapter = VideoAdapter(this, CommonUtils().initGlide(this), it)
            binding.videoRecyclerview.adapter = videoAdapter
            /*Log.d("VIDEOS", "observeLiveData: $it")*/

            // Visibility
            binding.loading.visibility = View.GONE
            binding.videoRecyclerview.visibility = View.VISIBLE
        }
    }
}