package com.schooling.telecevideoapp.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.schooling.telecevideoapp.model.Video

@Dao
interface VideoDao {
    @Query("SELECT * FROM videos")
    suspend fun getAllVideos(): List<Video>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(videos: List<Video>)

    @Query("SELECT COUNT(*) FROM videos")
    suspend fun getVideoCount(): Int

    /*@Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVideo(videos: Video)*/
}