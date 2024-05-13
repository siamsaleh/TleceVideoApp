package com.schooling.telecevideoapp.room

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.schooling.telecevideoapp.model.Video
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

    suspend fun initAllVideos() {
        withContext(Dispatchers.IO) {
            allVideosLive.postValue(videoDao.getAllVideos())
        }
    }

    suspend fun isVideoTableEmpty(): Boolean {
        val count = videoDao.getVideoCount()
        return count == 0
    }
}