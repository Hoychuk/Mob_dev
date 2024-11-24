package com.example.lab_5.ui.entry

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun EntryScreen(
    onCalculator1Navigate: () -> Unit,
    onCalculator2Navigate: () -> Unit,
) {
    Column(
        modifier = Modifier.padding(all = 30.dp).fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Лабораторна робота 5"
        )
        Button(
            onClick = { onCalculator1Navigate() },
        ) {
            Text(
                text = "Калькулятор 1"
            )
        }
        Button(
            onClick = onCalculator2Navigate

        ) {
            Text(
                text = "Калькулятор 2"
            )
        }
    }
}