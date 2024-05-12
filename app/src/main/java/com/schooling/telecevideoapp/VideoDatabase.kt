package com.schooling.telecevideoapp

import androidx.room.Database
import androidx.room.RoomDatabase
import com.schooling.telecevideoapp.model.Video

@Database(entities = [Video::class], version = 1)
abstract class VideoDatabase : RoomDatabase() {
    abstract fun videoDao(): VideoDao
}