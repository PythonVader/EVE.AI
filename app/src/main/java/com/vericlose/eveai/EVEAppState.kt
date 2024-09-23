package com.vericlose.eveai

import android.content.res.Resources
import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavHostController
import com.vericlose.eveai.snackbar.SnackbarManager
import com.vericlose.eveai.snackbar.SnackbarMessage.Companion.toMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

class EVEAppState(val navController: NavHostController,
                  private val snackBarManager: SnackbarManager,
                  private val snackBarHostState: SnackbarHostState,
                  private val resources: Resources,
                  coroutineScope: CoroutineScope
) {

    init {
        coroutineScope.launch {
            snackBarManager.snackbarMessages.filterNotNull().collect { snackbarMessage ->
                val text = snackbarMessage.toMessage(resources)
                snackBarHostState.showSnackbar(text)
                snackBarManager.clearSnackbarState()
            }
        }
    }

    fun popUp() {
        navController.popBackStack()
    }

    fun navigate(route: String) {
        navController.navigate(route) { launchSingleTop = true }
    }

    fun navigateAndPopUp(route: String, popUp: String) {
        navController.navigate(route) {
            launchSingleTop = true
            popUpTo(popUp) { inclusive = true }
        }
    }

    fun clearAndNavigate(route: String) {
        navController.navigate(route) {
            launchSingleTop = true
            popUpTo(0) { inclusive = true }
        }
    }

}
