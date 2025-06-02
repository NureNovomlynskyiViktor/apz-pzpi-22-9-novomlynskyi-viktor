package com.example.artguardmobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.navigation.compose.rememberNavController
import com.example.artguardmobile.data.storage.TokenStorage
import com.example.artguardmobile.data.storage.UserStorage
import com.example.artguardmobile.ui.AppNavigation
import com.example.artguardmobile.ui.theme.ArtGuardMobileTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        TokenStorage.init(applicationContext)
        UserStorage.init(applicationContext)

        setContent {
            ArtGuardMobileTheme {
                var startDestination by remember { mutableStateOf("login") }

                LaunchedEffect(Unit) {
                    val token = TokenStorage.getToken()
                    if (!token.isNullOrBlank()) {
                        startDestination = "dashboard"
                    }
                }

                val navController = rememberNavController()
                AppNavigation(navController = navController, startDestination = startDestination)
            }
        }
    }
}



