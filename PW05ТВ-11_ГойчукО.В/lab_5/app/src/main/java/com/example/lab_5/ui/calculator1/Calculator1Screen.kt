package com.example.lab_5.ui.calculator1

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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lab_5.services.CalculatorService

data class ReliabilityIndicators(
    val omega: Double,
    val tV: Double,
    val mu: Double,
    val tP: Double
)

@Composable
fun Calculator1Screen(
    goBack: () -> Unit,
    calculatorService: CalculatorService
) {

    val dataIndicators = mapOf(
        "ПЛ-110 кВ" to ReliabilityIndicators(0.007, 10.0, 0.167, 35.0),
        "ПЛ-35 кВ" to ReliabilityIndicators(0.02, 8.0, 0.167, 35.0),
        "ПЛ-10 кВ" to ReliabilityIndicators(0.02, 10.0, 0.167, 35.0),
        "КЛ-10 кВ (траншея)" to ReliabilityIndicators(0.03, 44.0, 1.0, 9.0),
        "КЛ-10 кВ (кабельний канал)" to ReliabilityIndicators(0.005, 17.5, 1.0, 9.0),
        "T-110 кВ" to ReliabilityIndicators(0.015, 100.0, 1.0, 43.0),
        "T-35 кВ" to ReliabilityIndicators(0.02, 80.0, 1.0, 28.0),
        "T-10 кВ (кабельна мережа 10 кВ)" to ReliabilityIndicators(0.005, 60.0, 0.5, 10.0),
        "T-10 кВ (повітряна мережа 10 кВ)" to ReliabilityIndicators(0.05, 60.0, 0.5, 10.0),
        "B-110 кВ (елегазовий)" to ReliabilityIndicators(0.01, 30.0, 0.1, 30.0),
        "B-10 кВ (малооливний)" to ReliabilityIndicators(0.02, 15.0, 0.33, 15.0),
        "B-10 кВ (вакуумний)" to ReliabilityIndicators(0.01, 15.0, 0.33, 15.0),
        "Збірні шини 10 кВ на 1 приєднання" to ReliabilityIndicators(0.03, 2.0, 0.167, 5.0),
        "АВ-0,38 кВ" to ReliabilityIndicators(0.05, 4.0, 0.33, 10.0),
        "ЕД 6,10 кВ" to ReliabilityIndicators(0.1, 160.0, 0.5, 0.0),
        "ЕД 0,38 кВ" to ReliabilityIndicators(0.1, 50.0, 0.5, 0.0),
    )

    fun createDefaultAmountMap(): Map<String, MutableState<String>> {
        val defaultValues = mapOf(
            "ПЛ-110 кВ" to "10",
            "ПЛ-35 кВ" to "0",
            "ПЛ-10 кВ" to "0",
            "КЛ-10 кВ (траншея)" to "0",
            "КЛ-10 кВ (кабельний канал)" to "0",
            "T-110 кВ" to "1",
            "T-35 кВ" to "0",
            "T-10 кВ (кабельна мережа 10 кВ)" to "0",
            "T-10 кВ (повітряна мережа 10 кВ)" to "0",
            "B-110 кВ (елегазовий)" to "1",
            "B-10 кВ (малооливний)" to "1",
            "B-10 кВ (вакуумний)" to "0",
            "Збірні шини 10 кВ на 1 приєднання" to "6",
            "АВ-0,38 кВ" to "0",
            "ЕД 6,10 кВ" to "0",
            "ЕД 0,38 кВ" to "0"
        )

        return defaultValues.mapValues { mutableStateOf(it.value) }
    }

    val amountMap = remember { createDefaultAmountMap() }

    var wOc by remember { mutableStateOf("") }
    var tVOc by remember { mutableStateOf("") }
    var kAOc by remember { mutableStateOf("") }
    var kPOc by remember { mutableStateOf("") }
    var wDk by remember { mutableStateOf("") }
    var wDc by remember { mutableStateOf("") }

    fun round(num: Double) = "%.5f".format(num)

    fun calculateResults(){
        val result = calculatorService.calculateResult1(amountMap, dataIndicators)

        wOc = result[0].toString()
        tVOc = result[1].toString()
        kAOc = result[2].toString()
        kPOc = result[3].toString()
        wDk = result[4].toString()
        wDc = result[5].toString()
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, top = 24.dp, end = 16.dp, bottom = 16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        amountMap.forEach { (key, state) ->
            TextField(
                value = state.value,
                onValueChange = { state.value = it },
                label = { Text(key) },
                maxLines = 1,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }

        Button(
            onClick = { calculateResults() }
        ) {
            Text("Розрахувати")
        }
        if (wOc.isNotEmpty()) {
            Text(
                """
                    Wос = ${round(wOc.toDouble())} (рік^(-1))
                    tв.ос = ${round(tVOc.toDouble())} (год)
                    kа.ос = ${round(kAOc.toDouble())}
                    kп.ос = ${round(kPOc.toDouble())}
                    Wдк = ${round(wDk.toDouble())} (рік^(-1))
                    Wдс = ${round(wDc.toDouble())} (рік^(-1))
                    """.trimIndent().format()
            )
        }
        Box(
            modifier = Modifier.padding(top = 100.dp)
        ) {
            Button(
                onClick = goBack
            ) {
                Text("Назад")
            }
        }
    }
}
