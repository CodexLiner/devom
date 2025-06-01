package com.devom.app.ui.screens.addslot

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.devom.app.theme.backgroundColor
import com.devom.app.ui.components.AppBar
import com.devom.app.ui.components.ButtonPrimary
import com.devom.app.ui.components.DateItem
import com.devom.app.utils.format
import com.devom.app.utils.to12HourTime
import com.devom.utils.date.formatIsoTo
import com.devom.utils.date.yyyy_MM_DD
import kotlinx.coroutines.launch
import kotlinx.datetime.*
import org.jetbrains.compose.resources.stringResource
import pandijtapp.composeapp.generated.resources.Res
import pandijtapp.composeapp.generated.resources.add_time_slot
import pandijtapp.composeapp.generated.resources.set_availablity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateSlotScreen(
    navController: NavController,
    initialSelectedDate: LocalDate = Clock.System.now()
        .toLocalDateTime(TimeZone.currentSystemDefault()).date,
) {
    val viewModel = viewModel { CreateSlotViewModel() }
    val user = viewModel.user.collectAsState()
    val availableSlots = viewModel.slots.collectAsState()
    var selectedDate by remember { mutableStateOf(initialSelectedDate) }

    LaunchedEffect(user.value) {
        user.value?.userId?.let { viewModel.getAvailableSlots(it) }
    }


    val horizontalPadding = 16.dp
    val verticalSpacing = 16.dp
    val buttonHeight = 58.dp
    val buttonBottomPadding = 16.dp

    val startOfWeek = remember(selectedDate) {
        selectedDate.with(DayOfWeek.MONDAY)
    }

    var sheetState = remember {
        mutableStateOf(false)
    }

    val scope = rememberCoroutineScope()
    val startOfList = initialSelectedDate
    val dates = remember(startOfList) {
        List(7) { index -> startOfList.plus(index, DateTimeUnit.DAY) }
    }

    val formattedMonthYear = remember(selectedDate) {
        "${
            selectedDate.month.name.lowercase().replaceFirstChar(Char::uppercaseChar)
        } ${selectedDate.year}"
    }

    Scaffold(
        topBar = {
            AppBar(
                title = stringResource(Res.string.set_availablity),
                onNavigationIconClick = { navController.popBackStack() }
            )
        },
        bottomBar = {
            ButtonPrimary(
                buttonText = stringResource(Res.string.add_time_slot),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = horizontalPadding, vertical = buttonBottomPadding)
                    .height(buttonHeight)
            ) {
                scope.launch {
                    sheetState.value = true
                }
            }
        },
        containerColor = backgroundColor
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = horizontalPadding)
        ) {
            Spacer(Modifier.height(verticalSpacing))

            Text(
                text = formattedMonthYear,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(12.dp))

            LazyRow(
                contentPadding = PaddingValues(horizontal = 0.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(dates) { date ->
                    DateItem(
                        date = date,
                        isSelected = date == selectedDate,
                        onClick = {
                            selectedDate = date
                        }
                    )
                }
            }

            Spacer(Modifier.height(24.dp))

            Text(
                text = "Slots Available",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(8.dp))

            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        color = Color.LightGray.copy(alpha = 0.5f),
                        shape = RoundedCornerShape(16.dp)
                    )
                    .background(Color(0xFFF9FCFF), shape = RoundedCornerShape(16.dp))
                    .padding(16.dp)
            ) {
                LazyColumn {
                    items(availableSlots.value.filter { it.availableDate.formatIsoTo(yyyy_MM_DD) == selectedDate.format(yyyy_MM_DD) }) { slot ->
                        TimeSlotItem(slot = slot.copy(
                            startTime = slot.startTime.to12HourTime(),
                            endTime = slot.endTime.to12HourTime()
                        ), enabled = false)
                    }
                }

                TimeSlotBottomSheet(initialSelectedDate = selectedDate , showSheet = sheetState.value, onDismiss = {
                    sheetState.value = false

                }) {
                   viewModel.createPanditSlot(it)
                }
            }
        }
    }
}

/** Returns a LocalDate adjusted to the given day of week in the same week */
fun LocalDate.with(dayOfWeek: DayOfWeek): LocalDate {
    val currentOrdinal = this.dayOfWeek.ordinal
    val targetOrdinal = dayOfWeek.ordinal
    val difference = targetOrdinal - currentOrdinal
    return this.plus(difference, DateTimeUnit.DAY)
}
