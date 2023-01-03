package com.internalcontentviewer.media.model

import android.net.Uri
import com.internalcontentviewer.constants.CardConstants
import com.internalcontentviewer.core.RecyclerViewListItem


/**
 * Created by Mohammad Sajjad on 30-12-2022.
 * Email mohammadsajjad679@gmail.com
 */

data class MediaListItems (
    val id: String,
    val name: String,
    val uri: Uri
        ): RecyclerViewListItem {
    override fun getViewType() = CardConstants.IMAGE_ITEM_ADAPTER

    override fun getUnique() = this
}