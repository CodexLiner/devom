package com.devom.app.ui.screens.addslot

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import com.devom.app.utils.to24HourTime
import com.devom.models.slots.Slot
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimeSlotBottomSheet(
    showSheet: Boolean,
    onDismiss: () -> Unit,
    onSlotSelected: (List<Slot>) -> Unit,
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()

    if (showSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                scope.launch {
                    sheetState.hide()
                    onDismiss() // Tell parent to set showSheet = false
                }
            },
            sheetState = sheetState
        ) {
            TimeSlotSelectorScreen {
                scope.launch {
                    onSlotSelected(it.map { slot ->
                        slot.copy(
                            startTime = slot.startTime.to24HourTime(),
                            endTime = slot.endTime.to24HourTime()
                        )
                    })
                    sheetState.hide()
                    onDismiss()
                }
            }
        }
    }
}
