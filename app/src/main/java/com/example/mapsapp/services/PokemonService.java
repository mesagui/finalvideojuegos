package com.example.mapsapp.services;

import com.example.mapsapp.personajes.Pokemon;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface PokemonService {
    @GET("pokemons/00039842/")
    Call<List<Pokemon>> getALL();


    @GET("pokemons/00039842/")
    Call<List<Pokemon>> getByQuery(@Query("query") String query);


    @POST("pokemons/00039842/crear")
    Call<Pokemon> create(@Body Pokemon pokemon);
}
