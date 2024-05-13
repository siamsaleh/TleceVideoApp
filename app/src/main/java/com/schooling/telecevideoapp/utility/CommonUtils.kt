package com.schooling.telecevideoapp.utility

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.schooling.telecevideoapp.R

class CommonUtils {

    fun initGlide(context: Context): RequestManager {
        val options: RequestOptions = RequestOptions()
            .placeholder(R.drawable.white_background)
            .error(R.drawable.white_background)
        return Glide.with(context)
            .setDefaultRequestOptions(options)
    }
}