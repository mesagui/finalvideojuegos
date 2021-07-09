package com.example.mapsapp.services;

import com.example.mapsapp.personajes.Entrenador;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface EntrenadorAPIService {

    @GET("entrenador/00039842")
    Call<List<Entrenador>> getALLEntrenador();


    @POST("entrenador/00039842")
    Call<Void> postCreateEntrenador(@Body Entrenador entrenador);


}
