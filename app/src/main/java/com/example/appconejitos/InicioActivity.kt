package com.example.appconejitos

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity

class InicioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)

        val rgDificultad: RadioGroup = findViewById(R.id.rgDificultad)

        findViewById<Button>(R.id.btnJugar).setOnClickListener {
            val tiempo = when (rgDificultad.checkedRadioButtonId) {
                R.id.rbMedio -> 45_000L
                R.id.rbDificil -> 30_000L
                else -> 60_000L
            }
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra(MainActivity.EXTRA_TIME, tiempo)
            startActivity(intent)
        }

        findViewById<Button>(R.id.btnReglas).setOnClickListener {
            startActivity(Intent(this, ReglasActivity::class.java))
        }
    }
}
