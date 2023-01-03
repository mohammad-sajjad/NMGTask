package com.internaldatapicker.media.view

import android.view.ViewGroup
import com.internalcontentviewer.core.BaseDelegate
import com.internalcontentviewer.media.view.MediaItemViewHolder


/**
 * Created by Mohammad Sajjad on 31-12-2022.
 * Email mohammadsajjad679@gmail.com
 */

class MediaItemViewDelegate: BaseDelegate() {
    override fun onCreateViewHolder(parent: ViewGroup) = MediaItemViewHolder(parent)
}