package com.fedcampus

import PostScreen
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.sharp.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.fedcampus.ui.*


enum class AppScreen(@StringRes val title: Int) {
  SplashScreen(title = R.string.splash_screen),
  Report(title = R.string.report),
  Post(title = R.string.post),
  Home(title = R.string.home),
  HealthReport(title = R.string.health_report),
  ExerciseReport(title = R.string.exercise_report)
}

@Composable
fun AppBar(
  currentScreen: AppScreen,
  canNavigateBack: Boolean,
  navigateUp: () -> Unit,
  modifier: Modifier = Modifier,
) {
  TopAppBar(title = { Text(stringResource(currentScreen.title)) },
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
    })
}

@Composable
fun BottomBar(
  navigateFirst: () -> Unit,
  navigateSecond: () -> Unit,
  navigateThird: () -> Unit,
  modifier: Modifier = Modifier,
) {
  BottomNavigation(
    modifier = modifier,
  ) {
    BottomNavigationItem(selected = true, onClick = navigateFirst, icon = {
      Icon(Icons.Sharp.AccountBox, contentDescription = null)
    }, label = { Text(text = stringResource(id = R.string.report)) })
    BottomNavigationItem(selected = false,
      onClick = navigateSecond,
      icon = { Icon(Icons.Sharp.Add, contentDescription = null) },
      label = { Text(text = stringResource(id = R.string.post)) })
    BottomNavigationItem(selected = false,
      onClick = navigateThird,
      icon = { Icon(Icons.Sharp.Home, contentDescription = null) },
      label = { Text(text = stringResource(id = R.string.home)) })

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
    backStackEntry?.destination?.route ?: AppScreen.SplashScreen.name
  )

  var showBottomBar by remember { mutableStateOf(false) }

  Scaffold(topBar = {
    AppBar(currentScreen = currentScreen,
      canNavigateBack = navController.previousBackStackEntry != null,
      navigateUp = { navController.navigateUp() })
  }, bottomBar = {
    if (showBottomBar) BottomBar(
      navigateFirst = { navController.navigate(AppScreen.Report.name) },
      navigateSecond = { navController.navigate(AppScreen.Post.name) },
      navigateThird = { navController.navigate(AppScreen.Home.name) })
  }) { innerPadding ->
    val uiState by viewModel.uiState.collectAsState()
    NavHost(
      navController = navController,
      startDestination = AppScreen.SplashScreen.name,
      modifier = modifier.padding(innerPadding)
    ) {
      composable(route = AppScreen.SplashScreen.name) {
        showBottomBar = false
        SplashScreen(options = 1, onNextButtonClicked = {
          navController.navigate(AppScreen.Report.name)
        })
      }
      composable(route = AppScreen.Report.name) {
        showBottomBar = true
        ReportScreen(onExerciseButtonClicked = {
          navController.navigate(AppScreen.Post.name)
          println(it)
        }, onHealthButtonClicked = {
          navController.navigate(AppScreen.Home.name)
        }
        )
      }
      composable(route = AppScreen.Post.name) {
        showBottomBar = true
        PostScreen(
        )
      }
      composable(route = AppScreen.Home.name) {
        showBottomBar = true
        HomeScreen(
        )
      }

      composable(route = AppScreen.ExerciseReport.name) {
        showBottomBar = true
        HomeScreen(
        )
      }

      composable(route = AppScreen.HealthReport.name) {
        showBottomBar = true
        HomeScreen(
        )
      }

    }

  }
}
