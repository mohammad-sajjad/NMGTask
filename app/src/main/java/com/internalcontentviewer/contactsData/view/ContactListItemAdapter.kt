package com.internalcontentviewer.contactsData.view

import com.internalcontentviewer.constants.CardConstants
import com.internalcontentviewer.core.BaseAdapter


/**
 * Created by Mohammad Sajjad on 30-12-2022.
 * Email mohammadsajjad679@gmail.com
 */

class ContactListItemAdapter: BaseAdapter() {

    init {
        initDelegates()
    }

    override fun initDelegates() {
        delegates[CardConstants.CONTACTS_ITEM_ADAPTER] = ContactListItemDelegate()
    }
}