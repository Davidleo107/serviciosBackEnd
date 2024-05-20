/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.serviciosREST.Usuarios.modelo;

/**
 *
 * @author sebas
 */
public class Usuario {

    private int id;
    private String nombre;
    private int edad;
    private String email;

    private String descripcionPaquete;

    private boolean solicitud;

    private String direccionPaquete;

    public Usuario(int id, String nombre, int edad, String email, String descripcionPaquete, boolean solicitud, String direccionPaquete) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.email = email;
        this.descripcionPaquete = descripcionPaquete;
        this.solicitud = solicitud;
        this.direccionPaquete = direccionPaquete;
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

    public void setNombre(String name) {
        this.nombre = name;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int age) {
        this.edad = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescripcionPaquete() {
        return descripcionPaquete;
    }

    public void setDescripcionPaquete(String descripcionPaquete) {
        this.descripcionPaquete = descripcionPaquete;
    }

    public boolean isSolicitud() {
        return solicitud;
    }

    public void setSolicitud(boolean solicitud) {
        this.solicitud = solicitud;
    }

    public String getDireccionPaquete() {
        return direccionPaquete;
    }

    public void setDireccionPaquete(String direccionPaquete) {
        this.direccionPaquete = direccionPaquete;
    }
}
