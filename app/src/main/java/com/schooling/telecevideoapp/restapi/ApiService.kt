package com.schooling.telecevideoapp.restapi

import com.schooling.telecevideoapp.model.Video
import retrofit2.Call
import retrofit2.http.GET

interface  ApiService {

    @GET("videos.json")
    fun getVideos(): Call<List<Video>>
}