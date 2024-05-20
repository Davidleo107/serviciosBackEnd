/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.serviciosREST.Usuarios.controlador;

import com.serviciosREST.Usuarios.modelo.Usuario;
import com.serviciosREST.Usuarios.modelo.Conductor;
import com.serviciosREST.Usuarios.servicio.Servicio;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class Controlador {

    private final Servicio servicio;

    @Autowired
    public Controlador(Servicio servicio) {
        this.servicio = servicio;
    }

    /*
    Maneja solicitd GET para obtener la lista completa de los usuarios
    */
    @GetMapping("/AllUsers")
    public List<Usuario> getAllUsers() {
        return servicio.getAllUsers();
    }

    /*
    Maneja solicitd POST para agregar un usuario nuevo, generando un nuevo ID para el usuario nuevo
    por medio de la obtencion de todos los usuarios, y calculando el tamaño del arreglo para aumentarlo
    */
    @PostMapping("/usuarios")
    public ResponseEntity<Usuario> addUser(@RequestBody Usuario nuevoUsuario) {
        int nuevoId = servicio.getAllUsers().size() + 1;
        nuevoUsuario.setId(nuevoId);
        servicio.addUser(nuevoUsuario);
        return ResponseEntity.ok(nuevoUsuario);
    }

    
    /*
    Maneja solicitud GET para ovtener un usuario por medio del ID
    */
    @GetMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Integer id) {
        Optional<Usuario> usuarioOptional = servicio.getUser(id);
        if (usuarioOptional.isPresent()) {
            return ResponseEntity.ok(usuarioOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /*
    Maneja solicitud PUT para acutalizar un usuario por su ID
    */
    @PutMapping("/usuarios/{id}")
    public ResponseEntity<String> editUserById(@PathVariable Integer id, @RequestBody Usuario userActualizado) {
        try {
            servicio.editUser(id, userActualizado);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /*
    Maneja solicitud DELETE para elminar un usuario por su ID
    */
    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Integer id) {
        try {
            servicio.deleteUser(id);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/solicitarCarga")
    public ResponseEntity<String> solicitarCarga(@RequestParam Integer conductorId, @RequestParam Integer idUsuario) {
        try {
            servicio.solicitarCarga(conductorId, idUsuario);
            return ResponseEntity.ok("Carga solicitada correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al solicitar la carga: " + e.getMessage());
        }
    }

    @GetMapping("/recepcionPaquete/{conductorId}")
    public ResponseEntity<String> recepcionPaquete(@PathVariable Integer conductorId) {
        Optional<Conductor> conductorOptional = servicio.getConductor(conductorId);
        if (conductorOptional.isPresent()) {
            Conductor conductor = conductorOptional.get();
            String recepcionInfo = servicio.recepcionPaquete(conductor);
            return ResponseEntity.ok(recepcionInfo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/consultarSolicitudes")
    public ResponseEntity<List<Usuario>> consultarSolicitudes() {
        Optional<Usuario> usuarioOptional = servicio.consultarSolicitudes();
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            List<Usuario> usuarios = new ArrayList<>();
            usuarios.add(usuario);
            return ResponseEntity.ok(usuarios);
        } else {
            return ResponseEntity.ok(new ArrayList<>());
        }
    }

    @GetMapping("/conductores/{id}")
    public ResponseEntity<Conductor> getConductorById(@PathVariable Integer id) {
        Optional<Conductor> conductorOptional = servicio.getConductor(id);
        if (conductorOptional.isPresent()) {
            return ResponseEntity.ok(conductorOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
