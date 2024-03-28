package com.example.fireapp.ui.screens.Register

import androidx.lifecycle.ViewModel
import com.example.fireapp.service.AuthService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class RegisterViewModel() : ViewModel(){
    private val _state = MutableStateFlow(RegisterState())
    val state = _state.asStateFlow()
    private val authService = AuthService()

    fun onEvent(event: RegisterEvent){
        when(event){
            RegisterEvent.OnNavigateToLogin -> TODO()
            RegisterEvent.OnSaveUser ->{
                authService.register(state.value)

            }
            is RegisterEvent.SetEmail ->{
                _state.update { it.copy(email = event.email) }
            }

            is RegisterEvent.SetPassword ->{
                _state.update { it.copy(email = event.password) }
            }

            is RegisterEvent.SetConfirmPassword ->{
                _state.update { it.copy(email = event.confirmpassword) }
            }

            is RegisterEvent.SetUsername ->{
                _state.update { it.copy(email = event.username) }
            }

        }
    }
}