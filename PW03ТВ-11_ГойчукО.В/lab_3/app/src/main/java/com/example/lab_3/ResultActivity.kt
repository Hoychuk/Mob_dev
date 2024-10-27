package com.example.lab_3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)

        //отримуємо посилання на поля виведення
        val profit1 = findViewById<TextView>(R.id.changeText1)
        val lose1 = findViewById<TextView>(R.id.changeText2)
        val profit_lose1 = findViewById<TextView>(R.id.changeText3)
        val profit2 = findViewById<TextView>(R.id.changeText4)
        val lose2 = findViewById<TextView>(R.id.changeText5)
        val profit_lose2 = findViewById<TextView>(R.id.changeText6)
        //отримуємо посилання на кнопку повернення
        val backButton: Button = findViewById(R.id.backButton)

        //отримуємо передані значення з MainActivity
        val getProfit1 = intent.getStringExtra("profit1")
        val getLose1 = intent.getStringExtra("lose1")
        val getProfit2 = intent.getStringExtra("profit2")
        val getLose2 = intent.getStringExtra("lose2")

        // присвоюємо значення текстовим полям та додаємо одиниці виміру
        //також перевіряємо прибуток/втрати та повідомляємо
        profit1.text = "Прибуток " + getProfit1 + " тис. грн"
        lose1.text = "Втрати " + getLose1 + " грн"
        if (getProfit1 != null && getLose1 != null) {
            if (getProfit1.toDouble() - getLose1.toDouble() < 0) {
                profit_lose1.text = "Загалом втрачено " + (getLose1.toDouble()-getProfit1.toDouble()).toString() + " тис. грн" }
            else{
                profit_lose1.text = "Загалом прибуток " + (getProfit1.toDouble() - getLose1.toDouble()).toString() + " тис. грн"
            }
        }

        profit2.text = "Прибуток " + getProfit2 + " тис. грн"
        lose2.text = "Втрати " + getLose2 + " тис. грн"
        if (getProfit2 != null && getLose2 != null) {
            if (getProfit2.toDouble() - getLose2.toDouble() < 0) {
                profit_lose2.text = "Загалом втрачено " + "%.2f".format(getLose2.toDouble()-getProfit2.toDouble()) + " тис. грн"
            }else{
                profit_lose2.text = "Загалом прибуток " + "%.2f".format(getProfit2.toDouble() - getLose2.toDouble()) + " тис. грн"
            }
        }

        //обробник кнопки повернення
        backButton.setOnClickListener(){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}