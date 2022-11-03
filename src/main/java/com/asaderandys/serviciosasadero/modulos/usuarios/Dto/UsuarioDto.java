package com.asaderandys.serviciosasadero.modulos.usuarios.Dto;

import com.asaderandys.serviciosasadero.modulos.usuarios.Modelos.Rol;

import java.io.Serializable;
import java.util.Set;

public class UsuarioDto implements Serializable {

    Long id;
    String nombre;
    String apellido;
    String nombreUsuario;
    Set<Rol> roles;

    public UsuarioDto(){}
    public UsuarioDto(Long id, String nombre, String apellido, String nombreUsuario, Set<Rol> roles) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.nombreUsuario = nombreUsuario;
        this.roles = roles;
    }
}
