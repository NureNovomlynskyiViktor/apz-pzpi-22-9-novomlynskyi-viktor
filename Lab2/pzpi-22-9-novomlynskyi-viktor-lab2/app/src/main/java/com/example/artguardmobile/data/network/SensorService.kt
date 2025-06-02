package com.example.artguardmobile.data.network

import com.example.artguardmobile.data.model.Sensor
import retrofit2.http.GET
import retrofit2.http.Header

interface SensorService {
    @GET("/api/sensors")
    suspend fun getSensors(
        @Header("Authorization") token: String
    ): List<Sensor>
}

