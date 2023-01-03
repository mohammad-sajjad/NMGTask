package com.internalcontentviewer.core


/**
 * Created by Mohammad Sajjad on 30-12-2022.
 * Email mohammadsajjad679@gmail.com
 */

interface RecyclerViewListItem {
    fun getViewType(): Int
    fun getUnique(): Any
}