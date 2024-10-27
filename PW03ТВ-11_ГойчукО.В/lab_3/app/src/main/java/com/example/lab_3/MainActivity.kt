package com.example.lab_3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.math.pow
import kotlin.math.sqrt
import kotlin.math.PI
import kotlin.math.exp


class MainActivity : AppCompatActivity() {

    //функція інтегралу для обчслення первісної
    fun integral (a: Double, b: Double, n: Int, function: (Double) -> Double): Double{
        val h = (b-a)/n
        var sum = 0.0
        for (i in 0 until  n){
            val x = a + i * h
            sum += function(x)
        }

        return sum * h
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Отримуємо посилання на поля введення
        val enterPower: EditText = findViewById(R.id.field1)
        val enterError1: EditText = findViewById(R.id.field2)
        val enterError2: EditText = findViewById(R.id.field3)
        val enterPrice: EditText = findViewById(R.id.field4)
        val button: Button = findViewById(R.id.button)

        // Створюємо обробку натискання на кнопку
        // використовуємо try catch для перевірки коректності типу введених даних
        button.setOnClickListener{
            try{
                //Отримуємо уведені значення та переводимо в тип Double
                val power = enterPower.text.toString().toDouble()
                val error1 = enterError1.text.toString().toDouble()
                val error2 = enterError2.text.toString().toDouble()
                val price = enterPrice.text.toString().toDouble()

                //задаємо значення границь інтегралу
                val upperLimit = power + power * 0.05
                val lowerLimit = power - power * 0.05

                //знаходимо значення інтегралу
                val deltaW1 = integral(lowerLimit, upperLimit, 1000) {x -> 1/(error1* sqrt(2* PI))* exp(-((x-power).pow(2))/(2*error1.pow(2)))}

                //знаходимо значення прибутку та втрати до вдосконалення системи
                val W1 = power * 24 * "%.2f".format(deltaW1).toDouble()
                val profit1 = W1 * price
                val W2 = power * 24 * "%.2f".format(1-deltaW1).toDouble()
                val lose1 = W2*price

                val deltaW2 = integral(lowerLimit, upperLimit, 1000) {x -> 1/(error2* sqrt(2* PI))* exp(-((x-power).pow(2))/(2*error2.pow(2)))}

                ////знаходимо значення прибутку та втрати після вдосконалення системи
                val W3 = power * 24 * "%.2f".format(deltaW2).toDouble()
                val profit2 = W3 * price
                val W4 = power * 24 * "%.2f".format(1-deltaW2).toDouble()
                val lose2 = W4 * price

                // переходимо до іншої активності та передаємо розрахункові значення
                intent = Intent(this, ResultActivity::class.java)
                intent.putExtra("profit1", profit1.toString())
                intent.putExtra("lose1", lose1.toString())
                intent.putExtra("profit2", profit2.toString())
                intent.putExtra("lose2", lose2.toString())
                startActivity(intent)

            }catch (e: NumberFormatException){
                // проводимо перевірку типів та виодимо попередження за потреби
                Toast.makeText(this, "Не числові значення", Toast.LENGTH_LONG).show()
            }
        }

    }
}