package com.example.artguardmobile.data.network

import com.example.artguardmobile.data.model.User
import retrofit2.http.GET
import retrofit2.http.Header

interface UserService {
    @GET("/api/me")
    suspend fun getCurrentUser(
        @Header("Authorization") token: String
    ): User
}