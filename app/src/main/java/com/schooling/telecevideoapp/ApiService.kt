package com.schooling.telecevideoapp

import com.schooling.telecevideoapp.model.Video
import retrofit2.http.GET

interface ApiService {
    @GET("https://gist.githubusercontent.com/poudyalanil/ca84582cbeb4fc123a13290a586da925/raw/1427bd0bcd0cd323b35ad79cf3b493dddf6216b/videos.json")
    suspend fun getVideos(): List<Video>
}