package com.example.appconejitos;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class StartActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Button btnJugar = findViewById(R.id.btnJugar);
        Button btnReglas = findViewById(R.id.btnReglas);
        RadioGroup rgDificultad = findViewById(R.id.rgDificultad);

        btnJugar.setOnClickListener(v -> {
            int tiempo;
            int selected = rgDificultad.getCheckedRadioButtonId();
            if (selected == R.id.rbMedio) {
                tiempo = 30000;
            } else if (selected == R.id.rbDificil) {
                tiempo = 15000;
            } else {
                tiempo = 60000;
            }
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("tiempo", tiempo);
            startActivity(intent);
        });

        btnReglas.setOnClickListener(v -> {
            startActivity(new Intent(this, RulesActivity.class));
        });
    }
}
