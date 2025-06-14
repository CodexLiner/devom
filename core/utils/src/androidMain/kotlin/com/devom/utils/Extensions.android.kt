package com.devom.utils

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.provider.ContactsContract
import androidx.core.content.ContextCompat
import com.devom.utils.share.UtilsApp

actual fun getContacts(): List<Contact> {
    return UtilsApp.applicationContext.getContactList()
}

@SuppressLint("Recycle", "Range")
private fun Context.getContactList(): List<Contact> {
    val contacts = mutableListOf<Contact>()

    if (ContextCompat.checkSelfPermission(
            this, Manifest.permission.READ_CONTACTS
        ) == PackageManager.PERMISSION_GRANTED
    ) {

        val contentResolver = contentResolver

        val projection = arrayOf(
            ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER
        )

        val cursor = contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            projection,
            null,
            null,
            "${ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME} ASC"
        )

        val contactMap = mutableMapOf<Long, Contact>()

        cursor?.use {
            while (it.moveToNext()) {
                val id = it.getLong(it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID))
                val name = it.getString(it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                var phone = it.getString(it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)) ?: ""

                phone = phone.trim().replace("\\s+".toRegex(), "")
                if (phone.startsWith("+") && phone.length > 10) {
                    phone = phone.substring(phone.length - 10)
                }

                if (contactMap[id] == null) {
                    contactMap[id] = Contact(name, phone)
                }
            }
        }

        contacts.addAll(contactMap.values)
    } else Application.showToast("Please Allow Contact Permission")

    return contacts
}
