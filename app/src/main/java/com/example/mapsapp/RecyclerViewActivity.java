package com.example.mapsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mapsapp.adapters.PokemonAdapter;
import com.example.mapsapp.personajes.Pokemon;
import com.example.mapsapp.services.PokemonService;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecyclerViewActivity extends AppCompatActivity {

    private androidx.recyclerview.widget.RecyclerView recyclerView;
    private ImageView imageView;
    private Context context;
    private TextView textView;
    private Activity activity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://upn.lumenes.tk/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        recyclerView = findViewById(R.id.rvPokemons);
        imageView = findViewById(R.id.igPokemon);
        textView = findViewById(R.id.tvPokemons);
        textView = findViewById(R.id.tvTipo);
        context = this.getApplicationContext();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        PokemonService service = retrofit.create(PokemonService.class);
        Call<List<Pokemon>> call = service.getByQuery("abc");

        call.enqueue(new Callback<List<Pokemon>>() {
            @Override
            public void onResponse(Call<List<Pokemon>> call, Response<List<Pokemon>> response) {
                if (response.code()==200){
                    List<Pokemon> pokemons = response.body();

                    recyclerView.setAdapter(new PokemonAdapter(pokemons, context, activity));
                }else {
                    Log.i("MY_APP" , "Intentalo de nuevo, algo falló");
                }
            }

            @Override
            public void onFailure(Call<List<Pokemon>> call, Throwable t) {
                Log.i("MY_APP" , "No pudimos conectarnos con la aplicación");
            }
        });
    }
}

