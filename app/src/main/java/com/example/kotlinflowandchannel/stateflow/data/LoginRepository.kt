package com.example.kotlinflowandchannel.stateflow.data

import kotlinx.coroutines.delay

class LoginRepository {

    suspend fun login(
        username: String,
        password: String
    ): Result<String> {

        // Simulate API network delay
        delay(2000)

        return if (
            username == "admin" &&
            password == "1234"
        ) {
            Result.success("Welcome! Login successful.")
        } else {
            Result.failure(
                Exception("Invalid username or password")
            )
        }
    }
}