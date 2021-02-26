package com.example.promediosemana3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

public class resultado extends AppCompatActivity {

    private ConstraintLayout fondo;
    private TextView mensajeFinal, nota, resultado;
    private String nombre, promedio;
    private Button calcularAgain;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        fondo = findViewById(R.id.fondo);
        mensajeFinal = findViewById(R.id.mensajeFinal);
        nota = findViewById(R.id.nota);
        nombre = getIntent().getExtras().getString("name");
        promedio = getIntent().getExtras().getString("promedio");
        calcularAgain = findViewById(R.id.calcularAgainBtn);
        resultado = findViewById(R.id.resultadoT);

        mensajeFinal.setText("Hola, " + nombre + " . Tu nota final es de:");
        nota.setText(promedio);

        Log.e(">>>",""+promedio);

        calcularAgain.setOnClickListener(
                (v) -> {
                    finish();
                }
        );

    }

    protected void onResume() {
        super.onResume();
        SharedPreferences preferences = getSharedPreferences("colores", MODE_PRIVATE);
        String color = preferences.getString("color", "#ffffff");
        String colorLetra = preferences.getString("colorLetra", "#787979");
        fondo.setBackgroundColor(Color.parseColor(color));
        mensajeFinal.setTextColor(Color.parseColor(colorLetra));
        nota.setTextColor(Color.parseColor(colorLetra));
        resultado.setTextColor(Color.parseColor(colorLetra));
    }
}