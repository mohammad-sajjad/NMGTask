package com.internalcontentviewer.contactsData.model

import com.internalcontentviewer.constants.CardConstants
import com.internalcontentviewer.core.RecyclerViewListItem


/**
 * Created by Mohammad Sajjad on 30-12-2022.
 * Email mohammadsajjad679@gmail.com
 */

data class ContactListItems (
    val id: String,
    val name: String,
    val number: String
): RecyclerViewListItem {
    override fun getViewType() = CardConstants.CONTACTS_ITEM_ADAPTER

    override fun getUnique() = this
}