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

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private Button config, continuar;
    private ConstraintLayout fondo;
    private EditText nombreET;
    private TextView mensaje;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        config = findViewById(R.id.configBtn);
        fondo = findViewById(R.id.fondo);
        nombreET = findViewById(R.id.nombreET);
        continuar = findViewById(R.id.continuarBtn);
        mensaje = findViewById(R.id.textMessage);

        continuar.setOnClickListener(
                (v) -> {
                    String nombre = nombreET.getText().toString();
                    validacionNumeros(nombre);
                }
        );


        config.setOnClickListener(
                (v) -> {

                    Intent i = new Intent(this, Config.class);
                    startActivity(i);

                }
        );

    }

    @Override
    protected void onResume() {
        super.onResume();
        nombreET.setText("");
        SharedPreferences preferences = getSharedPreferences("colores", MODE_PRIVATE);
        String color = preferences.getString("color", "#ffffff");
        String colorLetra = preferences.getString("colorLetra", "#787979");
        fondo.setBackgroundColor(Color.parseColor(color));
        mensaje.setTextColor(Color.parseColor(colorLetra));
        nombreET.setTextColor(Color.parseColor(colorLetra));
    }

    public void validacionNumeros(String string) {
        boolean noNumber = true;
        if (string == null || string.isEmpty()) {
            Toast.makeText(this, "No hay ingresado un nombre", Toast.LENGTH_LONG).show();
        } else {
            for (int i = 0; i < string.length(); i++) {
                if (Character.isDigit(string.charAt(i))) {
                    Toast.makeText(this, "Ha ingresado numeros en el nombre", Toast.LENGTH_LONG).show();
                    string = "";
                    nombreET.setText(string);
                    noNumber = false;
                } else {
                    noNumber = true;
                }
            }
            if (noNumber) {
                String nombre = string;
                Intent i = new Intent(this, calculoPromedio.class);
                i.putExtra("name", nombre);
                startActivity(i);
            }
        }
    }
}