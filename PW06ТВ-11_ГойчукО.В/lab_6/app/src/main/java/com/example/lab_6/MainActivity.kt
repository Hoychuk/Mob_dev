package com.example.lab_6

import com.example.lab_6.ui.theme.Lab_6Theme
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab_6Theme {
                Calculator()
            }
        }
    }
}
