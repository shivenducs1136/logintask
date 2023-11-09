package com.bitwisor.loginscreen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bitwisor.loginscreen.screen.LoginScreen
import com.bitwisor.loginscreen.screen.LoginViewModel
import com.bitwisor.loginscreen.ui.theme.LoginScreenTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginScreenTheme {
                LoginScreen(Modifier.padding(
                    horizontal = 22.dp
                ).fillMaxSize(),
                    LoginViewModel()
                )
            }
        }
    }
}
