package com.maydana.roman.hoteleslapaz;

import java.io.Serializable;

public class Hotel implements Serializable {
    private String nombre;
    private String direccion;
    private double estrellas;
    private String numero;
    private String detalle;
    private int foto;



    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public double getEstrellas() {
        return estrellas;
    }

    public void setEstrellas(double estrellas) {
        this.estrellas = estrellas;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public Hotel(String nombre, String direccion, double estrellas, String numero, String detalle, int foto) {

        this.nombre = nombre;
        this.direccion = direccion;
        this.estrellas = estrellas;
        this.numero = numero;
        this.detalle = detalle;
        this.foto = foto;
    }
}
