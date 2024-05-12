package com.schooling.telecevideoapp.helper

interface ApiFetchListener<T> {
    fun onSuccess(responseData: T)
    fun onError(errorMessage: String, responseCode: Int)
}