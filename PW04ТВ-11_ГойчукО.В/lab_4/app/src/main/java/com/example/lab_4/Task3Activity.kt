package com.example.lab_4

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import java.lang.Math.pow
import kotlin.math.sqrt

class Task3Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_task3)

        fun round(num: Double) = "%.2f".format(num)

        val SNom = 6.3
        val Uh = 115.0
        val Ul = 11.0
        val UkMax = 11.1

        // Отримуємо посилання на поля введення
        val enterRcn: EditText = findViewById(R.id.field1)
        val enterXcn: EditText = findViewById(R.id.field2)
        val enterRcMin: EditText = findViewById(R.id.field3)
        val enterXcMin: EditText = findViewById(R.id.field4)
        val calculate_button: Button = findViewById(R.id.calcButton)
        val back_button: Button = findViewById(R.id.backButton)

        // Створюємо обробку натискання на кнопку розрахунку
        // використовуємо try catch для перевірки коректності типу введених даних
        calculate_button.setOnClickListener{
            try {
                //Отримуємо уведені значення та переводимо в тип Double
                val Rcn = enterRcn.text.toString().toDouble()
                val Xcn = enterXcn.text.toString().toDouble()
                val RcMin = enterRcMin.text.toString().toDouble()
                val XcMin = enterXcMin.text.toString().toDouble()

                //Проводимо розрахунки згідно формул
                val XT: Double = UkMax * pow(Uh, 2.0)/100/SNom
                val Xsh = Xcn + XT
                val Zsh = sqrt(pow(Rcn, 2.0) + pow(Xsh, 2.0))

                val XshMin = XcMin + XT
                val ZshMin = sqrt(pow(RcMin, 2.0) + pow(XshMin, 2.0))

                val Ish3 = Uh * 1000 / sqrt(3.0) / Zsh
                val Ish2 = Ish3 * sqrt(3.0) / 2

                val IshMin3 = Uh * 1000 / sqrt(3.0) / ZshMin
                val IshMin2 = IshMin3 * sqrt(3.0) / 2

                val Kpr: Double = pow(Ul, 2.0) / pow(Uh, 2.0)

                val RshN = Rcn * Kpr
                val XshN = Xsh * Kpr
                val ZshN = sqrt(pow(RshN, 2.0) + pow(XshN, 2.0))

                val RshMinN = RcMin * Kpr
                val XshMinN = XshMin * Kpr
                val ZshMinN = sqrt(pow(RshMinN, 2.0) + pow(XshMinN, 2.0))

                val IshN3 = Ul * 1000 / sqrt(3.0) / ZshN
                val IshN2 = IshN3 * sqrt(3.0) / 2

                val IshMinN3 = Ul * 1000 / sqrt(3.0) / ZshMinN
                val IshMinN2 = IshMinN3 * sqrt(3.0) / 2

                val Rl = 7.91
                val Xl = 4.49

                val RSumN = Rl + RshN
                val XSumN = Xl + XshN
                val ZSumN = sqrt(pow(RSumN, 2.0) + pow(XSumN, 2.0))

                val RSumMinN = Rl + RshMinN
                val XSumMinN = Xl + XshMinN
                val ZSumMinN = sqrt(pow(RSumMinN, 2.0) + pow(XSumMinN, 2.0))

                val Iln3 = Ul * 1000 / sqrt(3.0) / ZSumN
                val Iln2 = Iln3 * sqrt(3.0) / 2

                val IlMinN3 = Ul * 1000 / sqrt(3.0) / ZSumMinN
                val IlMinN2 = IlMinN3 * sqrt(3.0) / 2

                //передаємо фінальний результат
                val output = """
        Опір на шинах в нормальному режимі: ${round(Zsh)} Ом
        Опір на шинах в мінімальному режимі: ${round(ZshMin)} Ом
        Сила трифазного струму на шинах в нормальному режимі: ${round(Ish3)} А
        Сила двофазного струму на шинах в нормальному режимі: ${round(Ish2)} А
        Сила трифазного струму на шинах в мінімальному режимі: ${round(IshMin3)} А
        Сила двофазного струму на шинах в мінімальному режимі: ${round(IshMin2)} А
        Коефіцієнт приведення для визначення дійсних струмів: ${round(Kpr)}
        Опір на шинах в нормальному режимі: ${round(ZshN)} Ом
        Опір на шинах в мінімальному режимі: ${round(ZshMinN)} Ом
        Сила трифазного струму на шинах в нормальному режимі: ${round(IshN3)} А
        Сила двофазного струму на шинах в нормальному режимі: ${round(IshN2)} А
        Сила трифазного струму на шинах в мінімальному режимі: ${round(IshMinN3)} А
        Сила двофазного струму на шинах в мінімальному режимі: ${round(IshMinN2)} А
        Опір в точці 10 в нормальному режимі: ${round(ZSumN)} Ом
        Опір в точці 10 в мінімальному режимі: ${round(ZSumMinN)} Ом
        Сила трифазного струму в точці 10 в нормальному режимі: ${round(Iln3)} А
        Сила двофазного струму в точці 10 в нормальному режимі: ${round(Iln2)} А
        Сила трифазного струму в точці 10 в мінімальному режимі: ${round(IlMinN3)} А
        Сила двофазного струму в точці 10 в мінімальному режимі: ${round(IlMinN2)} А
    """.trimIndent()

                intent = Intent(this, ResultActivity::class.java)
                intent.putExtra("output", output)
                startActivity(intent)

            }catch (e: NumberFormatException){
                // проводимо перевірку типів та виводимо попередження за потреби
                Toast.makeText(this, "Не числові значення", Toast.LENGTH_LONG).show()
            }
        }

        // Створюємо обробку натискання на кнопку повернення
        back_button.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}