package com.devom.utils

import android.annotation.SuppressLint
import android.content.Context
import android.provider.ContactsContract
import com.devom.utils.share.UtilsApp

actual fun getContacts(): List<Contact> {
    return UtilsApp.applicationContext.getContactList()
}

@SuppressLint("Recycle", "Range")
private fun Context.getContactList(): List<Contact> {
    val contacts = mutableListOf<Contact>()
    val contentResolver = contentResolver
    val cursor =
        contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null)
    cursor?.use {
        while (it.moveToNext()) {
            val id = it.getString(it.getColumnIndex(ContactsContract.Contacts._ID))
            val name = it.getString(it.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
            val phoneCursor = contentResolver.query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null,
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                arrayOf(id),
                null
            )
            phoneCursor?.use { phoneIt ->
                while (phoneIt.moveToNext()) {
                    val phoneNumber =
                        phoneIt.getString(phoneIt.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                    contacts.add(Contact(name, phoneNumber))
                }
            }
            phoneCursor?.close()
        }
    }
    cursor?.close()
    return contacts
}
