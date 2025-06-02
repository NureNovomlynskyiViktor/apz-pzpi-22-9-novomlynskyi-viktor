package com.example.artguardmobile.ui.sensors

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.artguardmobile.data.model.Sensor
import com.example.artguardmobile.data.model.SensorViewModel

@Composable
fun SensorListScreen() {
    val viewModel: SensorViewModel = viewModel()
    val sensors by viewModel.sensors.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchSensors()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Сенсори", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(12.dp))

        when {
            isLoading -> {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            error != null -> {
                Text("Помилка: $error", color = MaterialTheme.colorScheme.error)
            }

            else -> {
                LazyColumn {
                    items(sensors) { sensor ->
                        SensorCard(sensor)
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun SensorCard(sensor: Sensor) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Тип: ${sensor.type}", style = MaterialTheme.typography.titleMedium)
            Text("Одиниця: ${sensor.unit}")
            Text("Ідентифікатор: ${sensor.identifier}")
            Text("Object ID: ${sensor.objectId}")
        }
    }
}


