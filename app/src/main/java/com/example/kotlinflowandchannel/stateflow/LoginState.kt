package com.example.kotlinflowandchannel.stateflow

sealed class LoginState {

    object Idle : LoginState()

    object Loading : LoginState()

    data class Success(
        val message: String
    ) : LoginState()

    data class Error(
        val message: String
    ) : LoginState()
}