package com.devom.utils
import platform.Contacts.*
import kotlinx.cinterop.*


@OptIn(ExperimentalForeignApi::class)
actual fun getContacts(): List<Contact> {
    val contacts = mutableListOf<Contact>()
    val store = CNContactStore()
    val keysToFetch = listOf(CNContactGivenNameKey, CNContactFamilyNameKey, CNContactPhoneNumbersKey)
    val fetchRequest = CNContactFetchRequest(keysToFetch)
    store.enumerateContactsWithFetchRequest(fetchRequest , null) { contact , _ ->
        val name = "${contact?.givenName} ${contact?.familyName}"
        contact?.phoneNumbers?.forEach { phoneNumber ->
            phoneNumber?.let {
                contacts.add(Contact(name, it.toString()))
            }
        }
    }

    return contacts
}