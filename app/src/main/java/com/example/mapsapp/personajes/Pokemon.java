package com.example.mapsapp.personajes;

public class Pokemon {
    public String nombre;
    public String tipo;
    public String url_imagen;
    public Number latitude;
    public Number longitude;

    public Pokemon() {
    }

    public Pokemon(String nombre, String tipo, String url_imagen, Number latitude, Number longitude) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.url_imagen = url_imagen;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getImagen() {
        return url_imagen;
    }

    public void setImagen(String imagen) {
        this.url_imagen = imagen;
    }

    public Number getLatitude() {
        return latitude;
    }

    public void setLatitude(Number latitude) {
        this.latitude = latitude;
    }

    public Number getLongitude() {
        return longitude;
    }

    public void setLongitude(Number longitude) {
        this.longitude = longitude;
    }
}