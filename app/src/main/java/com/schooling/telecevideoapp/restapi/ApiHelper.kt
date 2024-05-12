package com.schooling.telecevideoapp.restapi

import com.schooling.telecevideoapp.model.Video
import com.schooling.telecevideoapp.helper.ApiFetchListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiHelper {
    companion object {

        fun getVideos(
            apiFetchListener: ApiFetchListener<List<Video>>
        ) {
            val apiService = ApiClient.getInstance().create(ApiService::class.java)

            apiService.getVideos().enqueue(object : Callback<List<Video>> {
                override fun onResponse(
                    call: Call<List<Video>>,
                    response: Response<List<Video>>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let { apiFetchListener.onSuccess(it) }
                    } else
                        apiFetchListener.onError(response.message(), response.code())
                }

                override fun onFailure(call: Call<List<Video>>, t: Throwable) {
                    t.message?.let { apiFetchListener.onError(it, 0) }
                }
            })
        }
    }
}