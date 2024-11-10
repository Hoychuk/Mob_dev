package com.example.lab_4

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

        //отримуємо посилання на поле виведення
        val output = findViewById<TextView>(R.id.ChangeText1)
        //отримуємо посилання на кнопку повернення
        val back_button: Button = findViewById(R.id.backButton)

        //отримуємо передані значення
        val getoutput = intent.getStringExtra("output")

        output.text = getoutput

        //обробник кнопки повернення
        back_button.setOnClickListener(){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}