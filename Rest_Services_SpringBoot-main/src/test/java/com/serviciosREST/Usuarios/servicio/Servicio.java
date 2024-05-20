/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.serviciosREST.Usuarios.servicio;
import com.serviciosREST.Usuarios.modelo.Usuario;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class Servicio {

    private List<Usuario> userList;

    public Servicio() {
        userList = new ArrayList<>();

        Usuario user1 = new Usuario(1,"Ida", 32, "ida@mail.com","Caja 1", false, "Calle 1");
        Usuario user2 = new Usuario(2,"Hans", 26, "hans@mail.com","caja 2", false, "Calle 2");
        Usuario user3 = new Usuario(3,"Lars", 45, "lars@mail.com", "Caja 3", false, "Calle 3");
        Usuario user4 = new Usuario(4,"Ben", 32, "ben@mail.com", "Caja 4", false, "Calle 4");
        Usuario user5 = new Usuario(5,"Eva", 59, "eva@mail.com", "Caja 5", false, "Calle 5");

        userList.addAll(Arrays.asList(user1,user2,user3,user4,user5));
    }

    public Optional<Usuario> getUser(Integer id) {
        Optional<Usuario> optional = Optional.empty();
        for (Usuario usuario: userList) {
            if(id == usuario.getId()){
                optional = Optional.of(usuario);
                return optional;
            }
        }
        return optional;
    }
}