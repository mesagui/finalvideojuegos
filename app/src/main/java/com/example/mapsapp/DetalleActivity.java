package com.example.mapsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mapsapp.personajes.Pokemon;
import com.google.gson.Gson;

public class DetalleActivity extends AppCompatActivity {

    private ImageView ivImagen;
    private String path;
    private TextView tvNombre;
    private TextView tvTipo;
    private Button btnUbicacion;
    private Context context;
    private String urlImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        tvNombre = findViewById(R.id.tvNombre);
        tvTipo = findViewById(R.id.tvTipo);
        ivImagen = findViewById(R.id.ivImage);
        context = this.getApplicationContext();
        btnUbicacion = findViewById(R.id.btnUicacion);
        path = "";

        Intent intent = getIntent();
        String pokemon = intent.getStringExtra("Pokemon");
        Pokemon pkm = new Gson().fromJson(pokemon, Pokemon.class);

        urlImage = pkm.getImagenUrl();
        tvNombre.setText(pkm.getNombre());
        tvTipo.setText(pkm.getTipo());
        tvNombre.setText(pkm.getNombre());
        Glide.with(context).load(urlImage).into(ivImagen);
        Log.i("MY_APP",urlImage);
        btnUbicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetalleActivity.this,MapsActivity.class);
                intent.putExtra("Pokemon",new Gson().toJson(pkm));
                startActivity(intent);
            }
        });
    }
}