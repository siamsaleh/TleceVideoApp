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
    }

    private fun loadLiveData() {
        viewModel.loadVideoList()
    }

    private fun observeLiveData() {
        /*viewModel.getVideoList().observe(this){
            Log.d("VIDEOS", "observeLiveData: $it")
        }*/

        viewModel.getAllVideosLive().observe(this){
            Log.d("VIDEOS", "observeLiveData: $it")
        }
    }
}