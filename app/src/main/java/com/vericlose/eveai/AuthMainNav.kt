package com.vericlose.eveai

import android.content.res.Resources
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vericlose.eveai.snackbar.SnackbarManager
import com.vericlose.eveai.ui.BottomNavigationBar
import com.vericlose.eveai.ui.theme.EVEAITheme
import kotlinx.coroutines.CoroutineScope

@Composable
fun AuthNavigationScreens() {
    EVEAITheme {
        Surface {
            val snackBarHostState = remember{ SnackbarHostState() }
            val appState = rememberAppState(snackBarHostState)
            Scaffold(
                snackbarHost = {
                    SnackbarHost(
                        hostState = snackBarHostState,
                        modifier = Modifier.padding(8.dp),
                        snackbar = {
                            Snackbar(
                                it,
                                contentColor = MaterialTheme.colorScheme.primary
                            )
                        }
                    )
                },
            ) {
                NavHost(
                    navController = appState.navController,
                    startDestination = SPLASH_SCREEN,
                    modifier = Modifier.padding(it)
                ) {
                    atomicGraph(appState)
                }
            }
        }
    }
}

@Composable
fun rememberAppState(
    snackBarHostState: SnackbarHostState,
    navController: NavHostController = rememberNavController(),
    resources: Resources = resources(),
    snackBarManager: SnackbarManager = SnackbarManager,
    coroutineScope: CoroutineScope = rememberCoroutineScope()
) =
    remember(navController) {
        EVEAppState(navController, snackBarManager,snackBarHostState, resources, coroutineScope)
    }

@Composable
@ReadOnlyComposable
fun resources(): Resources {
    LocalConfiguration.current
    return LocalContext.current.resources
}


fun NavGraphBuilder.atomicGraph(appState: EVEAppState) {
    composable(MAIN_SCREEN) {
//        openScreen = { route -> appState.navigate(route) }
        BottomNavigationBar(navController = rememberNavController(),
            restartApp = { route -> appState.clearAndNavigate(route) })
    }

//    composable(FIRST_TIME_USER_FLOW){
//        FirstMainScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
//    }
//
//    composable(LOGIN_SCREEN) {
//        LoginMain(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
//    }
//
//    composable(SPLASH_SCREEN) {
//        SplashScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
//    }
}