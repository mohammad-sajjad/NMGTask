package com.internalcontentviewer.media.view

import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.internalcontentviewer.R
import com.internalcontentviewer.core.BaseViewHolder
import com.internalcontentviewer.core.Extensions.inflate
import com.internalcontentviewer.core.RecyclerViewListItem
import com.internaldatapicker.media.model.MediaPlayItems
import kotlinx.android.synthetic.main.media_item_view_holder_layout.view.*


/**
 * Created by Mohammad Sajjad on 03-01-2023.
 * Email mohammadsajjad679@gmail.com
 */

class VideoItemViewHolder(parent: ViewGroup) : BaseViewHolder(parent.inflate(R.layout.media_item_view_holder_layout)) {
    override fun bindView(item: RecyclerViewListItem) {
        item as MediaPlayItems

        Glide.with(itemView.context).load(item.uri).into(itemView.picture)
    }
}