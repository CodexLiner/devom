package org.company.app.models

import androidx.compose.ui.input.key.Key.Companion.R
import multiplatform_app.composeapp.generated.resources.Res
import multiplatform_app.composeapp.generated.resources.ic_google
import org.jetbrains.compose.resources.DrawableResource

data class Booking(
    val name: String,
    val phone: String,
    val address: String,
    val poojaType: String,
    val dateTime: String,
    val imageRes: DrawableResource
)

val sampleBookings = listOf(
    Booking(
        name = "Amrita Singh",
        phone = "(406) 555-0120",
        address = "12 Helen St, Newstead EC1A 1BB",
        poojaType = "Wedding",
        dateTime = "MAR 23, 2025 01:00 pm",
        imageRes = Res.drawable.ic_google
    ),
    Booking(
        name = "Ravi Kumar",
        phone = "(408) 555-0199",
        address = "45 Palm St, Westwood W1D 4FA",
        poojaType = "Satyanarayan Katha",
        dateTime = "MAR 24, 2025 10:00 am",
        imageRes = Res.drawable.ic_google
    ),
    Booking(
        name = "Sneha Reddy",
        phone = "(312) 555-0182",
        address = "9 Abbey Rd, London NW8 9AY",
        poojaType = "Griha Pravesh",
        dateTime = "MAR 25, 2025 08:00 am",
        imageRes = Res.drawable.ic_google
    ),
    Booking(
        name = "Vikram Patel",
        phone = "(215) 555-0160",
        address = "88 Market St, Nottingham NG1 6HW",
        poojaType = "Bhoomi Pujan",
        dateTime = "MAR 26, 2025 03:30 pm",
        imageRes = Res.drawable.ic_google
    ),
    Booking(
        name = "Priya Desai",
        phone = "(646) 555-0143",
        address = "33 Green Ln, Manchester M45 7TJ",
        poojaType = "Naamkaran",
        dateTime = "MAR 27, 2025 11:00 am",
        imageRes = Res.drawable.ic_google
    )
)
