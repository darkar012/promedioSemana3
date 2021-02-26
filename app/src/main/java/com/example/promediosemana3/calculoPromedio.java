package com.example.promediosemana3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;

public class calculoPromedio extends AppCompatActivity {

    private ConstraintLayout fondo;
    private EditText nota1, nota2, nota3, nota4, nota5;
    private TextView calculoTitle;
    private String nombre;
    private Button calcular;
    private boolean isNumber = true;
    private double note1, note2, note3, note4, note5, promedio;
    private String[] notas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculo_promedio);

        fondo = findViewById(R.id.fondo);
        nota1 = findViewById(R.id.nota1);
        nota2 = findViewById(R.id.nota2);
        nota3 = findViewById(R.id.nota3);
        nota4 = findViewById(R.id.nota4);
        nota5 = findViewById(R.id.nota5);
        calcular = findViewById(R.id.calcularBtn);
        calculoTitle =findViewById(R.id.calculoTIttle);

        nombre = getIntent().getExtras().getString("name");


        calcular.setOnClickListener(
                (v) -> {
                    isNumber=true;
                    calculo();
                    Log.e(">>>", "" + promedio);
                    if (isNumber != false) {
                        Intent i = new Intent(this, resultado.class);
                        i.putExtra("name", nombre);
                        String prom = String.valueOf(promedio);
                        i.putExtra("promedio", prom);
                        startActivity(i);
                        finish();
                    }
                }

        );

    }

    protected void onResume() {
        super.onResume();
        SharedPreferences preferences = getSharedPreferences("colores", MODE_PRIVATE);
        String color = preferences.getString("color", "#ffffff");
        String colorLetra = preferences.getString("colorLetra", "#787979");
        fondo.setBackgroundColor(Color.parseColor(color));
        calculoTitle.setTextColor(Color.parseColor(colorLetra));
        nota1.setTextColor(Color.parseColor(colorLetra));
        nota2.setTextColor(Color.parseColor(colorLetra));
        nota3.setTextColor(Color.parseColor(colorLetra));
        nota4.setTextColor(Color.parseColor(colorLetra));
        nota5.setTextColor(Color.parseColor(colorLetra));
    }

    public void calculo() {

        String string1 = nota1.getText().toString();
        String string2 = nota2.getText().toString();
        String string3 = nota3.getText().toString();
        String string4 = nota4.getText().toString();
        String string5 = nota5.getText().toString();

        notas = new String[]{
                string1,
                string2,
                string3,
                string4,
                string5
        };
        if (isNumber) {

            for (int i = 0; i < notas.length; i++) {
                if (notas[i] == null || notas[i].isEmpty()) {
                    Toast.makeText(this, "Hay una nota sin valores ingresados", Toast.LENGTH_SHORT).show();
                    isNumber = false;
                } else {
                    try {
                        double number = Double.parseDouble(notas[i]);
                        if (number > 5) {
                            Toast.makeText(this, "Las calificacion van de 1 a 5", Toast.LENGTH_SHORT).show();
                            isNumber = false;
                        } else {
                            if (i == 0) {
                                note1 = number;
                            } else if (i == 1) {
                                note2 = number;
                            } else if (i == 2) {
                                note3 = number;
                            } else if (i == 3) {
                                note4 = number;
                            } else if (i == 4) {
                                note5 = number;
                            }
                        }
                    } catch (NumberFormatException e) {
                        Toast.makeText(this, "Solo se admiten números", Toast.LENGTH_LONG).show();
                        isNumber = false;
                    }
                    double prom = ((note1 * 0.25) + (note2 * 0.25) + (note3 * 0.20) + (note4 * 0.15) + (note5 * 0.15));
                    promedio=Math.round(prom*100.0)/100.0;
                }
            }
        }
    }
}