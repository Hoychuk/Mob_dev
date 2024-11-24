package com.example.lab_5.utils

fun validateInputs(vararg values: String): Boolean {
    return values.all { it.toDoubleOrNull() != null }
}