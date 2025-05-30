package com.devom.app.ui.screens.addslot

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import co.touchlab.kermit.Logger
import com.devom.app.theme.backgroundColor
import com.devom.app.theme.text_style_lead_body_1
import com.devom.app.ui.components.AppBar
import com.devom.app.ui.components.ButtonPrimary
import com.devom.app.ui.components.DateItem
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
    selectedDate: LocalDate = Clock.System.now()
        .toLocalDateTime(TimeZone.currentSystemDefault()).date,
) {
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
    val dates = remember(startOfWeek) {
        List(7) { index -> startOfWeek.plus(index, DateTimeUnit.DAY) }
    }

    val formattedMonthYear = remember(selectedDate) {
        "${selectedDate.month.name.lowercase().replaceFirstChar(Char::uppercaseChar)} ${selectedDate.year}"
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
                            scope.launch {

                            }
                            // TODO: Handle date selection
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

                TimeSlotBottomSheet(sheetState.value , onDismiss = {
                    sheetState.value = false

                }) {
                    Logger.d("SELECTED_SLOTS") { "Selected Slots $it" }
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
