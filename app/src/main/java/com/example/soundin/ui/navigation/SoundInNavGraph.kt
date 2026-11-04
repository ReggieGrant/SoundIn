package com.example.soundin.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.soundin.ui.screens.LoginScreen
import com.example.soundin.ui.screens.MainScreen
import com.example.soundin.ui.screens.RegisterScreen
import com.example.soundin.viewmodels.UserSessionViewModel

@Composable
fun SoundInNavGraph(
    navController: NavHostController,
    sessionViewModel: UserSessionViewModel
) {
    // NavHost is a container that holds the screens
    // NavHost takes a navController and a startDestination
    NavHost(
        navController = navController,
        startDestination = SoundinRoutes.LOGIN
    ) {
        composable(SoundinRoutes.LOGIN) {
            LoginScreen(
                sessionViewModel = sessionViewModel,
                onNavigateToRegister = {
                    navController.navigate(SoundinRoutes.REGISTER)
                },
                onLoginSuccess = {
                    navController.navigate(SoundinRoutes.MAIN)
                }
            )
        }
        composable(SoundinRoutes.REGISTER) {
            RegisterScreen(
                onNavigateToLogin = {
                    navController.navigate(SoundinRoutes.LOGIN) {
                        popUpTo(SoundinRoutes.LOGIN) { inclusive = true }
                    }
                }
            )
        }
        composable(SoundinRoutes.MAIN) {
            MainScreen(
                sessionViewModel = sessionViewModel,
                onLogout = {
                    sessionViewModel.logout()
                    navController.navigate(SoundinRoutes.LOGIN) {
                        popUpTo(SoundinRoutes.MAIN) { inclusive = true }
                    }
                },
                onNavigationToPlaylistDetail = { playlist ->
                    navController.navigate("playlistDetail/${playlist.id}")
                }
            )
        }
    }
}
