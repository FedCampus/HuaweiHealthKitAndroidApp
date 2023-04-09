package com.huaweihealthkitandroidapp.ui

import androidx.lifecycle.ViewModel
import com.huaweihealthkitandroidapp.data.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AppViewModel: ViewModel() {
  private val _uiState = MutableStateFlow(UiState())
  val uiState: StateFlow<UiState> = _uiState.asStateFlow()

  fun setQuantity(numberCupcakes: Int) {
    _uiState.update { currentState ->
      currentState.copy(
        quantity = numberCupcakes
      )
    }
  }
}