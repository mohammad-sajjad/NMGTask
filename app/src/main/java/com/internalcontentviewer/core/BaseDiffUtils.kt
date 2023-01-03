package com.internalcontentviewer.core

import androidx.recyclerview.widget.DiffUtil


/**
 * Created by Mohammad Sajjad on 30-12-2022.
 * Email mohammadsajjad679@gmail.com
 */

class BaseDiffUtils(
    private val oldItems: MutableList<RecyclerViewListItem>,
    private val newItems: MutableList<RecyclerViewListItem>
) :
    DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldItems[oldItemPosition]
        val newItem = newItems[newItemPosition]
        return oldItem.getViewType() == newItem.getViewType() && oldItem.getUnique() == newItem.getUnique()

    }

    override fun getOldListSize(): Int {
        return oldItems.size
    }

    override fun getNewListSize(): Int {
        return newItems.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldItems[oldItemPosition]
        val newItem = newItems[newItemPosition]
        return oldItem.getViewType() == newItem.getViewType() && oldItem.getUnique() == newItem.getUnique()
    }
}