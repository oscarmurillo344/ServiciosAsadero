package com.asaderandys.serviciosasadero.modulos.usuarios.Dto;

import com.asaderandys.serviciosasadero.modulos.usuarios.Modelos.Rol;

import java.util.List;
import java.util.Set;

public class ListaUsuario {

    private Long id;
    private String nombre;
    private String apellido;
    private String nombreUsuario;
    private Set<Rol> roles;

    public ListaUsuario(Long id, String nombre, String apellido, String nombreUsuario, Set<Rol> roles) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.nombreUsuario = nombreUsuario;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public Set<Rol> getRoles() {
        return this.roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }
}
