package com.example.appconejitos

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private val board = mutableListOf(1, 1, 1, 0, 2, 2, 2)
    private val imageIds = listOf(
        R.id.img1, R.id.img2, R.id.img3, R.id.img4, R.id.img5, R.id.img6, R.id.img7
    )
    private lateinit var timer: CountDownTimer
    private var timeMillis: Long = 60_000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        timeMillis = intent.getLongExtra(EXTRA_TIME, 60_000L)
        startTimer()

        imageIds.forEachIndexed { index, id ->
            findViewById<ImageView>(id).setOnClickListener { move(index) }
        }

        findViewById<Button>(R.id.btnReiniciar).setOnClickListener { resetGame() }
        findViewById<Button>(R.id.btnInicio).setOnClickListener {
            startActivity(Intent(this, InicioActivity::class.java))
            finish()
        }

        refreshImages()
    }

    private fun startTimer() {
        timer = object : CountDownTimer(timeMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                findViewById<TextView>(R.id.txtTimer).text = (millisUntilFinished / 1000).toString()
            }

            override fun onFinish() {
                goToResult(getString(R.string.tiempo_agotado))
            }
        }.start()
    }

    private fun resetGame() {
        timer.cancel()
        board.clear()
        board.addAll(listOf(1, 1, 1, 0, 2, 2, 2))
        refreshImages()
        startTimer()
    }

    private fun move(pos: Int) {
        val value = board[pos]
        if (value == 0) return
        val step1 = if (value == 1) pos + 1 else pos - 1
        if (step1 in board.indices && board[step1] == 0) {
            board[step1] = value
            board[pos] = 0
        } else {
            val step2 = if (value == 1) pos + 2 else pos - 2
            if (step2 in board.indices && board[step2] == 0) {
                board[step2] = value
                board[pos] = 0
            } else {
                return
            }
        }
        refreshImages()
        if (board == listOf(2, 2, 2, 0, 1, 1, 1)) {
            goToResult(getString(R.string.ganaste))
        }
    }

    private fun refreshImages() {
        imageIds.forEachIndexed { index, id ->
            val img = findViewById<ImageView>(id)
            when (board[index]) {
                1 -> img.setImageResource(R.drawable.iz)
                2 -> img.setImageResource(R.drawable.der)
                else -> img.setImageDrawable(null)
            }
        }
    }

    private fun goToResult(message: String) {
        timer.cancel()
        val intent = Intent(this, ResultadoActivity::class.java)
        intent.putExtra(ResultadoActivity.EXTRA_MESSAGE, message)
        startActivity(intent)
        finish()
    }

    companion object {
        const val EXTRA_TIME = "extra_time"
    }
}
