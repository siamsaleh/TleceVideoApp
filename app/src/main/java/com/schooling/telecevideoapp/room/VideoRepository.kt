package com.schooling.telecevideoapp.room

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.schooling.telecevideoapp.DatabaseClient
import com.schooling.telecevideoapp.model.Video
import com.schooling.telecevideoapp.room.VideoDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class VideoRepository(context: Context) {

    private val videoDao: VideoDao
    private val allVideosLive: MutableLiveData<List<Video>>

    init {
        val videoDatabase = VideoDatabase.getInstance(context)
        videoDao = videoDatabase.videoDao()
        allVideosLive = MutableLiveData()
    }

    fun getAllVideosLive(): LiveData<List<Video>> {
        return allVideosLive
    }

    suspend fun insertVideos(videos: List<Video>) {
        withContext(Dispatchers.IO) {
            videoDao.insertAll(videos)
        }
    }

    suspend fun getAllVideos() {
        withContext(Dispatchers.IO) { allVideosLive.postValue(videoDao.getAllVideos()) }
    }
}