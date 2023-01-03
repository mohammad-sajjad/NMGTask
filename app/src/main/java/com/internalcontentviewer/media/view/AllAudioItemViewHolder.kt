package com.internalcontentviewer.media.view

import android.net.Uri
import android.view.View
import android.view.ViewGroup
import com.internalcontentviewer.R
import com.internalcontentviewer.core.BaseViewHolder
import com.internalcontentviewer.core.Extensions.inflate
import com.internalcontentviewer.core.RecyclerViewListItem
import com.internalcontentviewer.media.model.AllAudioListItem
import kotlinx.android.synthetic.main.activity_all_audio_actiivty.view.*
import kotlinx.android.synthetic.main.all_audio_item_layout.view.*


/**
 * Created by Mohammad Sajjad on 03-01-2023.
 * Email mohammadsajjad679@gmail.com
 */

class AllAudioItemViewHolder(parent: ViewGroup, private val listener: AudioItemActionListener): BaseViewHolder(parent.inflate(R.layout.all_audio_item_layout)) {
private var selected = -1
init {
    itemView.audio_action_img.setOnClickListener {
        selected = adapterPosition
    }
}

    override fun bindView(item: RecyclerViewListItem) {
        item as AllAudioListItem
        itemView.audio_name_tv.text = item.name

        itemView.audio_action_img.setOnClickListener {
            itemView.audio_stop_action_img.visibility = View.VISIBLE
            itemView.audio_action_img.visibility = View.INVISIBLE
            listener.onAudioPlay(item.uri)
        }
        itemView.audio_stop_action_img.setOnClickListener {
            itemView.audio_stop_action_img.visibility = View.INVISIBLE
            itemView.audio_action_img.visibility = View.VISIBLE
            listener.stopAudio()
        }
    }

    interface AudioItemActionListener {
        fun onAudioPlay(uri: Uri)
        fun stopAudio()
    }
}