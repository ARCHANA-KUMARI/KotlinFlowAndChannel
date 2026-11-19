package com.example.stateflowdemo.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.stateflowdemo.viewmodel.LoginViewModel

@Composable
fun LoginScreen(
    viewModel: LoginViewModel
) {

    // Collect StateFlow
    val uiState by viewModel.uiState
        .collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Login Application",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = uiState.username,
            onValueChange = {
                viewModel.updateUsername(it)
            },
            label = {
                Text("Username")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = uiState.password,
            onValueChange = {
                viewModel.updatePassword(it)
            },
            label = {
                Text("Password")
            },
            visualTransformation =
                PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                viewModel.login()
            },
            enabled = !uiState.isLoading,
            modifier = Modifier.fillMaxWidth()
        ) {

            Text("Login")
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Loading UI
        if (uiState.isLoading) {

            CircularProgressIndicator()

            Text("Please wait...")
        }

        // Success UI
        if (uiState.isLoginSuccessful) {

            Text(
                text = "Login Successful!",
                color = MaterialTheme.colorScheme.primary
            )
        }

        // Error UI
        uiState.errorMessage?.let {

            Text(
                text = it,
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}

@PreviewLightDark
@Preview(name = "Large font", fontScale = 1.5f, showBackground = true)
@Composable
private fun LoginScreenPreview() {
    MaterialTheme {
        Surface {
            LoginScreen(viewModel = LoginViewModel())
        }
    }
}
