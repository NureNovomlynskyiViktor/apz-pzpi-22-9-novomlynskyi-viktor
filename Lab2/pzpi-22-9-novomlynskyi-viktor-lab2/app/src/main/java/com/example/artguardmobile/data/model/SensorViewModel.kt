package com.example.artguardmobile.data.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.artguardmobile.data.network.RetrofitInstance
import com.example.artguardmobile.data.storage.TokenStorage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SensorViewModel : ViewModel() {

    private val _sensors = MutableStateFlow<List<Sensor>>(emptyList())
    val sensors: StateFlow<List<Sensor>> = _sensors

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun fetchSensors() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val token = TokenStorage.getToken()
                if (token != null) {
                    _sensors.value = RetrofitInstance.sensorApi.getSensors("Bearer $token")
                } else {
                    _error.value = "Немає токена авторизації"
                }
            } catch (e: Exception) {
                _error.value = "Не вдалося завантажити дані: ${e.localizedMessage}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}

