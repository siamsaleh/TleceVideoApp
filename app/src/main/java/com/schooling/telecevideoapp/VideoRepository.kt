package com.schooling.telecevideoapp

class VideoRepository(private val apiService: ApiService, private val videoDao: VideoDao) {
    /*fun getVideos(): List<Video> {
        val videos = apiService.getVideos()
        videoDao.insertAll(videos)
        return videos
    }

    fun getAllVideosFromDb(): List<Video> {
        return videoDao.getAllVideos()
    }*/
}