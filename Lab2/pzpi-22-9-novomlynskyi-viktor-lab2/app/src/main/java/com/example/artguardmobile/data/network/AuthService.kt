package com.example.artguardmobile.data.network

import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Response

data class LoginRequest(val email: String, val password: String)
data class LoginResponse(val token: String)
data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String
)

data class RegisterResponse(val token: String)

interface AuthService {
    @POST("/api/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @POST("/api/register")
    suspend fun register(@Body request: RegisterRequest): Response<RegisterResponse>
}

