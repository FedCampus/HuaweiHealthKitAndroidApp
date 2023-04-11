package com.fedcampus.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * TODO The @param options is reserved for view-model.
 */
@Composable
fun PostScreen(options: Int,
               onNextButtonClicked: (Int) -> Unit,
               modifier: Modifier = Modifier,) {
  Column() {
    Text(text = "PostScreen")


    Text(text = "PostScreen")
  }
}

