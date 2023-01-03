package com.internalcontentviewer.contactsData.view

import android.view.ViewGroup
import com.internalcontentviewer.core.BaseDelegate


/**
 * Created by Mohammad Sajjad on 30-12-2022.
 * Email mohammadsajjad679@gmail.com
 */

class ContactListItemDelegate: BaseDelegate() {

    override fun onCreateViewHolder(parent: ViewGroup) = ContactListItemViewHolder(parent)
}