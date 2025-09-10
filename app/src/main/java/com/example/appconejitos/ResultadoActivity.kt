package com.example.appconejitos

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultadoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado)

        val message = intent.getStringExtra(EXTRA_MESSAGE) ?: ""
        findViewById<TextView>(R.id.txtMessage).text = message

        findViewById<Button>(R.id.btnRestart).setOnClickListener {
            startActivity(Intent(this, InicioActivity::class.java))
            finish()
        }

        findViewById<Button>(R.id.btnHome).setOnClickListener {
            startActivity(Intent(this, InicioActivity::class.java))
            finish()
        }
    }

    companion object {
        const val EXTRA_MESSAGE = "message"
    }
}
