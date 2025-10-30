package com.example.pasteleria

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.pasteleria.navigation.AppNavigation
import com.example.pasteleria.ui.theme.PasteleriaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PasteleriaTheme {
                AppNavigation()
            }
        }
    }
}
