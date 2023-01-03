package com.internalcontentviewer.core

import android.view.ViewGroup


/**
 * Created by Mohammad Sajjad on 30-12-2022.
 * Email mohammadsajjad679@gmail.com
 */

interface DelegateInterface {
    fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder
    fun onBindViewHolder(holder: BaseViewHolder, item: RecyclerViewListItem)
}