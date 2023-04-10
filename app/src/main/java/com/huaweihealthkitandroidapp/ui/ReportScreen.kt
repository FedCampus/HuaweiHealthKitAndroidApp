package com.huaweihealthkitandroidapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.AccountBox
import androidx.compose.material.icons.sharp.Done
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.huaweihealthkitandroidapp.R
import com.huaweihealthkitandroidapp.ui.theme.DarkBlue
import com.huaweihealthkitandroidapp.ui.theme.LightBlue
import com.huaweihealthkitandroidapp.ui.theme.White


@Composable
fun ReportScreen(
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
