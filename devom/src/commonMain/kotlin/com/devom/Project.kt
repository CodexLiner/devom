package com.devom

import com.devom.category.Category
import com.devom.document.Document
import com.devom.notification.Notification
import com.devom.pandit.Pandit
import com.devom.payment.Payment
import com.devom.pooja.Pooja
import com.devom.poojaitem.PoojaItem
import com.devom.user.User

object Project {
    /**
     * contains all the use cases for user and auth related operations
     */
    val user by lazy { User() }

    /**
     * contains all the use cases for pooja related operations
     */
    val pooja by lazy { Pooja() }

    /**
     * contains all the use cases for pandit related operations
     */
    val pandit by lazy { Pandit() }

    /**
     * contains all the use cases for category related operations
     */
    val category by lazy { Category() }

    /**
     * contains all the use cases for poojaItem related operations
     */
    val poojaItem by lazy { PoojaItem() }

    /**
     * pandit documents
     */
    val document by lazy { Document() }

    /**
     * wallet
     */
    val payment by lazy { Payment() }

    /**
     * notification
     */
    val notification by lazy { Notification() }


}