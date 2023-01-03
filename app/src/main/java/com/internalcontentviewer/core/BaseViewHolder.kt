package com.internalcontentviewer.core

import android.view.View
import androidx.recyclerview.widget.RecyclerView


/**
 * Created by Mohammad Sajjad on 30-12-2022.
 * Email mohammadsajjad679@gmail.com
 */

abstract class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    abstract fun bindView(item: RecyclerViewListItem)

}