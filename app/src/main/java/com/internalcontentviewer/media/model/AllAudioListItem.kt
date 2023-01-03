package com.internalcontentviewer.media.model

import android.net.Uri
import com.internalcontentviewer.constants.CardConstants
import com.internalcontentviewer.core.RecyclerViewListItem


/**
 * Created by Mohammad Sajjad on 03-01-2023.
 * Email mohammadsajjad679@gmail.com
 */

data class AllAudioListItem(
    val id: String,
    val name: String,
    val size: String,
    val path: String,
    val uri: Uri,
    val duration:String
): RecyclerViewListItem {
    override fun getViewType() = CardConstants.ALL_AUDIO_ITEM_ADAPTER

    override fun getUnique() = this
}