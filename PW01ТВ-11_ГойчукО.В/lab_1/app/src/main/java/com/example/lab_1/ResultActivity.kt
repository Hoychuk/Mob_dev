package com.example.lab_1

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

        var finalQrn = findViewById<TextView>(R.id.changeText1)
        var finalQcn = findViewById<TextView>(R.id.changeText2)
        var finalQgn = findViewById<TextView>(R.id.changeText3)
        val backButoon: Button = findViewById(R.id.backButton)

        val qrn = intent.getStringExtra("finalQrn")
        val qcn = intent.getStringExtra("finalQcn")
        val qgn = intent.getStringExtra("finalQgn")

        finalQrn.text = qrn
        finalQcn.text = qcn
        finalQgn.text = qgn

        backButoon.setOnClickListener(){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}