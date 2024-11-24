package com.example.lab_5.services

import androidx.compose.runtime.MutableState
import com.example.lab_5.ui.calculator1.ReliabilityIndicators

class CalculatorService {
    fun calculateResult1(amountMap: Map<String, MutableState<String>>,
                         dataIndicators: Map<String, ReliabilityIndicators>): List<Double>{
        var wOc = 0.0
        var tVOc = 0.0

        amountMap.forEach { (key, value) ->
            val amount = value.value.toIntOrNull() ?: 0
            val indicator = dataIndicators[key] ?: return@forEach

            if (amount > 0) {
                wOc += amount * indicator.omega
                tVOc += amount * indicator.tV * indicator.omega
            }
        }

        tVOc /= wOc
        val kAOc = (tVOc * wOc) / 8760
        val kPOs = 1.2 * 43 / 8760
        val wDk = 2 * wOc * (kAOc + kPOs)
        return listOf(wOc, tVOc, kAOc, kPOs, wDk, wDk + 0.02)
    }

    fun calculateResult2(formattedZPerA: Double,formattedZPerP: Double,formattedOmega: Double,
                         formattedTV: Double,formattedPm: Double,formattedTm: Double,
                         formattedKP: Double): List<Double>{
        val mWnedA = formattedOmega * formattedTV * formattedPm * formattedTm
        val mWnedP = formattedKP * formattedPm * formattedTm
        val mZper = formattedZPerA * mWnedA + formattedZPerP * mWnedP

        return listOf(mWnedA, mWnedP, mZper)
    }

}