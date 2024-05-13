package com.schooling.telecevideoapp.adapter

import android.content.Context
import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.OvershootInterpolator
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import at.blogc.android.views.ExpandableTextView
import com.bumptech.glide.RequestManager
import com.schooling.telecevideoapp.R
import com.schooling.telecevideoapp.model.Video

class VideoAdapter(
    private var context: Context,
    private var requestManager: RequestManager,
    private var videoList: List<Video>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.item_post_video, parent, false)
        return VideoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return videoList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val videoViewHolder = holder as VideoViewHolder
        videoOnBind(videoViewHolder, videoList[position], position)
    }

    private fun videoOnBind(holder: VideoViewHolder, item: Video, position: Int) {
        requestManager
            .load(item.thumbnailUrl)
            .into(holder.thumbnailView)

        setDescription(holder.description, item.description)

        holder.titleText.text = item.title


    }

    private fun setDescription(descriptionTV: ExpandableTextView, description: String) {

        descriptionTV.text = description
        if (description.length > 100) {
            var text = description;
            text = text.substring(0, 100) + "...";
            descriptionTV.text =
                Html.fromHtml(
                    "$text<font color='gray'> <u>see more</u></font>",
                    HtmlCompat.FROM_HTML_MODE_COMPACT
                )
        }

        descriptionTV.expandInterpolator = OvershootInterpolator()

        var isExpand = false
        descriptionTV.setAnimationDuration(1000L)

        descriptionTV.setOnClickListener {
            if (!isExpand) {
                descriptionTV.maxLines = 2
                descriptionTV.text = description
                descriptionTV.expand()
                descriptionTV.maxLines = 1000
                isExpand = true
            }
        }
    }
}