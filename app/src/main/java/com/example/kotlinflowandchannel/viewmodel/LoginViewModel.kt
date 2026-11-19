package com.example.stateflowdemo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinflowandchannel.stateflow.data.LoginRepository
import com.example.kotlinflowandchannel.stateflow.ui.LoginUiState

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val repository = LoginRepository()

    // 1. Mutable StateFlow
    private val _uiState =
        MutableStateFlow(LoginUiState())

    // 2. Expose immutable StateFlow
    val uiState: StateFlow<LoginUiState> =
        _uiState.asStateFlow()

    // 3. Update username
    fun updateUsername(username: String) {

        _uiState.value = _uiState.value.copy(
            username = username,
            errorMessage = null
        )
    }

    // 4. Update password
    fun updatePassword(password: String) {

        _uiState.value = _uiState.value.copy(
            password = password,
            errorMessage = null
        )
    }

    // 5. Login operation
    fun login() {

        viewModelScope.launch {

            val currentState = _uiState.value

            // Update UI to loading
            _uiState.value = currentState.copy(
                isLoading = true,
                errorMessage = null,
                isLoginSuccessful = false
            )

            try {

                val result = repository.login(
                    currentState.username,
                    currentState.password
                )

                result.fold(

                    onSuccess = {

                        _uiState.value =
                            _uiState.value.copy(
                                isLoading = false,
                                isLoginSuccessful = true
                            )
                    },

                    onFailure = { exception ->

                        _uiState.value =
                            _uiState.value.copy(
                                isLoading = false,
                                errorMessage =
                                    exception.message
                            )
                    }
                )

            } catch (exception: Exception) {

                _uiState.value =
                    _uiState.value.copy(
                        isLoading = false,
                        errorMessage = exception.message
                    )
            }
        }
    }
}