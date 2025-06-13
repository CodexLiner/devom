package com.devom.app.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.IconButton
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import com.devom.utils.Application
import org.jetbrains.compose.resources.painterResource
import pandijtapp.composeapp.generated.resources.Res
import pandijtapp.composeapp.generated.resources.ic_arrow_drop_down

data class DropDownItem(
    val option: String,
    val id: String,
)

@Composable
fun ExposedDropdown(
    expanded: Boolean = false,
    enabled: Boolean = true,
    disabledMessage: String = "",
    modifier: Modifier = Modifier,
    placeholder: String = "",
    options: List<DropDownItem> = listOf(),
    selectedOption: DropDownItem? = null,
    onOptionSelected: (DropDownItem) -> Unit,
) {
    var isExpanded by remember { mutableStateOf(expanded) }
    var isEnabled  by remember { mutableStateOf(enabled) }

    LaunchedEffect(expanded) {
        isExpanded = expanded
    }
    LaunchedEffect(enabled) {
        isEnabled = enabled
    }

    LaunchedEffect(Unit) {
        if (selectedOption != null) {
            onOptionSelected(selectedOption)
        }
    }
    DropDownContent(
        enabled = isEnabled,
        disabledMessage = disabledMessage,
        placeholder = placeholder,
        expanded = isExpanded,
        modifier = modifier,
        options = options,
        selectedOption = selectedOption,
        onSelect = onOptionSelected,
        onDismiss = {
            isExpanded = false
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DropDownContent(
    modifier: Modifier,
    options: List<DropDownItem>,
    selectedOption: DropDownItem?,
    onDismiss: () -> Unit = {},
    onSelect: (DropDownItem) -> Unit,
    expanded: Boolean,
    placeholder: String,
    enabled: Boolean,
    disabledMessage: String,
) {
    val focusManager = LocalFocusManager.current
    var expanded = remember { mutableStateOf(expanded) }
    var focusRequester = remember { FocusRequester() }
    val localSelectedOption = remember { mutableStateOf(selectedOption) }


    LaunchedEffect(Unit) {
        focusManager.clearFocus(true)
    }

    ExposedDropdownMenuBox(
        modifier = Modifier.focusRequester(focusRequester),
        expanded = true,
        onExpandedChange = { isExpanded ->
            expanded.value = isExpanded
        }
    ) {
        TextInputField(
            readOnly = true,
            enabled = false,
            placeholder = placeholder,
            initialValue = selectedOption?.option.orEmpty(),
            modifier = Modifier.fillMaxWidth().menuAnchor(type = MenuAnchorType.PrimaryNotEditable),
            trailingIcon = {
                IconButton(onClick = {
                    if (enabled) {
                        expanded.value = !expanded.value
                    } else if (disabledMessage.isNotEmpty()) Application.showToast(disabledMessage)
                }) {
                    Image(
                        painter = painterResource(Res.drawable.ic_arrow_drop_down),
                        contentDescription = null
                    )
                }
            }
        )

        ExposedDropdownMenu(
            modifier = modifier,
            expanded = expanded.value,
            onDismissRequest = {
                expanded.value = false
                onDismiss()
            }
        ) {
            options.forEach { (option, id) ->
                DropdownMenuItem(
                    text = {
                        Text(text = option)
                    }, onClick = {
                        val option = DropDownItem(
                            option = option,
                            id = id
                        )
                        onSelect(option)
                        localSelectedOption.value = option
                        expanded.value = false
                    }
                )
            }
        }
    }
}