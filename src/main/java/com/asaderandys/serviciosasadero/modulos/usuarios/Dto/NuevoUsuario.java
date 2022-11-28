package com.asaderandys.serviciosasadero.modulos.usuarios.Dto;

import com.asaderandys.serviciosasadero.modulos.usuarios.Modelos.Rol;
import jakarta.validation.constraints.NotBlank;
import java.util.Set;

public class NuevoUsuario {

    @NotBlank
    private String nombre;
    @NotBlank
    private  String apellido;
    @NotBlank
    private String nombreUsuario;
    @NotBlank
    private String password;

    private Set<Rol> roles;

    public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }

    public NuevoUsuario(){
    }
    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
