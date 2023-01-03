package com.internalcontentviewer.contactsData.view

import android.view.ViewGroup
import com.internalcontentviewer.R
import com.internalcontentviewer.contactsData.model.ContactListItems
import com.internalcontentviewer.core.BaseViewHolder
import com.internalcontentviewer.core.Extensions.inflate
import com.internalcontentviewer.core.RecyclerViewListItem
import com.internalcontentviewer.widget.ColorGenerator
import com.internalcontentviewer.widget.InitialLetterTextDrawable
import kotlinx.android.synthetic.main.contact_list_item_layout.view.*


/**
 * Created by Mohammad Sajjad on 30-12-2022.
 * Email mohammadsajjad679@gmail.com
 */

class ContactListItemViewHolder(parent: ViewGroup): BaseViewHolder(parent.inflate(R.layout.contact_list_item_layout)) {
    override fun bindView(item: RecyclerViewListItem) {
        item as ContactListItems

        val nameDrawable = ColorGenerator.DEFAULT?.let { InitialLetterTextDrawable.builder().buildRound(item.name[0].toString(), it.getRandomColor()) }

        itemView.initialsCircleImageView.setImageDrawable(nameDrawable)
        itemView.contact_list_item_contact_name_tv.text = item.name
        itemView.contact_list_item_contact_number_tv.text = item.number

    }
}