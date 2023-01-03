package com.internaldatapicker.contactsData

import com.internalcontentviewer.core.BaseViewModel
import com.internalcontentviewer.core.DataFetchState
import com.internalcontentviewer.core.RecyclerViewListItem
import com.internalcontentviewer.core.StateMachine
import com.internalcontentviewer.error.StandardError


/**
 * Created by Mohammad Sajjad on 30-12-2022.
 * Email mohammadsajjad679@gmail.com
 */

class ContactListViewModel: BaseViewModel() {
    private val repository = ContactsRepository()
    val items = mutableListOf<RecyclerViewListItem>()

    fun getAllContacts(stateMachine: StateMachine) {

            val contacts = repository.getContact(appControllerContract.getProperContext())
            stateMachine.postValue(DataFetchState.Loading)
            if (contacts.isNullOrEmpty()) {
                contacts.map { items.add(it) }
                stateMachine.postValue(DataFetchState.Success())
            } else {
                stateMachine.postValue(DataFetchState.Error(StandardError("No data found")))
            }

        }
    }


//    @SuppressLint("Range")
//    private fun getPhoneContacts(): ArrayList<ContactListItems> {
//        val contactsList = ArrayList<ContactListItems>()
//        val contactsCursor = appControllerContract.getProperContext().contentResolver?.query(
//            ContactsContract.Contacts.CONTENT_URI,
//            null,
//            null,
//            null,
//            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC")
//        if (contactsCursor != null && contactsCursor.count > 0) {
//            val idIndex = contactsCursor.getColumnIndex(ContactsContract.Contacts._ID)
//            val nameIndex = contactsCursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)
//            while (contactsCursor.moveToNext()) {
//                val id = contactsCursor.getString(idIndex)
//                val name = contactsCursor.getString(nameIndex)
////                val phoneNumber: String = contactsCursor.getString(
////                                    contactsCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
////                                )
//                if (name != null) {
//                    contactsList.add(ContactListItems(id, name, ""))
//                }
//            }
//            contactsCursor.close()
//        }
//        return contactsList
//    }
//}


//    @SuppressLint("Range")
//    fun getContacts() {
//        val contentResolver: ContentResolver = appControllerContract.getProperContext().contentResolver
//        var contactId: String? = null
//        var displayName: String? = null
//        val cursor: Cursor? = appControllerContract.getProperContext().contentResolver.query(
//            ContactsContract.Contacts.CONTENT_URI, null, null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC")
//        if (cursor?.count != null) {
//            if (cursor.count > 0) {
//                while (cursor.moveToNext()) {
//                    val hasPhoneNumber: Int =
//                        cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))
//                            .toInt()
//                    if (hasPhoneNumber > 0) {
//                        contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID))
//                        displayName = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
//
//                        val phoneCursor: Cursor? = appControllerContract.getProperContext().contentResolver.query(
//                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", arrayOf<String?>(contactId), null)
//
//                        if (phoneCursor != null) {
//                            if (phoneCursor.moveToNext()) {
//                                val phoneNumber: String = phoneCursor.getString(
//                                    phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
//                                )
//                                val contactsInfoList = mutableListOf<ContactListItems>()
//                                val contactsInfo = ContactListItems(id = contactId, name = displayName, number = phoneNumber)
//                                contactsInfoList.add(contactsInfo)
//                                contactsInfoList.map { items.add(it) }
//                                Log.e("Contacts", contactsInfoList.toString())
//                            }
//                        }
//                        phoneCursor?.close()
//                    }
//                }
//            }
//            cursor.close()
//        }
//    }}