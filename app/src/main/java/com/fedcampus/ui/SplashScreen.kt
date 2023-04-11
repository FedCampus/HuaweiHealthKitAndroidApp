package com.fedcampus.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fedcampus.R


@Composable
fun SplashScreen(
  options: Int,
  onNextButtonClicked: (Int) -> Unit,
  modifier: Modifier = Modifier,
) {
  var emailInput by remember { mutableStateOf("") }
  val focusManager = LocalFocusManager.current


  Column(
    modifier = modifier
      .padding(16.dp)
      .fillMaxWidth(),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.spacedBy(8.dp)
  ) {
    Spacer(modifier = Modifier.height(16.dp))
    Text(text = stringResource(R.string.app_alias), style = MaterialTheme.typography.h4)
    Spacer(modifier = Modifier.height(16.dp))
    EditField(label = R.string.email,
      keyboardOptions = KeyboardOptions.Default.copy(
        keyboardType = KeyboardType.Text, imeAction = ImeAction.Next
      ),
      keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) }),
      value = emailInput,
      onValueChanged = { emailInput = it })
    EditField(label = R.string.password,
      keyboardOptions = KeyboardOptions.Default.copy(
        keyboardType = KeyboardType.Password, imeAction = ImeAction.Next
      ),
      keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) }),
      value = emailInput,
      onValueChanged = { emailInput = it })
    Spacer(modifier = Modifier.height(16.dp))
    LoginButton(labelResourceId = R.string.login, onClick = {onNextButtonClicked(0) /*TODO*/
    })
  }
}

@Composable
fun EditField(
  @StringRes label: Int,
  keyboardOptions: KeyboardOptions,
  keyboardActions: KeyboardActions,
  value: String,
  onValueChanged: (String) -> Unit,
  modifier: Modifier = Modifier,
) {
  TextField(
    value = value,
    singleLine = true,
    modifier = modifier.fillMaxWidth(),
    onValueChange = onValueChanged,
    label = { Text(stringResource(label)) },
    keyboardOptions = keyboardOptions,
    keyboardActions = keyboardActions
  )
}

@Composable
fun LoginButton(
  @StringRes labelResourceId: Int,
  onClick: () -> Unit,
  modifier: Modifier = Modifier,
) {
  Button(
    onClick = onClick, modifier = modifier.widthIn(min = 250.dp)
  ) {
    Text(stringResource(labelResourceId))
  }
}

@Preview
@Composable
fun Preview() {
  SplashScreen(options = 0, onNextButtonClicked = {})
}