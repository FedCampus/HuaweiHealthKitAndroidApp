package com.fedcampus.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Done
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.fedcampus.R
import com.fedcampus.ui.theme.LightBlue
import com.fedcampus.ui.theme.White

/**
 * TODO The @param options is reserved for view-model.
 */
@Composable
fun ReportScreen(
  options: Int,
  onExerciseButtonClicked: (Int) -> Unit,
  onHealthButtonClicked: (Int) -> Unit
) {
  Column(
    modifier = Modifier
      .fillMaxSize()
      .background(LightBlue),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Spacer(modifier = Modifier.height(16.dp))
    Text(
      text = stringResource(id = R.string.report),
      style = MaterialTheme.typography.h4,
      color = White,
      modifier = Modifier.align(Alignment.CenterHorizontally)
    )
    Spacer(modifier = Modifier.height(40.dp))
    Row(
      modifier = Modifier.padding(24.dp),
      horizontalArrangement = Arrangement.spacedBy(30.dp)
    ) {
      Button(onClick = {onExerciseButtonClicked(101)}) {
        Column() {
          Icon(Icons.Sharp.Done, contentDescription = null)
          Text(text = stringResource(id = R.string.exercise_report), color = White)
        }
      }
      Button(onClick = {onHealthButtonClicked(110)}) {
        Column() {
          Icon(Icons.Sharp.Done, contentDescription = null)
          Text(text = stringResource(id = R.string.health_report), color = White)
        }
      }
    }
  }
}
