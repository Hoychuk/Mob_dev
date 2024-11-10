package com.example.lab_4

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.sqrt

class Task1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_task1)

        val U = 10.0

        // Отримуємо посилання на поля введення
        val enterI: EditText = findViewById(R.id.field1)
        val entert: EditText = findViewById(R.id.field2)
        val enterSm: EditText = findViewById(R.id.field3)
        val enterjEk: EditText = findViewById(R.id.field4)
        val calculate_button: Button = findViewById(R.id.calcButton)
        val back_Button: Button = findViewById(R.id.backButton)

        // Створюємо обробку натискання на кнопку розрахунку
        // використовуємо try catch для перевірки коректності типу введених даних
        calculate_button.setOnClickListener{
            try {
                //Отримуємо уведені значення та переводимо в тип Double
                val I = enterI.text.toString().toDouble()
                val t = entert.text.toString().toDouble()
                val Sm = enterSm.text.toString().toDouble()
                val jEk = enterjEk.text.toString().toDouble()

                //Проводимо розрахунки згідно формул
                val Im = Sm / 2 / (sqrt(3.0)*U)
                val ImPA = 2 * Im
                val sEk = Im/jEk
                val s = I * 1000 * sqrt(t) / 92

                //передаємо фінальний результат
                val output = "Розрахунковий струм для нормального режиму: "+ "%.2f".format(Im).toDouble() +" A\n" +
                        "Розрахунковий струм для після аварійного режиму: "+ "%.2f".format(ImPA).toDouble() +" A\n" +
                        "Економічний переріз: "+ "%.2f".format(sEk).toDouble() +" мм^2\n" +
                        "Оптимальний переріз: "+ "%.2f".format(s).toDouble() +" мм^2\n"

                intent = Intent(this, ResultActivity::class.java)
                intent.putExtra("output", output)
                startActivity(intent)

            }catch (e: NumberFormatException){
                // проводимо перевірку типів та виводимо попередження за потреби
                Toast.makeText(this, "Не числові значення", Toast.LENGTH_LONG).show()
            }
        }

        // Створюємо обробку натискання на кнопку повернення
        back_Button.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}