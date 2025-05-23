package com.example.rutea;
import com.google.firebase.firestore.GeoPoint;

public class Ruta {
    private String categoria;
    private String descripcion;
    private int estrellas;
    private String imagen;
    private String lugar;
    private String nombre;

    private GeoPoint ubicasion;
    public Ruta() {} // Necesario para Firebase

    public Ruta(String categoria, String descripcion, int estrellas, String imagen, String lugar, String nombre, GeoPoint ubicasion) {
        this.categoria= categoria;
        this.descripcion = descripcion;
        this.estrellas= estrellas;
        this.imagen = imagen;
        this.lugar = lugar;
        this.nombre = nombre;
        this.ubicasion = ubicasion;


    }

    // Getters
    public String getCategoria() { return categoria; }
    public String getDescripcion() { return descripcion; }
    public int getEstrellas() { return estrellas; }
    public String getImagen() { return imagen; }
    public String getLugar() { return lugar; }
    public String getNombre() { return nombre; }
    public GeoPoint getUbicasion() { return ubicasion; }
}
