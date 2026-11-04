package com.example.soundin.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel : ViewModel() {
    // private state -- only this class can modify
    private val _email = MutableStateFlow("")

    // Challenge complete the rest of the state variables
    private val _password = MutableStateFlow("")
    private val _rememberSession = MutableStateFlow(false)
    private val _emailError = MutableStateFlow(false)
    private val _passwordError = MutableStateFlow(false)


    // public state -- the UI can observe these, but not modify them
    val email: StateFlow<String> = _email.asStateFlow()
    val password: StateFlow<String> = _password.asStateFlow()
    val rememberSession: StateFlow<Boolean> = _rememberSession.asStateFlow()
    val emailError: StateFlow<Boolean> = _emailError.asStateFlow()
    val passwordError: StateFlow<Boolean> = _passwordError.asStateFlow()

    // update functions -- only this class can modify the state
    fun onEmailChange(newEmail: String) {
        _email.value = newEmail
        _emailError.value = false
    }

    // Challenge complete the rest of the update functions
    fun onPasswordChange(newPassword: String) {
        _password.value = newPassword
        _passwordError.value = false
    }

    fun onRememberSessionChange(newRememberSession: Boolean) {
        _rememberSession.value = newRememberSession
    }

    // Validation -- called from theUI when the user clicks the login button
    fun validateAndLogin(): Boolean {
        val isEmailValid = _email.value.contains("@") && _email.value.contains(".")
        // challenge complete the rest of the validation
        val isPasswordValid = _password.value.length > 6
        _emailError.value = !isEmailValid
        _passwordError.value = !isPasswordValid
        // return true if both email and password are valid
        return isEmailValid && isPasswordValid
    }
}



