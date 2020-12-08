package com.example.mapsapp;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.graphics.Camera;
import android.os.Bundle;
import android.util.Log;

import com.example.mapsapp.personajes.Pokemon;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Double latitud;
    private Double longitud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        Intent intent = getIntent();
        String pokemon = intent.getStringExtra("Pokemon");
        Pokemon pkm = new Gson().fromJson(pokemon, Pokemon.class);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        latitud = pkm.getLatitude().doubleValue();
        longitud = pkm.getLongitude().doubleValue();

        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // -7.168186, -78.513110
        LatLng cajamarca = new LatLng(latitud, longitud);
        mMap.addMarker(new MarkerOptions().position(cajamarca).title("Pokemon"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cajamarca,20f));
    }
}