package com.schooling.telecevideoapp.adapter

import android.view.View
import android.webkit.WebView
import android.widget.ImageView
import android.widget.TextView
import android.widget.VideoView
import androidx.recyclerview.widget.RecyclerView
import at.blogc.android.views.ExpandableTextView
import com.google.android.exoplayer2.ui.PlayerView
import com.schooling.telecevideoapp.R

class VideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val titleText: TextView = itemView.findViewById(R.id.title_txt)
    val playPauseBtn: ImageView = itemView.findViewById(R.id.play_pause)

    val videoView: VideoView = itemView.findViewById(R.id.videoView)
    val playerView: PlayerView = itemView.findViewById(R.id.player_view)
    val thumbnailView: ImageView = itemView.findViewById(R.id.thumbnail_image)

    val description: ExpandableTextView = itemView.findViewById(R.id.description_txt)
}