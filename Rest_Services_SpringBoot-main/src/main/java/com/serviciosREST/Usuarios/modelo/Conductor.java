package com.serviciosREST.Usuarios.modelo;

public class Conductor {

    private int id;
    private String nombre;
    private int capacidadCamion;
    private boolean disponibilidad;
    private String ruta;

    private String placa;
    private String contenido;

    public Conductor(int id, String nombre, int capacidadCamion, boolean disponibilidad, String ruta, String placa, String contenido) {
        this.id = id;
        this.nombre = nombre;
        this.capacidadCamion = capacidadCamion;
        this.disponibilidad = disponibilidad;
        this.ruta = ruta;
        this.placa = placa;
        this.contenido = contenido;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCapacidadCamion() {
        return capacidadCamion;
    }

    public void setCapacidadCamion(int capacidadCamion) {
        this.capacidadCamion = capacidadCamion;
    }

    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
}
