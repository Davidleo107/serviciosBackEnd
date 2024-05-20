/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.serviciosREST.Usuarios.controlador;

import com.serviciosREST.Usuarios.modelo.Usuario;
import com.serviciosREST.Usuarios.servicio.Servicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class Controlador {

    private final Servicio servicio;

    @Autowired
    public Controlador(Servicio servicio) {
        this.servicio = servicio;
    }

    @GetMapping("/Usuarios")
    public Usuario getUser(@RequestParam Integer id) {
        // Asumiendo que el método getUser de la clase Servicio devuelve un Optional<Usuario>
        Optional<Usuario> user = servicio.getUser(id);
        return user.orElse(null);
    }
    
    
}
