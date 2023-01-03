package com.internalcontentviewer.media

import com.internalcontentviewer.constants.CardConstants
import com.internalcontentviewer.core.BaseAdapter
import com.internalcontentviewer.media.view.VideoItemDelegate


/**
 * Created by Mohammad Sajjad on 03-01-2023.
 * Email mohammadsajjad679@gmail.com
 */

class MediaPlayerAdapter: BaseAdapter() {

    init {
        initDelegates()
    }

    override fun initDelegates() {
        delegates[CardConstants.ALL_MEDIA_ITEM_ADAPTER] = VideoItemDelegate()
    }
}