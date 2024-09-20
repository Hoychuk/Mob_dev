package com.example.lab_1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.math.floor

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        var hp: EditText = findViewById(R.id.field1)
        var cp: EditText = findViewById(R.id.field2)
        var sp: EditText = findViewById(R.id.field3)
        var np: EditText = findViewById(R.id.field4)
        var op: EditText = findViewById(R.id.field5)
        var wp: EditText = findViewById(R.id.field6)
        var ap: EditText = findViewById(R.id.field7)
        val button: Button = findViewById(R.id.button)

        button.setOnClickListener {
            try{
                val h = hp.text.toString().toDouble()
                val c = cp.text.toString().toDouble()
                val s = sp.text.toString().toDouble()
                val n = np.text.toString().toDouble()
                val o = op.text.toString().toDouble()
                val w = wp.text.toString().toDouble()
                val a = ap.text.toString().toDouble()

                val Kpc = 100/(100-w)
                val Kpg = 100/(100-w-a)

                var Qrn = (339 * c + 1030 * h - 108.8 * (o - s) - 25 * w) / 1000
                Qrn= floor(Qrn * 1000)/1000
                var Qcn = (Qrn + 0.025 * w) * Kpc
                Qcn= floor(Qcn * 1000)/1000
                var Qgn = (Qrn + 0.025 * w) * Kpg
                Qgn= floor(Qgn * 1000)/1000

                intent = Intent(this, ResultActivity::class.java)
                intent.putExtra("finalQrn", Qrn.toString())
                intent.putExtra("finalQcn", Qcn.toString())
                intent.putExtra("finalQgn", Qgn.toString())
                startActivity(intent)

            }catch (e: NumberFormatException){
                Toast.makeText(this, "Не числові значення", Toast.LENGTH_LONG).show()
            }
        }
    }
}