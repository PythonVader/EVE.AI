package com.vericlose.eveai.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.vericlose.eveai.ui.navigation.BottomNavigationItem
import com.vericlose.eveai.ui.navigation.Screens

@Composable
fun BottomNavigationBar(navController: NavHostController,
                        restartApp: (String) -> Unit) {
    var navigationSelectedItem by rememberSaveable {
        mutableIntStateOf(0)
    }
    var isPremiumInfoClicked by rememberSaveable {
        mutableStateOf(false)
    }

    Scaffold(modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar(
                modifier = Modifier
                    .height(65.dp)
                    .clip(
                        RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)
                    ),
                containerColor = MaterialTheme.colorScheme.secondary) {
                BottomNavigationItem().bottomNavigationItems()
                    .forEachIndexed { index, navigationItem ->
                        NavigationBarItem(selected = index == navigationSelectedItem,
                            icon = {
                                Icon(
                                    painter = painterResource(id = navigationItem.icon),
                                    contentDescription = navigationItem.route,
                                    modifier = Modifier
                                        .size(30.dp)
                                        .clipToBounds()
                                )
                            },
                            onClick = {
                                navigationSelectedItem = index
                                navController.navigate(navigationItem.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            })
                    }
            }
        }) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screens.Profile.route,
            modifier = Modifier.padding(paddingValues = innerPadding)
        ) {
            composable(route = Screens.Profile.route){

            }
            composable(route = Screens.Match.route) {

            }
            composable(route = Screens.Chat.route) {

            }
        }
    }
}