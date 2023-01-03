package com.internalcontentviewer.media

import com.internalcontentviewer.constants.CardConstants
import com.internalcontentviewer.core.BaseAdapter
import com.internalcontentviewer.media.view.AllAudioItemDelegate
import com.internalcontentviewer.media.view.AllAudioItemViewHolder


/**
 * Created by Mohammad Sajjad on 03-01-2023.
 * Email mohammadsajjad679@gmail.com
 */

class AllAudioAdapter(private val listener: AllAudioItemViewHolder.AudioItemActionListener): BaseAdapter() {
    init {
        initDelegates()
    }
    override fun initDelegates() {
        delegates[CardConstants.ALL_AUDIO_ITEM_ADAPTER] = AllAudioItemDelegate(listener)
    }
}