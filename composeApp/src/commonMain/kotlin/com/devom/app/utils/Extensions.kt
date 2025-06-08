package com.devom.app.utils

import androidx.compose.ui.graphics.Color
import com.devom.app.IMAGE_BASE_URL
import com.devom.models.slots.Slot
import io.ktor.http.encodeURLPath
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atTime
import kotlinx.datetime.minus
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime

fun String.toColor(): Color {
    val hex = removePrefix("#")
    return Color(hex.toLong(16) or (if (hex.length == 6) 0xFF000000 else 0x00000000))
}

fun String?.toDevomImage(): String? {
    val encodedPath = this?.encodeURLPath()
    return IMAGE_BASE_URL + encodedPath
}


fun List<Slot>.updateSlotTimeAndShiftFollowingSlots(
    indexToUpdate: Int,
    newStartTime: String? = null,
    newEndTime: String? = null,
): MutableList<Slot> {
    val updatedSlots = this.map { it.copy() }.toMutableList()
    val slot = updatedSlots[indexToUpdate]
    val today = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date

    // Parse original times
    val originalStart = parse12HourTime(slot.startTime)
    val originalEnd = parse12HourTime(slot.endTime)

    // Apply changes
    val startTime = newStartTime?.let { parse12HourTime(it) } ?: originalStart
    val endTime = newEndTime?.let { parse12HourTime(it) } ?: originalEnd

    // Convert to LocalDateTime
    val startDT = today.atTime(startTime)
    var endDT = today.atTime(endTime)

    // Calculate gap in minutes
    val gapInMinutes = endDT.toInstant(TimeZone.currentSystemDefault())
        .minus(startDT.toInstant(TimeZone.currentSystemDefault()), DateTimeUnit.MINUTE)

    // If gap less than 2 hours (120 mins), update endDT to startDT + 2 hours
    if (gapInMinutes < 120) {
        endDT = startDT.addHours(2)
    }

    // Update the selected slot
    slot.startTime = startDT.time.to12HourTime()
    slot.endTime = endDT.time.to12HourTime()

    // Start shifting from updated end time
    var nextStart = endDT
    val slotGapHours = 2

    for (i in indexToUpdate + 1 until updatedSlots.size) {
        val s = updatedSlots[i]
        val newStartDT = nextStart
        val newEndDT = newStartDT.addHours(slotGapHours)

        s.startTime = newStartDT.time.to12HourTime()
        s.endTime = newEndDT.time.to12HourTime()

        nextStart = newEndDT
    }

    return updatedSlots
}

