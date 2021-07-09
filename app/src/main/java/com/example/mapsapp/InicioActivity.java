package com.example.mapsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InicioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);


        Button btnEntrenador = findViewById(R.id.btnInicioEntrenador);
        Button btnPokemon = findViewById(R.id.btnInicioPokemon);

        btnPokemon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(InicioActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });

        btnEntrenador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(InicioActivity.this, FormCrearEntrenadorActivity.class);
                startActivity(intent);

            }
        });

    }
}