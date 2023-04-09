package com.huaweihealthkitandroidapp

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.sharp.ArrowBack
import androidx.compose.material.icons.sharp.ArrowForward
import androidx.compose.material.icons.sharp.Refresh
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.huaweihealthkitandroidapp.ui.AppViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.huaweihealthkitandroidapp.ui.NextNextScreen
import com.huaweihealthkitandroidapp.ui.NextScreen
import com.huaweihealthkitandroidapp.ui.SplashScreen


enum class AppScreen(@StringRes val title: Int) {
  Start(title = R.string.splash_screen),
  Next(title = R.string.app_name),
  NextNext(title = R.string.password),
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
fun BottomBar(
  currentScreen: AppScreen,
  canNavigateBack: Boolean,
  navigateUp: () -> Unit,
  navigateMiddle: () -> Unit,
  modifier: Modifier = Modifier,
) {
  BottomNavigation(
//    title = { Text(stringResource(currentScreen.title)) },
    modifier = modifier,
  ) {
    BottomNavigationItem(
      selected = true,
      onClick = navigateUp,
      icon = {
        Icon(Icons.Sharp.ArrowBack, contentDescription = null)
      })
    BottomNavigationItem(
      selected = false,
      onClick = navigateMiddle,
      icon = { Icon(Icons.Sharp.Refresh, contentDescription = null) })
    BottomNavigationItem(
      selected = false,
      onClick = { /*TODO*/ },
      icon = { Icon(Icons.Sharp.ArrowForward, contentDescription = null) })

  }
}

@Composable
fun App(
  modifier: Modifier = Modifier,
  viewModel: AppViewModel = viewModel(),
//  navController: NavHostController = rememberNavController(),
) {
  // Get current back stack entry
  val navController: NavHostController =
    rememberNavController() //same effect as pass it in constructor, any changes in a remembered field causes recomposition, whether it is declared inside of in the constructor
  val backStackEntry by navController.currentBackStackEntryAsState()
  // Get the name of the current screen
  val currentScreen = AppScreen.valueOf(
    backStackEntry?.destination?.route ?: AppScreen.Start.name
  )

  var showBottomBar by remember { mutableStateOf(false) }

  Scaffold(
    topBar = {
      AppBar(
        currentScreen = currentScreen,
        canNavigateBack = navController.previousBackStackEntry != null,
        navigateUp = { navController.navigateUp() }
      )
    },
    bottomBar = {
      if (showBottomBar)
        BottomBar(
          currentScreen = currentScreen,
          canNavigateBack = navController.previousBackStackEntry != null,
          navigateUp = { navController.navigateUp() },
          navigateMiddle = { navController.navigate(AppScreen.NextNext.name) }
        )
    }
  ) { innerPadding ->
    val uiState by viewModel.uiState.collectAsState()
    NavHost(
      navController = navController,
      startDestination = AppScreen.Start.name,
      modifier = modifier.padding(innerPadding)
    ) {
      composable(route = AppScreen.Start.name) {
        showBottomBar = false
        SplashScreen(
          options = 1,
          onNextButtonClicked = {
//            viewModel.setQuantity(it)
            navController.navigate(AppScreen.Next.name)
          }
        )
      }
      composable(route = AppScreen.Next.name) {
        showBottomBar = true
        NextScreen(
//          options = 1,
//          onNextButtonClicked = {
//            viewModel.setQuantity(it)
//            navController.navigate(AppScreen.Next.name)
//          }
        )
      }
      composable(route = AppScreen.NextNext.name) {
        showBottomBar = true
        NextNextScreen(
        )
      }
    }

  }
}
