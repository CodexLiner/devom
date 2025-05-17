package com.devom.app.models

import org.jetbrains.compose.resources.DrawableResource

data class BottomNavItem(
    val label: String,
    val icon: DrawableResource,
    val isSelected: Boolean
)
