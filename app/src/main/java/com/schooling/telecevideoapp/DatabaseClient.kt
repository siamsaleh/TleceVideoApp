package com.schooling.telecevideoapp

import android.content.Context
import androidx.room.Room

object DatabaseClient {
    private var instance: VideoDatabase? = null

    fun getInstance(context: Context): VideoDatabase {
        if (instance == null) {
            synchronized(VideoDatabase::class) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    VideoDatabase::class.java, "video_database"
                ).build()
            }
        }
        return instance!!
    }
}