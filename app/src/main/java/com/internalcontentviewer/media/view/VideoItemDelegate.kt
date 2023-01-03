package com.internalcontentviewer.media.view

import android.view.ViewGroup
import com.internalcontentviewer.core.BaseDelegate


/**
 * Created by Mohammad Sajjad on 03-01-2023.
 * Email mohammadsajjad679@gmail.com
 */

class VideoItemDelegate: BaseDelegate() {
    override fun onCreateViewHolder(parent: ViewGroup) = VideoItemViewHolder(parent)
}