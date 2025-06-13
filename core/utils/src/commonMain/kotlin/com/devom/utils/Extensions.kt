package com.devom.utils

expect fun getContacts(): List<Contact>

data class Contact(val name: String, val phoneNumber: String)