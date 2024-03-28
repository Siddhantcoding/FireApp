package com.example.fireapp.ui.screens.Register

data class RegisterState(
    val email : String = "",
    val password : String = "",
    val username : String = "",
    val confirmPassword : String = "",
    var isLoading : Boolean = false,
    var error : String = "",
    var isLoginSuccess: Boolean = false,

) {

}