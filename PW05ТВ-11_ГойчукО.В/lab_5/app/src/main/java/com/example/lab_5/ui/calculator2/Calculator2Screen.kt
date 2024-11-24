package com.example.lab_5.ui.calculator2

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lab_5.services.CalculatorService

@Composable
fun Calculator2Screen(
    goBack: () -> Unit,
    calculatorService: CalculatorService
) {//автозаповнення для спрощення уведення
    var zPerA by remember { mutableStateOf("23.6") }
    var zPerP by remember { mutableStateOf("17.6") }
    var omega by remember { mutableStateOf("0.01") }
    var tV by remember { mutableStateOf("0.045") }
    var Pm by remember { mutableStateOf("5120") }
    var Tm by remember { mutableStateOf("6451") }
    var kP by remember { mutableStateOf("0.004") }

    var mWnedA by remember { mutableStateOf("") }
    var mWnedP by remember { mutableStateOf("") }
    var mZper by remember { mutableStateOf("") }

    //функція заокруглення
    fun round(num: Double) = "%.2f".format(num)

    //виклик функції розранку
    fun calculateResult(){
        val formattedZPerA = zPerA.toDoubleOrNull() ?: 0
        val formattedZPerP = zPerP.toDoubleOrNull() ?: 0
        val formattedOmega = omega.toDoubleOrNull()?: 0
        val formattedTV = tV.toDoubleOrNull()?: 0
        val formattedPm = Pm.toDoubleOrNull()?: 0
        val formattedTm = Tm.toDoubleOrNull()?: 0
        val formattedKP = kP.toDoubleOrNull()?: 0

        val result = calculatorService.calculateResult2(formattedZPerA.toDouble(),
            formattedZPerP.toDouble(),
            formattedOmega.toDouble(),
            formattedTV.toDouble(),
            formattedPm.toDouble(),
            formattedTm.toDouble(),
            formattedKP.toDouble())
        mWnedA = result[0].toString()
        mWnedP = result[1].toString()
        mZper = result[2].toString()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, top = 24.dp, end = 16.dp, bottom = 16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {//текстові поля для уведення
        TextField(
            value = zPerA,
            onValueChange = { zPerA = it },
            label = { Text("zPerA") },
            maxLines = 1,
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = zPerP,
            onValueChange = { zPerP = it },
            label = { Text("zPerP") },
            maxLines = 1,
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = omega,
            onValueChange = { omega = it },
            label = { Text("Omega") },
            maxLines = 1,
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = tV,
            onValueChange = { tV = it },
            label = { Text("tS") },
            maxLines = 1,
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = Pm,
            onValueChange = { Pm = it },
            label = { Text("pM") },
            maxLines = 1,
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = Tm,
            onValueChange = { Tm = it },
            label = { Text("Tm") },
            maxLines = 1,
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = kP,
            onValueChange = { kP = it },
            label = { Text("kP") },
            maxLines = 1,
            modifier = Modifier.fillMaxWidth()
        )
        Button(//кнопка розрахунку
            onClick = { calculateResult()}
        ) {
            Text("Розрахувати")
        }
        if (mWnedA.isNotEmpty() && mWnedP.isNotEmpty() && mZper.isNotEmpty()) {
            Text( """
                        M(Wнед.а): ${round(mWnedA.toDouble())} (кВт * год)
                        M(Wед.п): ${round(mWnedP.toDouble())} (кВт * год)
                        M(Зпер): ${round(mZper.toDouble())} (грн)
                        """.trimIndent().format(mWnedA, mWnedP, mZper)
            )
        }
        Box(//вікно результатів
            modifier = Modifier.padding(top = 100.dp)
        ) {
            Button(//кнопка повернення
                onClick = goBack
            ) {
                Text("Назад")
            }
        }
    }
}