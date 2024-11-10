package com.example.lab_4

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.sqrt

class Task2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_task2)

        val SNom = 6.3

        // Отримуємо посилання на поля введення
        val enterP: EditText = findViewById(R.id.field1)
        val enterUsn: EditText = findViewById(R.id.field2)
        val calculate_button: Button = findViewById(R.id.calcButton)
        val back_Button: Button = findViewById(R.id.backButton)

        // Створюємо обробку натискання на кнопку розрахунку
        // використовуємо try catch для перевірки коректності типу введених даних
        calculate_button.setOnClickListener{
            try {
                //Отримуємо уведені значення та переводимо в тип Double
                val P = enterP.text.toString().toDouble()
                val Usn = enterUsn.text.toString().toDouble()

                //Проводимо розрахунки згідно формул
                val Xc = (Usn*Usn)/P
                val Xt = (Usn*Usn*Usn)/SNom/100

                val X = Xc + Xt

                val Ip0 = Usn / sqrt(3.0)/X

                //передаємо фінальний результат
                val output = "Опори елементів заступних схеми: " +"%.2f".format(Xc).toDouble() + " Ом і " +"%.2f".format(Xt).toDouble() + " Ом\n" +
                        "Сумарний опір: " +"%.2f".format(X).toDouble() + " Ом\n" +
                        "Початкове діюче значення струму трифазного КЗ: " +"%.2f".format(Ip0).toDouble() + " кА\n"

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