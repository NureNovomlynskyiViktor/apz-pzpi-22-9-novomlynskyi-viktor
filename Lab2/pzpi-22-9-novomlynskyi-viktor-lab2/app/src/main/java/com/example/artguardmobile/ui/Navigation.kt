package com.example.artguardmobile.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.artguardmobile.data.model.DashboardViewModel
import com.example.artguardmobile.data.storage.TokenStorage
import com.example.artguardmobile.data.storage.UserStorage
import com.example.artguardmobile.ui.alerts.AlertListScreen
import com.example.artguardmobile.ui.dashboard.DashboardScreen
import com.example.artguardmobile.ui.login.LoginScreen
import com.example.artguardmobile.ui.login.RegisterScreen
import com.example.artguardmobile.ui.sensors.SensorListScreen

@Composable
fun AppNavigation(navController: NavHostController, startDestination: String = "login") {
    NavHost(navController = navController, startDestination = startDestination) {
        composable("login") { LoginScreen(navController) }
        composable("register") { RegisterScreen(navController) }

        composable("dashboard") {
            val dashboardViewModel: DashboardViewModel = viewModel()
            val user = UserStorage.getUser()

            DashboardScreen(
                user = user,
                objects = dashboardViewModel.objects.value,
                onLogout = {
                    TokenStorage.clearToken()
                    UserStorage.clearUser()
                    navController.navigate("login") { popUpTo("dashboard") { inclusive = true } }
                },
                onNavigateToSensors = { navController.navigate("sensors") },
                onNavigateToAlerts = { navController.navigate("alerts") }
            )
        }

        composable("sensors") { SensorListScreen() }
        composable("alerts") { AlertListScreen() }
    }
}





