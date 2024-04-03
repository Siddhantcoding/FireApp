package com.example.fireapp.service

import com.digi.fireapp.ui.screens.login.LoginState
import com.example.fireapp.ui.screens.Register.RegisterState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.userProfileChangeRequest
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow

class AuthService(
   private val auth:FirebaseAuth= Firebase.auth,
) {
    fun login(state: MutableStateFlow<LoginState>){
        auth.signInWithEmailAndPassword(state.email,state.password).addOnFailureListener{
            state.error = it.message ?: "An error Occurred"
            state.isLoading =false
        } .addOnSuccessListener {
            state.isLoading = false
            state.error = ""
            state.isLoginSuccess = true
        }
    }
    fun register(state:RegisterState) {
        auth.createUserWithEmailAndPassword(state.email, state.password)
            .addOnFailureListener {
                state.error = it.message ?: "An error Occurred"
                state.isLoading = false
            }.addOnSuccessListener {
                state.isLoading = false
                state.error = ""
                state.isLoginSuccess = true
                val profileUpdates = userProfileChangeRequest {
                    displayName = state.username
                }
                it.user?.updateProfile(profileUpdates)
            }
    }
    fun isUserLoggedIn():Boolean {
        return auth.currentUser != null
    }}





