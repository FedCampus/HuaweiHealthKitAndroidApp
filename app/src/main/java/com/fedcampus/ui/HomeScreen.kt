package com.fedcampus.ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * TODO The @param options is reserved for view-model.
 */
@Composable
fun HomeScreen(options: Int,
               onNextButtonClicked: (Int) -> Unit,
               modifier: Modifier = Modifier,) {
  Text(text = "Home Screen")
}