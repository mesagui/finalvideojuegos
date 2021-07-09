package com.example.mapsapp.personajes;

public class Pokemon {
    public String nombre;
    public String tipo;
    public String imagen;
    public String url_imagen;
    public Number latitude;
    public Number longitude;

    public Pokemon() {
    }
    

    public Pokemon(String nombre, String tipo, String imagen, Number latitude, Number longitude, String url_imagen) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.imagen = imagen;
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
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getImagenUrl() {
        return "https://upn.lumenes.tk" + url_imagen;
    }

    public void setImagenUrl(String url_imagen) {
        this.url_imagen = url_imagen;
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