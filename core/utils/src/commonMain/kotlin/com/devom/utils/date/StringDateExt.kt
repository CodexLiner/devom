package com.devom.utils.date

import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.format.DateTimeComponents
import kotlinx.datetime.format.DateTimeFormat
import kotlinx.datetime.format.FormatStringsInDatetimeFormats
import kotlinx.datetime.format.byUnicodePattern
import kotlinx.datetime.isoDayNumber
import kotlinx.datetime.plus
import kotlinx.datetime.toInstant
import kotlin.time.DurationUnit

val String.asDate: Instant
    get() = Instant.fromEpochSeconds(this.toLong())

@OptIn(FormatStringsInDatetimeFormats::class)
fun formatter(format: String): DateTimeFormat<LocalDateTime>{
    return LocalDateTime.Format {
        byUnicodePattern(format)
    }
}


@OptIn(FormatStringsInDatetimeFormats::class)
fun componentsFormatter(format: String): DateTimeFormat<DateTimeComponents>{
    return DateTimeComponents.Format {
        byUnicodePattern(format)
    }
}

fun String.formatTo(inputFormat: String, outputFormat: String): String? {
    return try {
        formatter(inputFormat).parse(this).toInstant(defaultTimeZone).formatTo(outputFormat)
    } catch (e: Exception) {
        ""
    }
}

fun String.formatIsoTo(outputFormat: String): String? {
    return try {
        if (this.isNotEmpty()) {
            Instant.parse(this).formatTo(outputFormat, TimeZone.UTC)
        } else {
            ""
        }
    } catch (e: Exception) {
        ""
    }
}

fun String.convertIsoToDate(): Instant? {
    return try {
        if (this.isNotEmpty()) {
            Instant.parse(this)
        } else {
            null
        }
    } catch (e: Exception) {
        null
    }
}

fun String.convertToISOFormat(inputFormat: String): String? {
    return try {
        if (this.isNotEmpty()) {
            LocalDateTime.parse(this).toInstant(TimeZone.UTC).toString()
        } else {
            ""
        }
    } catch (e: Exception) {
        ""
    }
}

fun String.isFutureDate(inputFormat: String): Boolean {
    return formatter(inputFormat).parse(this).toInstant(defaultTimeZone).isFutureDate()
}

fun String.isPastDate(inputFormat: String): Boolean {
    return formatter(inputFormat).parse(this).toInstant(defaultTimeZone).isPastDate()
}

fun String.isTodayDate(inputFormat: String): Boolean {
    return formatter(inputFormat).parse(this).toInstant(defaultTimeZone).isTodayDate()
}

fun String.isYesterdaysDate(inputFormat: String): Boolean {
    return formatter(inputFormat).parse(this).toInstant(defaultTimeZone).isYesterdaysDate()
}

fun String.isTomorrowsDate(inputFormat: String): Boolean {
    return formatter(inputFormat).parse(this).toInstant(defaultTimeZone).isTomorrowsDate()
}

fun String.getDate(inputFormat: String): Instant? {
    return try {
        formatter(inputFormat).parse(this).toInstant(defaultTimeZone)
    } catch (e: Exception) {
        null
    }
}

fun String.getYesterdaysDate(inputFormat: String): Instant {
    return formatter(inputFormat).parse(this).toInstant(defaultTimeZone).getYesterdaysDate()
}

fun String.getYesterdayStringDate(inputFormat: String, outputFormat: String): String? {
    return getYesterdaysDate(inputFormat).formatTo(outputFormat)
}

fun String.getTomorrowsDate(inputFormat: String): Instant {
    return formatter(inputFormat).parse(this).toInstant(defaultTimeZone).getTomorrowsDate()
}

fun String.getTomorrowsStringDate(inputFormat: String, outputFormat: String): String? {
    return getTomorrowsDate(inputFormat).formatTo(outputFormat)
}

fun String.getLastMonthDate(inputFormat: String): Instant {
    return formatter(inputFormat).parse(this).toInstant(defaultTimeZone).getLastMonthDate()
}

fun String.getLastMonthStringDate(inputFormat: String, outputFormat: String): String? {
    return getLastMonthDate(inputFormat).formatTo(outputFormat)
}

fun String.getNextMonthDate(inputFormat: String): Instant {
    return formatter(inputFormat).parse(this).toInstant(defaultTimeZone).getNextMonthDate()
}

fun String.getNextMonthStringDate(inputFormat: String, outputFormat: String): String? {
    return getNextMonthDate(inputFormat).formatTo(outputFormat)
}

fun String.getLastYearDate(inputFormat: String): Instant {
    return formatter(inputFormat).parse(this).toInstant(defaultTimeZone).getLastYearDate()
}

fun String.getLastYearStringDate(inputFormat: String, outputFormat: String): String?{
    return getLastYearDate(inputFormat).formatTo(outputFormat)
}

fun String.getNextYearDate(inputFormat: String): Instant {
    return formatter(inputFormat).parse(this).toInstant(defaultTimeZone).getNextYearDate()
}

fun String.getNextYearStringDate(inputFormat: String, outputFormat: String): String? {
    return getNextYearDate(inputFormat).formatTo(outputFormat)
}

fun String.hour(inputFormat: String, required24HrFormat: Boolean = false): Int {
    return if (required24HrFormat)
        formatter(inputFormat).parse(this).hour
    else
        formatter(inputFormat).parse(this).hour.mod(12)
}

fun String.minute(inputFormat: String): Int {
    return formatter(inputFormat).parse(this).minute
}

fun String.second(inputFormat: String): Int {
    return formatter(inputFormat).parse(this).second
}

fun String.month(inputFormat: String): Int {
    return formatter(inputFormat).parse(this).monthNumber
}

fun String.monthName(inputFormat: String): String {
    return formatter(inputFormat).parse(this).month.name
}

fun String.year(inputFormat: String): Int {
    return formatter(inputFormat).parse(this).year
}

fun String.day(inputFormat: String): Int {
    return formatter(inputFormat).parse(this).dayOfMonth
}

fun String.dayOfWeek(inputFormat: String): Int {
    return formatter(inputFormat).parse(this).dayOfWeek.isoDayNumber
}

fun String.dayOfWeekName(inputFormat: String): String {
    return formatter(inputFormat).parse(this).dayOfWeek.name
}

fun String.dayOfYear(inputFormat: String): Int? {
    return formatter(inputFormat).parse(this).dayOfYear
}

fun String?.toDisplayDate(inputFormat: String = "", outputFormat: String = MMM_DD_yyyy_hh_mm_a, isIsoFormat: Boolean = false): String {
    return if (this != null) {
        val date = if (isIsoFormat) {
            this.convertIsoToDate()
        } else {
            this.getDate(inputFormat)
        }
        if (date?.isTodayDate() == true)
            "Today, " + date.formatTo(hh_mm_a).orEmpty()
        else if (date?.isTomorrowsDate() == true)
            "Tomorrow, " + date.formatTo(hh_mm_a).orEmpty()
        else
            date?.formatTo(outputFormat).orEmpty()
    } else
        ""
}

fun String.roundMinute(interval: Int): String {
    val hour = this.takeWhile { it.isDigit() }.toInt()
    var mins = this.takeLastWhile { it.isDigit() }.toInt()
    return if (mins % interval == 0) {
        if (hour == 23 && mins > 55) {
            mins = 55
        }
        /*"${String.format("%02d", hour)}:${String.format("%02d", mins)}"*/""
    } else {
        if (hour == 23 && mins > 55) {
            "23:55"
        } else {
            mins = mins - (mins % interval) + interval
            if (mins == 60) {
                mins = 0
                /*"${String.format("%02d", hour + 1)}:${String.format("%02d", mins)}"*/ ""
            } else {
                /*"${String.format("%02d", hour)}:${String.format("%02d", mins)}"*/""
            }
        }
    }
}
fun String?.convertToIso8601(separator : String = "/"): String {
    // Input: "15/11/2002"
    val parts = this?.split(separator)
    if (parts?.size != 3) return ""
    val day = parts[0].toInt()
    val month = parts[1].toInt()
    val year = parts[2].toInt()
    val instant = LocalDate(year, month, day).atStartOfDayIn(TimeZone.UTC)
    return instant.toString()
}

fun isTimeIsInTimeRange(
    slotStartTime: String?,
    slotEndTime: String?,
    selectedTimeCalendar: Instant? = Clock.System.now(),
    futureDate: Instant? = null,
): Int {
    if (slotStartTime.isNullOrEmpty().not()
        && slotStartTime?.split(":").isNullOrEmpty().not() && slotStartTime?.split(":")?.size!! >= 2
        && slotEndTime.isNullOrEmpty().not()
        && slotEndTime?.split(":").isNullOrEmpty().not() && slotEndTime?.split(":")?.size!! >= 2) {
        return Pair(slotStartTime.split(":").let { hhMmList ->
            LocalDateTime(
                (futureDate ?: Clock.System.now()).toLocalDateTime(defaultTimeZone).date,
                LocalTime(
                    hour = hhMmList[0].toInt(),
                    minute = hhMmList[1].toInt(),
                    second = 0,
                    nanosecond = 0
                )
            ).toInstant(defaultTimeZone).formatTo(DD_MMM_yyyy_HH_mm_ss)
        }, slotEndTime.split(":").let { hhMmList ->
            LocalDateTime(
                (futureDate ?: Clock.System.now()).toLocalDateTime(defaultTimeZone).date,
                LocalTime(
                    hour = hhMmList[0].toInt(),
                    minute = hhMmList[1].toInt(),
                    second = 0,
                    nanosecond = 0
                )
            ).toInstant(defaultTimeZone).formatTo(DD_MMM_yyyy_HH_mm_ss)
        } ).let {
            try {
                isInstantBetween(selectedTimeCalendar ?: Clock.System.now(), it.first?.getDate(
                    DD_MMM_yyyy_HH_mm_ss
                ),it.second?.getDate(DD_MMM_yyyy_HH_mm_ss))
            } catch (e: Exception) {
                -1
            }
        }
    } else return -1
}

fun isInstantBetween(instant: Instant, start: Instant?, end: Instant?): Int {
    return when {
        start == null && end == null -> 0 // Both start and end are null
        start == null -> if (instant <= end!!) 0 else 1 // Only end is not null
        end == null -> if (instant >= start) 0 else -1 // Only start is not null
        else -> when {
            instant < start -> -1 // Before the start
            instant in start..end -> 0 // Within the range
            else -> 1 // After the end
        }
    }
}

fun addMinutesInTime(startDate: Instant?, startTime: String?, duration: Int): Instant? {
    startTime?.split(":")?.let {
        if (it.size >= 2) return try {
            LocalDateTime(
                (startDate ?: Clock.System.now()).toLocalDateTime(defaultTimeZone).date,
                LocalTime(
                    hour = it.first().toInt(),
                    minute = it[1].toInt(),
                    second = 0,
                    nanosecond = 0
                )
            ).toInstant(defaultTimeZone).plus(duration, DateTimeUnit.MINUTE, defaultTimeZone)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
    return null
}

fun Pair<String, String>.getSlotDurationInMinutes(startTimeFormat: String = HH_mm, endTimeFormat: String = HH_mm): Long {
    val startTimeInstant = Instant.parse(first, componentsFormatter(startTimeFormat))
    val endTimeInstant = Instant.parse(second, componentsFormatter(endTimeFormat))
    return (endTimeInstant - startTimeInstant).toLong(DurationUnit.MILLISECONDS)
}

fun Long.getRemainingTime(
    dayLabel: String = "d",
    hourLabel: String = "h",
    minLabel: String = "m",
    secLabel: String = "s",
    showSeconds: Boolean = false
): String {

    val dayCount =  asDate.day()
    val hourCount = asDate.hour()
    val minCount = asDate.minute()
    val secCount = asDate.second()

    if (dayCount > 0) {
        return with(StringBuilder()) {
            append("$dayCount $dayLabel")
            if ((hourCount % 24) != 0) {
                append(" ${hourCount % 24} $hourLabel")
            }
            toString()
        }
    } else if (minCount > 0) {
        val hours = minCount / 60
        val minutes = minCount % 60
        return if (hours > 0) {
            "${hours}${hourLabel} ${(if (minutes > 0) minutes else 60) % 60}${minLabel}"
        } else {
            "$minutes$minLabel"
        }
    } else if (showSeconds && secCount > 0) {
        return "$secCount$secLabel"
    } else if (secCount > 0) {
        return "1$minLabel"
    }
    return ""
}