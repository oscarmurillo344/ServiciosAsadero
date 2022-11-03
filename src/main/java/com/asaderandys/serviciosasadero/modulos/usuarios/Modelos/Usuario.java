package com.asaderandys.serviciosasadero.modulos.usuarios.Modelos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import javax.persistence.GeneratedValue;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nombre;

    @NotNull
    private String apellido;

    @NotNull
    @Column(unique = true)
    private String nombreUsuario;


    @NotNull
    private String contrasena;

    @NotNull
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Rol> roles = new HashSet<>();

    public Usuario() {
    }

    public Usuario(Long id,String nombre, String apellido, String nombreUsuario, String password) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.nombreUsuario = nombreUsuario;
        this.contrasena = password;
    }

    public Usuario(Long id,String nombre, String apellido, String nombreUsuario, String password,Set<Rol> rol) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.nombreUsuario = nombreUsuario;
        this.contrasena = password;
        this.roles = rol;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getPassword() {
        return contrasena;
    }

    public void setPassword(String password) {
        this.contrasena = password;
    }

    public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }

    private static final long serialVersionUID = 1L;
}
