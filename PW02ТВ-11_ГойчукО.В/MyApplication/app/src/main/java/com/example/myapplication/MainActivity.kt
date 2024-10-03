package com.example.myapplication

// додатково імпортуємо math.pow для розрахунку степення
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

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Отримуємо посилання на поля введення
        val enterCoal: EditText = findViewById(R.id.field1)
        val enterMasut: EditText = findViewById(R.id.field2)
        val enterGas: EditText = findViewById(R.id.field3)
        val button: Button = findViewById(R.id.button)
        val GasDensity = 0.723

        // Створюємо обробку натискання на кнопку
        // використовуємо try catch для перевірки коректності типу введених даних
        button.setOnClickListener{
            try{
                //Отримуємо уведені значення та переводимо в тип Double
                val BCoal = enterCoal.text.toString().toDouble()
                val BMasut = enterMasut.text.toString().toDouble()
                val BGas = enterGas.text.toString().toDouble() * GasDensity

                // Розраховуємо коефіцієнти емісії та валовий викид твердих частинок вугілля
                val kCoal = "%.2f".format(10.0.pow(6).toInt()/20.47*0.8*25.2/(100-1.5)*(1-0.985))
                val ECoal = "%.2f".format(10.0.pow(-6)*kCoal.toDouble()*20.47*BCoal)
                // --\\-- мазуту
                val kMasut = "%.2f".format(10.0.pow(6).toInt()/39.48*1*0.15/(100-0)*(1-0.985))
                val EMasut = "%.2f".format(10.0.pow(-6)*kMasut.toDouble()*39.48*BMasut)
                // --\\-- природнього газу
                val kGas = "%.2f".format(10.0.pow(6).toInt()/33.08*0*0/(100-0)*(1-0.985))
                val EGas = "%.2f".format(10.0.pow(-6)*kGas.toDouble()*33.08*BGas)

                // переходимо до іншої активності та передаємо розрахункові значення
                intent = Intent(this, ResultActivity::class.java)
                intent.putExtra("finalkCoal", kCoal.toString())
                intent.putExtra("finalECoal", ECoal.toString())
                intent.putExtra("finalkMasut", kMasut.toString())
                intent.putExtra("finalEMasut", EMasut.toString())
                intent.putExtra("finalkGas", kGas.toString())
                intent.putExtra("finalEGas", EGas.toString())
                startActivity(intent)

            }catch (e: NumberFormatException){
                // проводимо перевірку типів та виодимо попередження за потреби
                Toast.makeText(this, "Не числові значення", Toast.LENGTH_LONG).show()
            }
        }
    }
}