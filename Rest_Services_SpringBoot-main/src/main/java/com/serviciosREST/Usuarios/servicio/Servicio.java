/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.serviciosREST.Usuarios.servicio;

import com.serviciosREST.Usuarios.modelo.Conductor;
import com.serviciosREST.Usuarios.modelo.Usuario;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Date;

@Service
public class Servicio {

    private List<Usuario> userList;
    private List<Conductor> driverList;

    public Servicio() {
        // Inicalizacion de la lista de "userList" con ejemplos de usuarios
        userList = new ArrayList<>();
        driverList = new ArrayList<>();

        Usuario usuario1 = new Usuario(1, "Juan", 32, "Juan@mail.com", "Celular", false, "Calle 1");
        Usuario usuario2 = new Usuario(2, "Lorena", 26, "Lorena@mail.com", "Computador", false, "Calle 2");
        Usuario usuario3 = new Usuario(3, "Pablo", 45, "Pablo@mail.com", "Nevera", false,"Calle 3");
        Usuario usuario4 = new Usuario(4, "Laura", 32, "Laura@mail.com","Televisor", false, "Calle 4");
        Usuario usuario5 = new Usuario(5, "Jorge", 59, "Jorge@mail.com", "Cuadro", true, "Calle 5");

        Conductor conductor1 = new Conductor(1,"Camilo", 50, true, "Vacio","XCD231", null);
        Conductor conductor2 = new Conductor(2,"Juan", 100, true, "Vacio","DFT359", null);
        Conductor conductor3 = new Conductor(3,"Carlos", 100, true, "Vacio","ZML207", null);
        Conductor conductor4 = new Conductor(4,"Yeison", 50, true, "Vacio","AQW270", null);
        Conductor conductor5 = new Conductor(5,"Francisco", 100, true, "Vacio","VMN216", null);

        // Se añaden los usuarios de ejemplo
        userList.addAll(Arrays.asList(usuario1, usuario2, usuario3, usuario4, usuario5));
        driverList.addAll((Arrays.asList(conductor1,conductor2,conductor3,conductor4,conductor5)));
    }

    /*
    Devuelve la lista completa de los usuarios que se instanciaron
     */
    public List<Usuario> getAllUsers() {
        return userList;
    }

    /*
     Agregar un nuevo usuario a la lista 
     */
    public void addUser(Usuario user) {
        userList.add(user);
    }

    /*
     Busca y devuelve un usuario por el ID que se le instancia
     Adicional se agrega una clase de Java llamada Optional con el fin de evitar errores al momento de generar un objeto nulo
     debido a que se esta buscando obtener un usuario con todas las caractersiticas
     */
    public Optional<Usuario> getUser(Integer id) {
        Optional<Usuario> opcional = Optional.empty();
        for (Usuario usuario : userList) {
            if (id == usuario.getId()) {
                opcional = Optional.of(usuario);
                return opcional;
            }
        }
        return opcional;
    }

    /*
      Actualiza los datos de un usuario existente por si ID
     */
    public void editUser(Integer id, Usuario userActualizado) {
        // Primero, verificamos si el ID existe
        Optional<Usuario> usuarioExistente = getUser(id);
        if (usuarioExistente.isPresent()) {
            // Si el ID existe, actualizamos los atributos del usuario
            Usuario usuario = usuarioExistente.get();
            usuario.setNombre(userActualizado.getNombre());
            usuario.setEdad(userActualizado.getEdad());
            usuario.setEmail(userActualizado.getEmail());
        } else {
            // Si el ID no existe, lanzamos una excepción o manejamos el error de alguna otra manera
            throw new IllegalArgumentException("El ID " + id + " no existe");
        }
    }
/*
    Elimiza un usuario de la lista por medio de su ID
    */
    public void deleteUser(Integer id) {
        Optional<Usuario> usuarioOpcional = getUser(id);
        if (usuarioOpcional.isPresent()) {
            Usuario usuario = usuarioOpcional.get();
            userList.remove(usuario);
        } else {
            // si el ID no existe, se lanza una excepcion para saber que no existe un usuario por eliminar
            throw new IllegalArgumentException("El ID " + id + " no existe");
        }
    }

    public Optional<Conductor> getConductor(Integer id) {
        Optional<Conductor> opcional = Optional.empty();
        for (Conductor conductor : driverList) {
            if (id == conductor.getId()) {
                opcional = Optional.of(conductor);
                return opcional;
            }
        }
        return opcional;
    }

    public void solicitarCarga(Integer conductorId, Integer idUsuario) {
        Optional<Conductor> conductorOptional = getConductor(conductorId);
        Optional<Usuario> usuarioOptional = getUser(idUsuario);

        if (conductorOptional.isPresent() && usuarioOptional.isPresent()) {
            Conductor conductor = conductorOptional.get();
            Usuario usuario = usuarioOptional.get();

            if (conductor.isDisponibilidad()) {
                // Actualizar el conductor en la lista driverList
                int conductorIndex = driverList.indexOf(conductor);
                Conductor conductorActualizado = new Conductor(
                        conductor.getId(),
                        conductor.getNombre(),
                        conductor.getCapacidadCamion(),
                        conductor.isDisponibilidad(),
                        usuario.getDireccionPaquete(),
                        conductor.getPlaca(),
                        usuario.getDescripcionPaquete()
                );
                driverList.set(conductorIndex, conductorActualizado);

                // Actualizar el usuario en la lista userList
                int usuarioIndex = userList.indexOf(usuario);
                Usuario usuarioActualizado = new Usuario(
                        usuario.getId(),
                        usuario.getNombre(),
                        usuario.getEdad(),
                        usuario.getEmail(),
                        usuario.getDescripcionPaquete(),
                        true,
                        usuario.getDireccionPaquete()
                );
                userList.set(usuarioIndex, usuarioActualizado);
            }
        } else {
            throw new IllegalArgumentException("El ID de usuario o conductor no existe");
        }
    }

    public String recepcionPaquete(Conductor conductor){
        SimpleDateFormat formatoFechaHora = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String fechaHoraFormateada = formatoFechaHora.format(new Date());
        conductor.setDisponibilidad(false);

        return "Fecha y hora: " + fechaHoraFormateada + "\nOrigen: " + conductor.getRuta() +"\nPlaca: " +conductor.getPlaca() +"\n Contenido: " + conductor.getContenido();

    }

    public Optional<Usuario> consultarSolicitudes(){
        Optional<Usuario> opcional = Optional.empty();
        for (Usuario usuario : userList) {
            if (usuario.isSolicitud()) {
                opcional = Optional.of(usuario);
                return opcional;
            }
        }
        return opcional;
    }


}
