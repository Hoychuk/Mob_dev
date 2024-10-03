package com.example.myapplication

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
        val finalkCoal = findViewById<TextView>(R.id.changeText1)
        val finalECoal = findViewById<TextView>(R.id.changeText4)
        val finalkMasut = findViewById<TextView>(R.id.changeText2)
        val finalEMasut = findViewById<TextView>(R.id.changeText5)
        val finalkGas = findViewById<TextView>(R.id.changeText3)
        val finalEGas = findViewById<TextView>(R.id.changeText6)
        //отримуємо посилання на кнопку повернення
        val backButton: Button = findViewById(R.id.backButton)

        //отримуємо передані значення з MainActivity
        val kCoal = intent.getStringExtra("finalkCoal")
        val ECoal = intent.getStringExtra("finalECoal")
        val kMasut = intent.getStringExtra("finalkMasut")
        val EMasut = intent.getStringExtra("finalEMasut")
        val kGas = intent.getStringExtra("finalkGas")
        val EGas = intent.getStringExtra("finalEGas")

        // присвоюємо значення текстовим полям та додаємо одиниці виміру
        finalkCoal.text = kCoal + " г/ГДж"
        finalECoal.text = ECoal + " т"
        finalkMasut.text = kMasut + " г/ГДж"
        finalEMasut.text = EMasut + " т"
        finalkGas.text = kGas + " г/ГДж"
        finalEGas.text = EGas + " т"

        //обробник кнопки повернення
        backButton.setOnClickListener(){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}