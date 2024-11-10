package com.example.lab_4

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Отримуємо посилання на кнопки
        val task1Button: Button = findViewById(R.id.button1)
        val task2Button: Button = findViewById(R.id.button2)
        val task3Button: Button = findViewById(R.id.button3)

        // Створюємо обробку натискання на кожну з кнопок
        task1Button.setOnClickListener{
            intent = Intent(this, Task1Activity::class.java)
            startActivity(intent)
        }
        task2Button.setOnClickListener{
            intent = Intent(this, Task2Activity::class.java)
            startActivity(intent)
        }
        task3Button.setOnClickListener{
            intent = Intent(this, Task3Activity::class.java)
            startActivity(intent)
        }
    }
}