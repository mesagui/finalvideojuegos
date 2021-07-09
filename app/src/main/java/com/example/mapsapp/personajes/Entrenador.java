package com.example.mapsapp.personajes;

public class Entrenador {
    public String nombres;
    public String pueblo;
    public String imagen;

    public Pokemon pokemon;


    public Entrenador(String nombres, String pueblo, String imagen) {
        this.nombres = nombres;
        this.pueblo = pueblo;
        this.imagen = imagen;
    }

    public Entrenador(String nombres, String pueblo, String imagen, Pokemon pokemon) {
        this.nombres = nombres;
        this.pueblo = pueblo;
        this.imagen = imagen;
        this.pokemon = pokemon;
    }
}
