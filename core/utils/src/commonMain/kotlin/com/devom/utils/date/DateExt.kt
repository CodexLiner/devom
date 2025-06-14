package com.devom.utils.date

import kotlinx.datetime.*
import kotlinx.datetime.format.MonthNames
import kotlinx.datetime.format.Padding
import kotlinx.datetime.format.char

/**
 * Date formats vars
 */
const val DD_MMM_yyyy = "dd-MMM-yyyy"
const val d_MMM_yyyy = "d MMM yyyy"
const val dd_MMM_yyyy = "dd MMM yyyy"
const val DD_MM_yyyy_HH_mm_ss_EEE = "dd-MM-yyyy HH:mm:ss EEE"
const val DD_MM_yyyy_HH_mm_ss = "dd-MM-yyyy HH:mm:ss"
const val DD_MM_yyyy_hh_mm_ss_a = "dd-MM-yyyy hh:mm:ss a"
const val EEEE_DD_MMM_yyyy_hh_mm_ss_a = "EEEE, dd MMM yyyy hh:mm:ss a"
const val MMM_DD_yyyy_hh_mm_a = "MMM dd, yyyy, hh:mm a"
const val DD_MMM_yyyy_hh_mm_ss = "dd MMM yyyy hh:mm:ss"
const val EEEE_DD_MMM_yyyy_HH_mm_ss = "EEEE, dd MMM yyyy HH:mm:ss"
const val DD_MMM_yyyy_HH_mm_ss = "dd MMM yyyy HH:mm:ss"
const val D_MM_yyyy_slash = "dd/MM/yyyy"
const val DD_MMM_yyyy_slash = "dd/MMM/yyyy"
const val DD_MM_yyyy_HH_mm_ss_slash = "dd/MM/yyyy HH:mm:ss"
const val DD_MM_yy_HH_mm_ss_slash = "dd/MM/yy hh:mm a"
const val DD_MM_yyyy_hh_mm_ss_a_slash = "dd/MM/yyyy hh:mm:ss a"
const val D = "d"
const val DD = "dd"
const val MM = "MM"
const val MMM = "MMM"
const val yyyy = "yyyy"
const val DD_MMM = "dd MMM"
const val D_MMM = "d MMM"
const val yyyy_MM_DD = "yyyy-MM-dd"
const val ISO_8601_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ"
const val UTC_DATE_FORMAT = "yyyy-MM-d'T'HH:mm:ss.yyyy'Z'"
const val MMM_dd_yyyy = "MMM dd, yyyy"
const val EEE_MMM_dd_yyyy = "EEEE, MMM dd, yyyy"
const val EEE_MMM_dd_yyyy_hh_mm = "EEEE, MMM dd, yyyy hh:mm"
const val UNICODE_PREFIX = "&#x"

/**
 * Time formats vars
 */
const val HH_mm_ss = "HH:mm:ss"
const val HH_mm = "HH:mm"
const val hh_mm_ss_a = "hh:mm:ss a"
const val hh_mm_ss = "hh:mm:ss"
const val hh_mm = "hh:mm"
const val hh_mm_a = "hh:mm a"
const val hh = "hh"
const val HH = "HH"

val defaultTimeZone = TimeZone.currentSystemDefault()

val Long.asDate: Instant
    get() = Instant.fromEpochMilliseconds(this)


fun Instant.formatTo(requiredFormat: String, timeZone: TimeZone = defaultTimeZone): String? {
    var dateStr: String? = null
    try {
        dateStr = formatter(requiredFormat).format(toLocalDateTime(timeZone))
    } catch (_: Exception){}
    return dateStr
}

fun Instant.toLocalDateTime(systemTimeZone: TimeZone = defaultTimeZone): LocalDateTime {
    return toLocalDateTime(timeZone = systemTimeZone)
}

fun Instant.isPastDate(): Boolean {
    return toLocalDateTime().date < Clock.System.now().toLocalDateTime().date
}

fun Instant.isFutureDate(): Boolean {
    return toLocalDateTime().date > Clock.System.now().toLocalDateTime().date
}

fun Instant.isTodayDate(): Boolean {
    return toLocalDateTime().date == Clock.System.now().toLocalDateTime().date
}

fun Instant.isYesterdaysDate(): Boolean {
    return toLocalDateTime().date == Clock.System.now().minus(1, DateTimeUnit.DAY, defaultTimeZone)
        .toLocalDateTime().date
}

fun Instant.isTomorrowsDate(): Boolean {
    return toLocalDateTime().date == Clock.System.now().plus(1, DateTimeUnit.DAY, defaultTimeZone)
        .toLocalDateTime().date
}

fun Instant.getYesterdaysDate(): Instant {
    return this.minus(1, DateTimeUnit.DAY, defaultTimeZone)
}

fun Instant.getYesterdaysStringDate(outputFormat: String): String? {
    return getYesterdaysDate().formatTo(outputFormat)
}

fun Instant.getTomorrowsDate(): Instant {
    return plus(1, DateTimeUnit.DAY, defaultTimeZone)
}

fun Instant.getTomorrowsStringDate(outputFormat: String): String? {
    return getTomorrowsDate().formatTo(outputFormat)
}

fun Instant.getLastMonthDate(): Instant {
    return minus(1, DateTimeUnit.MONTH, defaultTimeZone)
}

fun Instant.getLastMonthStringDate(outputFormat: String): String? {
    return getLastMonthDate().formatTo(outputFormat)
}

fun Instant.getNextMonthDate(): Instant {
    return plus(1, DateTimeUnit.MONTH, defaultTimeZone)
}

fun Instant.getNextMonthStringDate(outputFormat: String): String? {
    return getNextMonthDate().formatTo(outputFormat)
}

fun Instant.getLastYearDate(): Instant {
    return minus(1, DateTimeUnit.YEAR, defaultTimeZone)
}

fun Instant.getLastYearStringDate(outputFormat: String): String? {
    return getLastYearDate().formatTo(outputFormat)
}

fun Instant.getNextYearDate(): Instant {
    return plus(1, DateTimeUnit.YEAR, defaultTimeZone)
}

fun Instant.getNextYearStringDate(outputFormat: String): String? {
    return getNextYearDate().formatTo(outputFormat)
}

fun Instant.addMinutes(minutes: Int, outputFormat: String): String? {
    return plus(minutes, DateTimeUnit.MINUTE, defaultTimeZone).formatTo(outputFormat)
}

fun Instant.hour(required24HrFormat: Boolean = false): Int {
    val localDateTime = toLocalDateTime()
    return if (required24HrFormat) localDateTime.hour else localDateTime.hour.mod(12)
}

fun Instant.minute(): Int {
    return toLocalDateTime().minute
}

fun Instant.second(): Int {
    return toLocalDateTime().second
}

fun Instant.month(): Int {
    return this.toLocalDateTime().monthNumber
}

fun Instant.monthName(): String {
    return this.toLocalDateTime(TimeZone.UTC).month.name
}

fun Instant.year(): Int {
    return this.toLocalDateTime().year
}

fun Instant.day(): Int {
    return this.toLocalDateTime().dayOfMonth
}

fun Instant.dayOfWeek(): Int {
    return this.toLocalDateTime().dayOfWeek.isoDayNumber
}

fun Instant.dayOfWeekName(): String {
    return this.toLocalDateTime().dayOfWeek.name
}

fun Instant.dayOfYear(): Int {
    return this.toLocalDateTime().dayOfYear
}

fun Instant.differenceBetweenDates(currentDate: Instant): Long {
    return this.periodUntil(currentDate, defaultTimeZone).seconds * 1000L
}

fun Instant.convertToISOFormat(): String {
    return toString()
}

fun Instant.addYears(years: Int): Instant {
    return plus(years, DateTimeUnit.YEAR, defaultTimeZone)
}

fun Instant.addMonths(months: Int): Instant {
    return plus(months, DateTimeUnit.MONTH, defaultTimeZone)
}

fun Instant.addDays(days: Int): Instant {
    return plus(days, DateTimeUnit.DAY, defaultTimeZone)
}

fun Instant.addWeeks(weeks: Int): Instant {
    return plus(weeks, DateTimeUnit.WEEK, defaultTimeZone)
}

fun Instant.addHours(hours: Int): Instant {
    return plus(hours, DateTimeUnit.HOUR, defaultTimeZone)
}

fun Instant.addMinutes(minutes: Int): Instant {
    return plus(minutes, DateTimeUnit.MINUTE, defaultTimeZone)
}

fun Instant.addSeconds(seconds: Int): Instant {
    return plus(seconds, DateTimeUnit.SECOND, defaultTimeZone)
}

fun LocalDate.toEpochMilli(): Long {
    return this.atStartOfDayIn(TimeZone.currentSystemDefault()).toEpochMilliseconds()
}

fun LocalDate.toIsoDateTimeString(): String {
    return this.atTime(LocalTime(0, 0)).toInstant(TimeZone.UTC).toString()
}

/**
 * Get Current Local Date Time
 * */
fun getCurrentLocalDateTime(): LocalDateTime {
    return Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
}
/**
 * Get Local Date Time from String
 * */
fun getLocalDateTime(date: String, time: String): LocalDateTime? {
    try {
        return LocalDateTime(
            LocalDate.parse(date), LocalTime.parse(time)
        )
    } catch (e: Exception) {
        return null
    }
}
/**
 * Get Local Date Time in current timezone from UTC String
 * */
fun getLocalDateTimeFromUTC(dateTime: String): LocalDateTime? {
    try {
        return Instant.parse(dateTime).toLocalDateTime(TimeZone.currentSystemDefault())
    } catch (e: Exception) {
        return null
    }
}
/**
 * Get Local Date in current timezone from String
 * */
fun getLocalDate(date: String): LocalDate? {
    try {
        return LocalDate.parse(date)
    } catch (e: Exception) {
        return null
    }
}
fun LocalTime?.toAmPm(): String {
    return this?.format(LocalTime.Format {
        amPmHour(padding = Padding.NONE)
        char(':')
        minute()
        char(' ')
        amPmMarker("Am", "Pm")
    }).orEmpty()
}
fun LocalDateTime?.addMinutesToLocalDateTime(minutesToAdd: Int): LocalDateTime? {
    return this?.toInstant(TimeZone.currentSystemDefault())?.plus(minutesToAdd, DateTimeUnit.MINUTE)
        ?.toLocalDateTime(TimeZone.currentSystemDefault())
}
fun LocalDateTime?.minusMinutesToLocalDateTime(minutesToAdd: Int): LocalDateTime? {
    return this?.toInstant(TimeZone.currentSystemDefault())
        ?.minus(minutesToAdd, DateTimeUnit.MINUTE)?.toLocalDateTime(TimeZone.currentSystemDefault())
}
fun LocalTime.toTimeFormat(): String {
    return this.format(LocalTime.Format {
        hour()
        char(':')
        minute()
        char(':')
        second()
    })
}
fun LocalDate.toDateFormat(): String {
    return this.format(LocalDate.Format {
        monthName(MonthNames.ENGLISH_FULL)
        char(' ')
        dayOfMonth(padding = Padding.NONE)
    })
}
fun LocalDate.toCompleteDateFormat(): String {
    return this.format(LocalDate.Format {
        monthName(MonthNames.ENGLISH_ABBREVIATED)
        char(' ')
        dayOfMonth(padding = Padding.NONE)
        char(',')
        char(' ')
        year(padding = Padding.NONE)
    })
}

fun LocalDate?.formatDate(): String? {
   return this?.let {
        val currentDate = getCurrentLocalDateTime().date
        val yesterday = currentDate.minus(1, DateTimeUnit.DAY)
        return when(this){
            currentDate -> "Today, ${this.toDateFormat()
                .replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }}"
            yesterday -> "Yesterday, ${this.toDateFormat()
                .replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }}"
            else -> this.toCompleteDateFormat()
                .replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
        }
    }
}



fun formatDateAndTime(inputDate: LocalDate): String{
    val currentDate = getCurrentLocalDateTime().date
    return when(inputDate){
        currentDate -> "${getCurrentLocalDateTime().time.toAmPm().uppercase()} , Today"
        else -> getCurrentLocalDateTime().time.toAmPm().uppercase()+" , "+ getCurrentLocalDateTime().date.toDateFormat()
    }
}

/*
fun Instant.localToUTC(): Instant {
    return Date(this.time + Calendar.getInstance().getTimeZone().getOffset(Date().time))
}*/
