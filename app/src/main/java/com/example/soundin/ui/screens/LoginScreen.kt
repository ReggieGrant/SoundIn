package com.example.soundin.ui.screens

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope

@Composable
fun LoginScreen(
    onNavigateToRegister: () -> Unit
){
    // Snackbar state -- lives here
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
}