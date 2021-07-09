package com.example.mapsapp.adapters;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mapsapp.DetalleActivity;
import com.example.mapsapp.R;
import com.example.mapsapp.personajes.Pokemon;
import com.google.gson.Gson;

import java.util.List;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>{
    private List<Pokemon> listaPokemons;
    public Context context;
    private TextView nombre;
    private TextView tipo;
    private ImageView imagen;
    private String pathImage = "https://upn.lumenes.tk";
    public String urlImage;
    private Button btnVer;
    private Activity activity;
    public PokemonAdapter(List<Pokemon> listaPokemons, Context context, Activity activity) {
        this.listaPokemons = listaPokemons;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public PokemonAdapter.PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pokemon, parent, false);
        PokemonViewHolder vh = new PokemonViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonAdapter.PokemonViewHolder holder, int position) {
        nombre = holder.itemView.findViewById(R.id.tvPokemons);
        tipo = holder.itemView.findViewById(R.id.tvTipo);
        imagen = holder.itemView.findViewById(R.id.igPokemon);
        Pokemon pokemon = listaPokemons.get(position);
        nombre.setText(pokemon.getNombre());
        urlImage = pokemon.getImagenUrl();
        tipo.setText(pokemon.getTipo());
        Glide.with(context).load(urlImage).into(imagen);

        btnVer = holder.itemView.findViewById(R.id.btnVer);
        btnVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, DetalleActivity.class);
                intent.putExtra("Pokemon",new Gson().toJson(pokemon));
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaPokemons.size();
    }

    // Por cada clase adapter, se necesita una clase ViewHolder
    public class PokemonViewHolder extends RecyclerView.ViewHolder {

        public PokemonViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}