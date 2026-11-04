package com.example.soundin.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class UserSessionViewModel : ViewModel() {
    // private state only visible to the viewmodel
    private val _userName = MutableStateFlow("")
    private val _userEmail = MutableStateFlow("")
    private val _isLoggedIn = MutableStateFlow(false)

    // Challenge add Public state for remember session  exposed to the UI
    val userName: StateFlow<String> = _userName.asStateFlow()
    val userEmail: StateFlow<String> = _userEmail.asStateFlow()
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn.asStateFlow()

    // Called after successful login
    fun login(name: String, email: String) {
        _userName.value = name
        _userEmail.value = email
        _isLoggedIn.value = true
    }
    fun logout() {
        _userName.value = ""
        _userEmail.value = ""
        _isLoggedIn.value = false // Set last -- clear data before the UI reacts
    }
}
