package com.devom.utils.date

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun String?.toIsoDate(time: TimeZone = TimeZone.UTC): LocalDateTime? {
    this?.let {
        return Instant.parse(this).toLocalDateTime(time)
    }
    return null
}