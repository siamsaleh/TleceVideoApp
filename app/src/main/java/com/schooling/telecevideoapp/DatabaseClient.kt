package com.schooling.telecevideoapp

import android.content.Context
import androidx.room.Room
import com.schooling.telecevideoapp.room.VideoDatabase

object DatabaseClient {
    private var instance: VideoDatabase? = null

    fun getInstance(context: Context): VideoDatabase {
        if (instance == null) {
            synchronized(VideoDatabase::class) {
                instance = Room.databaseBuilder(
                    context,
                    VideoDatabase::class.java, "video_database"
                ).build()
            }
        }
        return instance!!
    }
}