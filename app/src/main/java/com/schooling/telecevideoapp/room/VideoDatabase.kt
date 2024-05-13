package com.schooling.telecevideoapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.schooling.telecevideoapp.model.Video

@Database(entities = [Video::class], version = 1, exportSchema = false)
abstract class VideoDatabase : RoomDatabase() {

    abstract fun videoDao(): VideoDao

    companion object {
        @Volatile
        private var INSTANCE: VideoDatabase? = null

        fun getInstance(context: Context): VideoDatabase {
            return INSTANCE ?: synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    VideoDatabase::class.java,
                    "video_database"
                )
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}