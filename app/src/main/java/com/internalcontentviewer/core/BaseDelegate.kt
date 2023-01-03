package com.internalcontentviewer.core


/**
 * Created by Mohammad Sajjad on 30-12-2022.
 * Email mohammadsajjad679@gmail.com
 */

abstract class BaseDelegate : DelegateInterface {

    override fun onBindViewHolder(holder: BaseViewHolder, item: RecyclerViewListItem) {
        holder.bindView(item)
    }
}