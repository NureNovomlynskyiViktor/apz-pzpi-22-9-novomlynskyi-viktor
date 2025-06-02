package com.example.artguardmobile.data.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.artguardmobile.data.network.RetrofitInstance
import com.example.artguardmobile.data.storage.TokenStorage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AlertViewModel : ViewModel() {

    private val _alerts = MutableStateFlow<List<Alert>>(emptyList())
    val alerts: StateFlow<List<Alert>> = _alerts

    fun loadAlerts() {
        viewModelScope.launch {
            try {
                val token = TokenStorage.getToken()
                if (token != null) {
                    println("📦 TOKEN => Bearer $token")
                    _alerts.value = RetrofitInstance.alertApi.getAlerts("Bearer $token")
                }
            } catch (e: Exception) {
                println("❌ Помилка завантаження сповіщень: ${e.message}")
            }
        }
    }

    fun markViewed(id: Int) {
        viewModelScope.launch {
            try {
                val token = TokenStorage.getToken()
                if (token != null) {
                    val response = RetrofitInstance.alertApi.markAlertViewed(id, "Bearer $token")
                    if (response.isSuccessful) {
                        loadAlerts()
                    }
                }
            } catch (e: Exception) {
                println("❌ Не вдалося позначити як переглянуте: ${e.message}")
            }
        }
    }
}

