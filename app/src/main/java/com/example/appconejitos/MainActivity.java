package com.example.appconejitos;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ImageView[] conejos;
    private final int[] ids = {R.id.img1, R.id.img2, R.id.img3, R.id.img4, R.id.img5, R.id.img6, R.id.img7};
    private CountDownTimer countDownTimer;
    private long tiempoInicial;
    private TextView txtTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tiempoInicial = getIntent().getIntExtra("tiempo", 60000);
        txtTimer = findViewById(R.id.txtTimer);
        Button btnReiniciar = findViewById(R.id.btnReiniciar);
        Button btnInicio = findViewById(R.id.btnInicio);

        btnReiniciar.setOnClickListener(v -> reiniciarJuego());
        btnInicio.setOnClickListener(v -> {
            if (countDownTimer != null) countDownTimer.cancel();
            Intent intent = new Intent(this, StartActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        });

        conejos = new ImageView[ids.length];
        for (int i = 0; i < ids.length; i++) {
            int index = i;
            conejos[i] = findViewById(ids[i]);
            conejos[i].setOnClickListener(v -> {
                moverConejo(index);
                actualizarImagenes();
                if (juegoGanado()) {
                    mostrarResultado("\u00a1Ganaste!");
                } else if (!hayMovimientos()) {
                    mostrarResultado("No hay m\u00e1s movimientos");
                }
            });
        }

        inicializar();
        actualizarImagenes();
        iniciarTimer();
    }

    public void inicializar() {
        conejos[0].setTag(1);
        conejos[1].setTag(2);
        conejos[2].setTag(3);
        conejos[3].setTag(0);
        conejos[4].setTag(5);
        conejos[5].setTag(6);
        conejos[6].setTag(7);
    }

    private void asignarimagenes(ImageView imagen) {
        int tag = (int) imagen.getTag();
        switch (tag) {
            case 0:
                imagen.setImageDrawable(null);
                break;
            case 1:
            case 2:
            case 3:
                imagen.setImageResource(R.drawable.iz);
                break;
            case 4:
            case 5:
            case 6:
            case 7:
                imagen.setImageResource(R.drawable.der);
                break;
        }
    }

    private void actualizarImagenes() {
        for (ImageView conejo : conejos) {
            asignarimagenes(conejo);
        }
    }

    private int posicionvacia() {
        for (int i = 0; i < conejos.length; i++) {
            if ((int) conejos[i].getTag() == 0) {
                return i;
            }
        }
        return -1;
    }

    private void moverConejo(int desde) {
        int vacio = posicionvacia();
        if (vacio == -1) return;
        int tagDesde = (int) conejos[desde].getTag();
        if (tagDesde == 0) return;

        if (Math.abs(vacio - desde) == 1) {
            if (tagDesde >= 1 && tagDesde <= 3 && vacio > desde) {
                intercambiarTag(desde, vacio);
            } else if (tagDesde >= 5 && tagDesde <= 7 && vacio < desde) {
                intercambiarTag(desde, vacio);
            }
        } else if (Math.abs(vacio - desde) == 2) {
            int medio = (desde + vacio) / 2;
            int tagMedio = (int) conejos[medio].getTag();
            if (tagMedio != 0) {
                if (tagDesde >= 1 && tagDesde <= 3 && vacio > desde) {
                    intercambiarTag(desde, vacio);
                } else if (tagDesde >= 5 && tagDesde <= 7 && vacio < desde) {
                    intercambiarTag(desde, vacio);
                }
            }
        }
    }

    private void intercambiarTag(int pos1, int pos2) {
        Object temp = conejos[pos1].getTag();
        conejos[pos1].setTag(conejos[pos2].getTag());
        conejos[pos2].setTag(temp);
    }

    private boolean hayMovimientos() {
        for (int i = 0; i < conejos.length; i++) {
            int tag = (int) conejos[i].getTag();
            if (tag >= 1 && tag <= 3) {
                if (i + 1 < conejos.length && (int) conejos[i + 1].getTag() == 0) return true;
                if (i + 2 < conejos.length && (int) conejos[i + 2].getTag() == 0 && (int) conejos[i + 1].getTag() != 0) return true;
            } else if (tag >= 5 && tag <= 7) {
                if (i - 1 >= 0 && (int) conejos[i - 1].getTag() == 0) return true;
                if (i - 2 >= 0 && (int) conejos[i - 2].getTag() == 0 && (int) conejos[i - 1].getTag() != 0) return true;
            }
        }
        return false;
    }

    private boolean juegoGanado() {
        return (int) conejos[0].getTag() >= 5 &&
                (int) conejos[1].getTag() >= 5 &&
                (int) conejos[2].getTag() >= 5 &&
                (int) conejos[3].getTag() == 0 &&
                (int) conejos[4].getTag() <= 3 &&
                (int) conejos[5].getTag() <= 3 &&
                (int) conejos[6].getTag() <= 3;
    }

    private void mostrarResultado(String mensaje) {
        if (countDownTimer != null) countDownTimer.cancel();
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("message", mensaje);
        intent.putExtra("tiempo", (int) tiempoInicial);
        startActivity(intent);
    }

    private void iniciarTimer() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        countDownTimer = new CountDownTimer(tiempoInicial, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                txtTimer.setText(String.valueOf(millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                mostrarResultado(getString(R.string.tiempo_agotado));
            }
        }.start();
    }

    private void reiniciarJuego() {
        inicializar();
        actualizarImagenes();
        iniciarTimer();
    }
}
