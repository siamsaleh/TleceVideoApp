package com.schooling.telecevideoapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.schooling.telecevideoapp.model.Video
import com.schooling.telecevideoapp.helper.ApiFetchListener
import com.schooling.telecevideoapp.restapi.ApiHelper
import com.schooling.telecevideoapp.room.VideoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    // Retrofit
    /*private var videoListLiveData = MutableLiveData<List<Video>>()*/
    private val statusMessageLiveData = MutableLiveData<String>()

    // Room
    private var repository = VideoRepository(application.applicationContext)
    private val videosLiveData = repository.getAllVideosLive()

    suspend fun isVideoTableEmpty(): Boolean {
        return repository.isVideoTableEmpty()
    }

    fun getAllVideosLive(): LiveData<List<Video>> {
        return videosLiveData
    }

    fun getStatusMessage(): LiveData<String> {
        return statusMessageLiveData
    }

    fun initRoomVideoList(){

        // Initialize video list in room
        viewModelScope.launch(Dispatchers.IO) {
            repository.initAllVideos()
        }
    }

    fun updateVideoList() {
        ApiHelper.getVideos(object : ApiFetchListener<List<Video>> {
            override fun onSuccess(responseData: List<Video>) {

                // Insert video list in room
                viewModelScope.launch(Dispatchers.IO) {
                    repository.insertVideos(responseData)
                    repository.initAllVideos()

                    statusMessageLiveData.postValue("success")
                }
            }

            override fun onError(errorMessage: String, responseCode: Int) {
                statusMessageLiveData.postValue("Error: $errorMessage, Code: $responseCode")
            }
        })
    }
}