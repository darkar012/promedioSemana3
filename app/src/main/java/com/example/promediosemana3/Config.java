package com.example.promediosemana3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class Config extends AppCompatActivity {

    private Button azul;
    private Button blanco;
    private Button negro;
    private ConstraintLayout fondo;
    private TextView tittle, mensajeConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        azul = findViewById(R.id.azulBtn);
        blanco = findViewById(R.id.blancoBtn);
        negro = findViewById(R.id.negroBtn);
        fondo = findViewById(R.id.fondo);
        tittle = findViewById(R.id.titulo);
        mensajeConfig = findViewById(R.id.mensajeConfig);

        SharedPreferences preferences = getSharedPreferences("colores", MODE_PRIVATE);

        azul.setOnClickListener(
                (v) -> {

                    String azul = "#457FDF";
                    preferences.edit().putString("color", azul).apply();
                    preferences.edit().putString("colorLetra", "#FFFFFF").apply();
                    finish();
                }
        );
        blanco.setOnClickListener(
                (v) -> {

                    String blanco = "#FFFFFF";

                    preferences.edit().putString("color", blanco).apply();
                    preferences.edit().putString("colorLetra", "#787979").apply();

                    finish();
                }
        );
        negro.setOnClickListener(
                (v) -> {

                    String negro = "#787979";

                    preferences.edit().putString("color", negro).apply();

                    preferences.edit().putString("colorLetra", "#FFFFFF").apply();

                    finish();
                }
        );

    }

    protected void onResume() {
        super.onResume();
        SharedPreferences preferences = getSharedPreferences("colores", MODE_PRIVATE);
        String colorLetra = preferences.getString("colorLetra", "#787979");
        String color = preferences.getString("color", "#ffffff");
        fondo.setBackgroundColor(Color.parseColor(color));
        tittle.setTextColor(Color.parseColor(colorLetra));
        mensajeConfig.setTextColor(Color.parseColor(colorLetra));
    }
}
