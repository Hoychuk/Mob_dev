package com.example.lab_6

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import kotlin.math.sqrt
import androidx.compose.ui.Modifier
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.tooling.preview.Preview
import kotlin.math.ceil

// Дані для кожного електроприводу (ЕП)
@Stable
data class EquipmentData(
    var name: String = "",
    var efficiencyRating: String = "",
    var loadPowerFactor: String = "",
    var loadVoltage: String = "",
    var quantity: String = "",
    var nominalPower: String = "",
    var usageFactor: String = "",
    var reactivePowerCoefficient: String = "",

    // значення для розрахунку
    var multipliedPower: String = "",
    var current: String = "",
)

// Форма для введення даних про один ЕП
@Composable
fun EquipmentForm(equipmentData: EquipmentData) {
    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        value = equipmentData.name,
        onValueChange = { equipmentData.name = it },
        label = { Text("Назва обладнання") },
        modifier = Modifier
            .fillMaxWidth()
            .clickable { focusManager.clearFocus() }
    )
    OutlinedTextField(
        value = equipmentData.efficiencyRating,
        onValueChange = { equipmentData.efficiencyRating = it },
        label = { Text("ККД обладнання (ηн)") },
        modifier = Modifier
            .fillMaxWidth()
            .clickable { focusManager.clearFocus() }
    )
    OutlinedTextField(
        value = equipmentData.loadPowerFactor,
        onValueChange = { equipmentData.loadPowerFactor = it },
        label = { Text("Коефіцієнт потужності (cos φ)") },
        modifier = Modifier
            .fillMaxWidth()
            .clickable { focusManager.clearFocus() }
    )
    OutlinedTextField(
        value = equipmentData.loadVoltage,
        onValueChange = { equipmentData.loadVoltage = it },
        label = { Text("Напруга (Uн, кВ)") },
        modifier = Modifier
            .fillMaxWidth()
            .clickable { focusManager.clearFocus() }
    )
    OutlinedTextField(
        value = equipmentData.quantity,
        onValueChange = { equipmentData.quantity = it },
        label = { Text("Кількість (шт)") },
        modifier = Modifier
            .fillMaxWidth()
            .clickable { focusManager.clearFocus() }
    )
    OutlinedTextField(
        value = equipmentData.nominalPower,
        onValueChange = { equipmentData.nominalPower = it },
        label = { Text("Номінальна потужність (Рн, кВт)") },
        modifier = Modifier
            .fillMaxWidth()
            .clickable { focusManager.clearFocus() }
    )
    OutlinedTextField(
        value = equipmentData.usageFactor,
        onValueChange = { equipmentData.usageFactor = it },
        label = { Text("Коефіцієнт використання") },
        modifier = Modifier
            .fillMaxWidth()
            .clickable { focusManager.clearFocus() }
    )
    OutlinedTextField(
        value = equipmentData.reactivePowerCoefficient,
        onValueChange = { equipmentData.reactivePowerCoefficient = it },
        label = { Text("tgφ (коефіцієнт реактивної потужності)") },
        modifier = Modifier
            .fillMaxWidth()
            .clickable { focusManager.clearFocus() }
    )
}

// Основний калькулятор для обчислення характеристик ЕП
@Preview
@Composable
fun Calculator() {
    //val focusManager = LocalFocusManager.current
    val scrollState = rememberScrollState()

    // Жорстко заданий список ЕП
    val equipmentList by remember {
        mutableStateOf(
            listOf(
                EquipmentData("Шліфувальний верстат", "0.92", "0.9", "0.38", "4", "20", "0.15", "1.33"),
                EquipmentData("Свердлильний верстат", "0.92", "0.9", "0.38", "2", "14", "0.12", "1"),
                EquipmentData("Фугувальний верстат", "0.92", "0.9", "0.38", "4", "42", "0.15", "1.33"),
                EquipmentData("Циркулярна пила", "0.92", "0.9", "0.38", "1", "36", "0.3", "1.52"),
                EquipmentData("Прес", "0.92", "0.9", "0.38", "1", "20", "0.5", "0.75"),
                EquipmentData("Полірувальний верстат", "0.92", "0.9", "0.38", "1", "40", "0.2", "1"),
                EquipmentData("Фрезерний верстат", "0.92", "0.9", "0.38", "2", "32", "0.2", "1"),
                EquipmentData("Вентилятор", "0.92", "0.9", "0.38", "1", "20", "0.65", "0.75"),
            )
        )
    }
    val Kr by remember { mutableStateOf("1.25") }
    val Kr2 by remember { mutableStateOf("0.7") }

    var kvGroup by remember { mutableStateOf("") }
    var effEpAmount by remember { mutableStateOf("") }
    var totalDepartmentUtilCoef by remember { mutableStateOf("") }
    var effEpDepartmentAmount by remember { mutableStateOf("") }
    var rozrahActNav by remember { mutableStateOf("") }
    var rozrahReactNav by remember { mutableStateOf("") }
    var fullPower by remember { mutableStateOf("") }
    var rozrahGroupStrumShr1 by remember { mutableStateOf("") }
    var rozrahActNavShin by remember { mutableStateOf("") }
    var rozrahReactNavShin by remember { mutableStateOf("") }
    var fullPowerShin by remember { mutableStateOf("") }
    var rozrahGroupStrumShin by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        // Список ЕП
        equipmentList.forEach { equipment ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                EquipmentForm(equipmentData = equipment)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))


        Button(
            onClick = {
                // Основні змінні
                var sumOfNPnKvProduct = 0.0
                var sumOfNPnProduct = 0.0
                var sumOfNPnPnProduct = 0.0

                // Розрахунки для кожного ЕП
                equipmentList.forEach { equipment ->
                    // Кількість ЕП
                    val quantity = equipment.quantity.toDouble()
                    // Номінальна потужність
                    val nominalPower = equipment.nominalPower.toDouble()
                    // Розрахунок множення кількості на потужність
                    equipment.multipliedPower = "${quantity * nominalPower}"
                    // Розрахунок струму
                    val current = equipment.multipliedPower.toDouble() /
                            (sqrt(3.0) * equipment.loadVoltage.toDouble() *
                                    equipment.loadPowerFactor.toDouble() *
                                    equipment.efficiencyRating.toDouble())
                    equipment.current = current.toString()

                    // Сума добутків n * Pn * Kv
                    sumOfNPnKvProduct += equipment.multipliedPower.toDouble() * equipment.usageFactor.toDouble()
                    // Сума добутків n * Pn
                    sumOfNPnProduct += equipment.multipliedPower.toDouble()
                    // Сума квадратів Pn
                    sumOfNPnPnProduct += quantity * nominalPower * nominalPower
                }

                // Групові обчислення
                kvGroup = (sumOfNPnKvProduct / sumOfNPnProduct).toString()
                effEpAmount = (ceil((sumOfNPnProduct * sumOfNPnProduct) / sumOfNPnPnProduct)).toString()

                // Додаткові обчислення
                val KrValue = Kr.toDouble()
                val tan_phi = 1.128
                val Un = 0.38

                val Pp = KrValue * sumOfNPnKvProduct
                val Qp = sumOfNPnKvProduct * tan_phi
                val Sp = sqrt((Pp * Pp) + (Qp * Qp))
                val Ip = Pp / Un

                rozrahActNav = Pp.toString()
                rozrahReactNav = Qp.toString()
                fullPower = Sp.toString()
                rozrahGroupStrumShr1 = Ip.toString()

                // Розрахунок для всього цеху
                val KvDepartment = 752.0 / 2330.0
                val n_e = 2330.0 * 2330.0 / 96399.0

                totalDepartmentUtilCoef = KvDepartment.toString()
                effEpDepartmentAmount = n_e.toString()

                // Обчислення для шин

                val Kr2Value = Kr2.toDouble()
                val PpShin = Kr2Value * 752.0
                val QpShin = Kr2Value * 657.0
                val SpShin = sqrt((PpShin * PpShin) + (QpShin * QpShin))
                val IpShin = PpShin / Un

                rozrahActNavShin = PpShin.toString()
                rozrahReactNavShin = QpShin.toString()
                fullPowerShin = SpShin.toString()
                rozrahGroupStrumShin = IpShin.toString()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Обчислити")
        }

        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Груповий коефіцієнт використання: $kvGroup", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Ефективна кількість ЕП: $effEpAmount", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Розрахункове активне навантаження: $rozrahActNav", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Розрахункове реактивне навантаження: $rozrahReactNav", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Повна потужність: $fullPower", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Розрахунковий груповий струм ШР1: $rozrahGroupStrumShr1", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Коефіцієнт використання цеху в цілому: $totalDepartmentUtilCoef", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Ефективна кількість ЕП цеху в цілому: $effEpDepartmentAmount", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Розрахункове активне навантаження на шинах: $rozrahActNavShin", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Розрахункове реактивне навантаження на шинах: $rozrahReactNavShin", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Повна потужність на шинах: $fullPowerShin", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Розрахунковий груповий струм на шинах: $rozrahGroupStrumShin", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(30.dp))
    }
}