package com.example.appconejitos

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class ReglasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reglas)

        findViewById<Button>(R.id.btnHome).setOnClickListener {
            finish()
        }
    }
}
