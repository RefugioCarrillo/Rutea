package com.example.rutea;
import com.google.firebase.firestore.GeoPoint;

import java.util.List;

public class Ruta {
    private List<String> categoria;
    private String descripcion;
    private int estrellas;
    private String imagen;
    private String lugar;
    private String nombre;
    private String establecimiento;
    private GeoPoint ubicasion;
    public Ruta() {} // Necesario para Firebase

    public Ruta(List<String> categoria, String descripcion, int estrellas, String imagen, String lugar, String nombre, GeoPoint ubicasion, String establecimiento) {
        this.categoria= categoria;
        this.descripcion = descripcion;
        this.estrellas= estrellas;
        this.imagen = imagen;
        this.lugar = lugar;
        this.nombre = nombre;
        this.ubicasion = ubicasion;
        this.establecimiento = establecimiento;


    }

    // Getters
    public List<String> getCategoria() {
        return categoria;
    }
    public String getDescripcion() { return descripcion; }
    public int getEstrellas() { return estrellas; }
    public String getImagen() { return imagen; }
    public String getLugar() { return lugar; }
    public String getNombre() { return nombre; }
    public String getEstablecimiento() { return establecimiento; }
    public GeoPoint getUbicasion() { return ubicasion; }
}
