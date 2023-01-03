package com.internalcontentviewer.media.view

import android.view.ViewGroup
import com.internalcontentviewer.core.BaseDelegate
import com.internalcontentviewer.core.BaseViewHolder


/**
 * Created by Mohammad Sajjad on 03-01-2023.
 * Email mohammadsajjad679@gmail.com
 */

class AllAudioItemDelegate(private val listener: AllAudioItemViewHolder.AudioItemActionListener): BaseDelegate() {
    override fun onCreateViewHolder(parent: ViewGroup) = AllAudioItemViewHolder(parent, listener)
}