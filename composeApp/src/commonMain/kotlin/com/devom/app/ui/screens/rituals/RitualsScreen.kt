package com.devom.app.ui.screens.rituals

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.devom.app.theme.blackColor
import com.devom.app.theme.greyColor
import com.devom.app.theme.textBlackShade
import com.devom.app.theme.text_style_h3
import com.devom.app.ui.components.AppBar
import com.devom.app.ui.components.ButtonPrimary
import com.devom.app.ui.components.DropDownItem
import com.devom.app.ui.components.ExposedDropdown
import com.devom.app.ui.components.TextInputField
import com.devom.app.utils.toColor
import com.devom.models.pandit.GetPanditPoojaResponse
import com.devom.models.pandit.MapPanditPoojaItemInput
import com.devom.models.pooja.GetPoojaResponse
import com.devom.utils.Application
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import pandijtapp.composeapp.generated.resources.Res
import pandijtapp.composeapp.generated.resources.all_field_required
import pandijtapp.composeapp.generated.resources.ic_arrow_left

@Composable
fun RitualsScreen(navController: NavController) {
    val viewModel = viewModel {
        RitualsViewModel()
    }
    Column(modifier = Modifier.fillMaxSize().background(Color.White)) {
        AppBar(
            navigationIcon = painterResource(Res.drawable.ic_arrow_left),
            title = "Preferred Rituals/Poojas",
            onNavigationIconClick = { navController.popBackStack() })
        RitualsScreenScreenContent(viewModel, navController)
    }

    LaunchedEffect(Unit) {
        viewModel.getUserProfile()
    }
}

@Composable
fun ColumnScope.RitualsScreenScreenContent(
    viewModel: RitualsViewModel,
    navController: NavController,
) {
    val poojaList = viewModel.rituals.collectAsState()
    val poojaItemsList = viewModel.getPoojaItems.collectAsState()
    val showSheet = remember { mutableStateOf(false) }
    val selectedDropDownItem = remember { mutableStateOf<GetPanditPoojaResponse?>(null) }
    LazyColumn(
        modifier = Modifier.weight(1f),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 20.dp)
    ) {
        items(poojaList.value.orEmpty()) {
            PoojaItemContent(it) {
                selectedDropDownItem.value = it
                showSheet.value = true
            }
        }
    }
    ButtonPrimary(
        buttonText = "Add Pooja",
        modifier = Modifier.navigationBarsPadding().padding(start = 16.dp, end = 16.dp)
            .fillMaxWidth().height(58.dp),
        onClick = {
            showSheet.value = true
        }
    )

    if (showSheet.value) {
        AddEditPoojaBottomSheet(
            poojaList = poojaItemsList.value,
            poojaItem = selectedDropDownItem.value,
            title = "${if (selectedDropDownItem.value == null) "Add" else "Edit"} Pooja",
            showSheet = showSheet.value,
            onDismiss = {
                showSheet.value = false
                selectedDropDownItem.value = null
            },
            onClick = {
                showSheet.value = false
                selectedDropDownItem.value = null
                viewModel.mapPoojaItem(it)
            }
        )
    }
}

@Composable
fun PoojaItemContent(poojaItem: GetPanditPoojaResponse , onClick: () -> Unit = {}) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth().border(
            width = 1.dp, color = "#A0A5BA3D".toColor(), shape = RoundedCornerShape(12.dp)
        ).padding(horizontal = 16.dp).padding(vertical = 8.dp).clickable(onClick = onClick)
    ) {
        Text(
            text = poojaItem.poojaName.capitalize(Locale.current),
            fontWeight = FontWeight.W600,
            color = textBlackShade,
            fontSize = 16.sp
        )

        HorizontalDivider(
            thickness = 1.dp, color = greyColor.copy(0.25f)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Cost with Samagri",
                    fontWeight = FontWeight.W600,
                    color = greyColor,
                    fontSize = 12.sp
                )
                Text(
                    text = "₹ ${poojaItem.withItemPrice}",
                    fontWeight = FontWeight.W600,
                    color = textBlackShade,
                    fontSize = 14.sp
                )
            }

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Cost with Samagri",
                    fontWeight = FontWeight.W600,
                    color = greyColor,
                    fontSize = 12.sp
                )
                Text(
                    text = "₹ ${poojaItem.withoutItemPrice}",
                    fontWeight = FontWeight.W600,
                    color = textBlackShade,
                    fontSize = 14.sp
                )
            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditPoojaBottomSheet(
    showSheet: Boolean,
    title: String? = null,
    onDismiss: () -> Unit,
    poojaList: List<GetPoojaResponse>,
    onClick: (MapPanditPoojaItemInput) -> Unit,
    poojaItem: GetPanditPoojaResponse?,
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val poojaItemMappingInput by remember { mutableStateOf(poojaItem ?: GetPanditPoojaResponse()) }
    val selectedDropDownItem = remember {
        mutableStateOf<DropDownItem?>(
            DropDownItem(
                poojaItemMappingInput?.poojaName.orEmpty(),
                poojaItemMappingInput?.poojaId.toString()
            )
        )
    }
    val scope = rememberCoroutineScope()
    val numberKeyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
    val errorState = remember { mutableStateOf(false) }

    if (showSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                scope.launch {
                    sheetState.hide()
                    onDismiss()
                }
            }, sheetState = sheetState
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(24.dp),
                modifier = Modifier.padding(horizontal = 24.dp)
            ) {
                title?.let {
                    Text(text = it, style = text_style_h3, color = blackColor)
                }
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {

                    ExposedDropdown(
                        placeholder = "Ritual/Pooja Name",
                        options = poojaList.map { DropDownItem(it.name, it.id.toString()) },
                        selectedOption = selectedDropDownItem.value
                    ) {
                        selectedDropDownItem.value = it
                        errorState.value = false
                    }

                    TextInputField(
                        keyboardOptions = numberKeyboardOptions,
                        initialValue = poojaItemMappingInput.withItemPrice,
                        placeholder = "Cost with Samagri (₹)"
                    ) {
                        poojaItemMappingInput?.withItemPrice = it
                        errorState.value = false
                    }


                    TextInputField(
                        keyboardOptions = numberKeyboardOptions,
                        initialValue = poojaItemMappingInput.withoutItemPrice,
                        placeholder = "Cost without Samagri (₹)"
                    ) {
                        poojaItemMappingInput?.withoutItemPrice = it
                        errorState.value = false
                    }

                    if (errorState.value) Text(text = stringResource(Res.string.all_field_required), color = Color.Red)

                }

                ButtonPrimary(
                    modifier = Modifier.padding(top = 26.dp).fillMaxWidth().height(58.dp),
                    buttonText = "Save"
                ) {
                    if (isValidInput(poojaItemMappingInput, selectedDropDownItem.value)) {
                        onClick(
                            MapPanditPoojaItemInput(
                                poojaId = selectedDropDownItem.value?.id?.toIntOrNull() ?: 0,
                                withItemPrice = poojaItemMappingInput.withItemPrice,
                                withoutItemPrice = poojaItemMappingInput.withoutItemPrice
                            )
                        )
                    } else errorState.value = true
                }
            }
        }
    }
}

private fun isValidInput(
    poojaItemMappingInput: GetPanditPoojaResponse?,
    value: DropDownItem?,
    error: String? = "All fields are required",
): Boolean {
    val isValid = value != null && poojaItemMappingInput?.withItemPrice.orEmpty()
        .isNotEmpty() && poojaItemMappingInput?.withoutItemPrice.orEmpty().isNotEmpty()
    if (isValid.not()) Application.showToast(error.orEmpty())
    return isValid
}
