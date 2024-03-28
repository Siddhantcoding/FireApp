package com.example.fireapp.ui.screens.Register

sealed class RegisterEvent {
    data class SetUsername(val username : String) : RegisterEvent()
    data class SetEmail(val email: String) : RegisterEvent()
    data class SetPassword(val password: String) : RegisterEvent()
    data class SetConfirmPassword(val confirmpassword: String) : RegisterEvent()
    data object OnSaveUser : RegisterEvent()
    data object OnNavigateToLogin : RegisterEvent()
}