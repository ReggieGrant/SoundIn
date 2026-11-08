package com.example.soundin.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.soundin.ui.navigation.SoundinRoutes
import com.example.soundin.ui.componets.BottomNavigationBar
import com.example.soundin.ui.models.Playlist
import com.example.soundin.viewmodels.UserSessionViewModel

@Composable
fun MainScreen(
    sessionViewModel: UserSessionViewModel,
    onLogout: () -> Unit,
    onNavigationToPlaylistDetail: (Playlist) -> Unit
) {
    val navController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                currentRoute = currentRoute,
                onNavigate = { route ->
                    navController.navigate(route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = SoundinRoutes.LIBRARY,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(SoundinRoutes.LIBRARY) {
                LibraryScreen(
                    onNavigateToPlaylistDetail = onNavigationToPlaylistDetail
                )
            }
            composable(SoundinRoutes.SEARCH) { SearchScreen() }
            composable(SoundinRoutes.PROFILE) {
                ProfileScreen(
                    sessionViewModel = sessionViewModel,
                    onLogout = onLogout
                )
            }

        }
    }
}
