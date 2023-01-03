package com.internalcontentviewer.adapter

import com.internalcontentviewer.constants.CardConstants
import com.internalcontentviewer.core.BaseAdapter
import com.internaldatapicker.media.view.MediaItemViewDelegate


/**
 * Created by Mohammad Sajjad on 31-12-2022.
 * Email mohammadsajjad679@gmail.com
 */

class AllImageAdapter : BaseAdapter() {

    init {
        initDelegates()
    }

    override fun initDelegates() {
        delegates[CardConstants.IMAGE_ITEM_ADAPTER] = MediaItemViewDelegate()
    }
}