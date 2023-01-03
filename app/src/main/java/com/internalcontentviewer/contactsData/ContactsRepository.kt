package com.internaldatapicker.contactsData

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.Context
import android.database.Cursor
import android.provider.ContactsContract
import android.util.Log
import com.internalcontentviewer.contactsData.model.ContactListItems


/**
 * Created by Mohammad Sajjad on 30-12-2022.
 * Email mohammadsajjad679@gmail.com
 */

class ContactsRepository {


    @SuppressLint("Range")
    fun getContact(context: Context): List<ContactListItems> {
        val contactsInfoList = mutableListOf<ContactListItems>()
        val contentResolver: ContentResolver = context.contentResolver
        var contactId: String? = null
        var displayName: String? = null
        val cursor: Cursor? = context.contentResolver.query(
            ContactsContract.Contacts.CONTENT_URI, null, null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC")
        if (cursor?.count != null) {
            if (cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val hasPhoneNumber: Int =
                        cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))
                            .toInt()
                    if (hasPhoneNumber > 0) {
                        contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID))
                        displayName = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))

                        val phoneCursor: Cursor? = context.contentResolver.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", arrayOf<String?>(contactId), null)

                        if (phoneCursor != null) {
                            if (phoneCursor.moveToNext()) {
                                val phoneNumber: String = phoneCursor.getString(
                                    phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                                )
                                val contactsInfo = ContactListItems(id = contactId, name = displayName, number = phoneNumber)
                                contactsInfoList.add(contactsInfo)
                                Log.e("Contacts", contactsInfoList.toString())
                            }
                        }
                        phoneCursor?.close()
                    }
                }
            }
            cursor.close()
        }
        return contactsInfoList
    }

}