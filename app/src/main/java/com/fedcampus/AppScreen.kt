package com.fedcampus

import com.fedcampus.R
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.fedcampus.ui.AppViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.fedcampus.ui.NextScreen
import com.fedcampus.ui.SplashScreen




enum class AppScreen(@StringRes val title: Int) {
  Start(title = R.string.splash_screen),
  Next(title = R.string.app_name),
}

@Composable
fun AppBar(
  currentScreen: AppScreen,
  canNavigateBack: Boolean,
  navigateUp: () -> Unit,
  modifier: Modifier = Modifier,
) {
  TopAppBar(
    title = { Text(stringResource(currentScreen.title)) },
    modifier = modifier,
    navigationIcon = {
      if (canNavigateBack) {
        IconButton(onClick = navigateUp) {
          Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = stringResource(R.string.back_button)
          )
        }
      }
    }
  )
}

@Composable
fun App(
  modifier: Modifier = Modifier,
  viewModel: AppViewModel = viewModel(),
  navController: NavHostController = rememberNavController(),
) {
  // Get current back stack entry
  val backStackEntry by navController.currentBackStackEntryAsState()
  // Get the name of the current screen
  val currentScreen = AppScreen.valueOf(
    backStackEntry?.destination?.route ?: AppScreen.Start.name
  )
  Scaffold(
    topBar = {
      AppBar(
        currentScreen = currentScreen,
        canNavigateBack = navController.previousBackStackEntry != null,
        navigateUp = { navController.navigateUp() }
      )
    },
//    bottomBar = {}
  ) {innerPadding ->
    val uiState by viewModel.uiState.collectAsState()
    NavHost(
      navController = navController,
      startDestination = AppScreen.Start.name,
      modifier = modifier.padding(innerPadding)
    ) {
      composable(route = AppScreen.Start.name) {
        SplashScreen(
          options = 1,
          onNextButtonClicked = {
//            viewModel.setQuantity(it)
            navController.navigate(AppScreen.Next.name)
          }
        )
      }
      composable(route = AppScreen.Next.name) {
        NextScreen(
//          options = 1,
//          onNextButtonClicked = {
//            viewModel.setQuantity(it)
//            navController.navigate(AppScreen.Next.name)
//          }
        )
      }
    }

  }
}
