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

        /*firebaseNotification()*/

        /*loadLiveData()*/
        observeLiveData()

        // Video Table
        initializeDatabase()


        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("FCM_TAG", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result

            // Log and toast
            /*val msg = getString(R.string.msg_token_fmt, token)*/
            Log.d("FCM_TAG", token)
            Toast.makeText(baseContext, "device reg token is ", Toast.LENGTH_SHORT).show()
        })
    }

    /*private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission(),
    ) { isGranted: Boolean ->
        if (isGranted) {
            // FCM SDK (and your app) can post notifications.
        } else {
            // TODO: Inform user that that your app will not show notifications.
        }
    }*/

    /*private fun askNotificationPermission() {
        // This is only necessary for API level >= 33 (TIRAMISU)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) ==
                PackageManager.PERMISSION_GRANTED
            ) {
                // FCM SDK (and your app) can post notifications.
            } else if (shouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS)) {
                // TODO: display an educational UI explaining to the user the features that will be enabled
                //       by them granting the POST_NOTIFICATION permission. This UI should provide the user
                //       "OK" and "No thanks" buttons. If the user selects "OK," directly request the permission.
                //       If the user selects "No thanks," allow the user to continue without notifications.
            } else {
                // Directly ask for the permission
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }*/

    /*private fun firebaseNotification() {
        notificationPermission()
        FirebaseMessaging.getInstance().subscribeToTopic("notification")
    }*/

    /*private fun notificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val permissionsNotification = ActivityCompat.checkSelfPermission(this, POST_NOTIFICATIONS)
            if (permissionsNotification != PackageManager.PERMISSION_GRANTED){
                val NOTIFICATION_PERMISSION = arrayOf(POST_NOTIFICATIONS)
                ActivityCompat.requestPermissions(this, NOTIFICATION_PERMISSION, 100)
            }
        }
    }*/

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