package com.example.artguardmobile.ui.alerts

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.artguardmobile.data.model.Alert
import com.example.artguardmobile.data.model.AlertViewModel

@Composable
fun AlertListScreen(viewModel: AlertViewModel = viewModel()) {
    val alerts by viewModel.alerts.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadAlerts()
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        Text("Сповіщення", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(alerts) { alert ->
                AlertItem(alert)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun AlertItem(alert: Alert, viewModel: AlertViewModel = viewModel()) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = if (alert.viewed) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.errorContainer
        )
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text("Тип: ${alert.alertType}", style = MaterialTheme.typography.titleSmall)
            Text("Повідомлення: ${alert.alertMessage}")
            Text("Переглянуто: ${if (alert.viewed) "Так" else "Ні"}")

            if (!alert.viewed) {
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = { viewModel.markViewed(alert.id) }) {
                    Text("Позначити як переглянуте")
                }
            }
        }
    }
}


