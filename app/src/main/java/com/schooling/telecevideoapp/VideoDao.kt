package com.schooling.telecevideoapp

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.schooling.telecevideoapp.model.Video

@Dao
interface VideoDao {
    /*@Query("SELECT * FROM videos")
    fun getAllVideos(): List<Video>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(videos: List<Video>)*/
    @Insert
    fun insertAll(videos: List<Video>)

    @Query("SELECT * FROM videos")
    fun getAllVideos(): List<Video>
}