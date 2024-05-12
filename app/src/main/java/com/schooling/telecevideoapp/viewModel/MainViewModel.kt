package com.schooling.telecevideoapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.schooling.telecevideoapp.model.Video
import com.schooling.telecevideoapp.helper.ApiFetchListener
import com.schooling.telecevideoapp.restapi.ApiHelper

class MainViewModel() : ViewModel() { //private val repository: VideoRepository


    private val videoListLiveData = MutableLiveData<List<Video>>()
    private val statusMessageLiveData = MutableLiveData<String>()

    fun getVideoList() : MutableLiveData<List<Video>> {
        return videoListLiveData
    }

    fun getStatusMessage(): LiveData<String> {
        return statusMessageLiveData
    }

    fun loadVideoList(){
        ApiHelper.getVideos(object : ApiFetchListener<List<Video>> {
            override fun onSuccess(responseData: List<Video>) {
                videoListLiveData.postValue(responseData)
                /*statusMessageLiveData.postValue(responseData.toString())*/ //TODO
            }
            override fun onError(errorMessage: String, responseCode: Int) {
                statusMessageLiveData.postValue("Error: $errorMessage, Code: $responseCode")
            }
        })
    }

    /*val videosLiveData = repository.getAllVideosFromDb()*/

    /*fun fetchVideos() {
        *//*viewModelScope.launch {
            repository.getVideos()
        }*//*
    }*/
}