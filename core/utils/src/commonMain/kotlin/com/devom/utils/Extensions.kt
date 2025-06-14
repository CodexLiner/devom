package com.devom.utils

data class Contact(val name: String, val phoneNumber: String)
expect fun getContacts(): List<Contact>