package com.asaderandys.serviciosasadero.modulos.clientes.Modelos;

import com.asaderandys.serviciosasadero.modulos.facturar.Modelos.Factura;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    private String apellido;

    @Column(unique = true)
    private String cedula;

    @Column(nullable = false, unique = true)
    private String email;

    @Temporal(TemporalType.DATE)
    private Date FechaIngreso;

    @JsonIgnoreProperties(value={"cliente", "hibernateLazyInitializer", "handler"}, allowSetters=true)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Factura> facturas;

    public Cliente(String nombre, String apellido, String cedula, String email, Date fechaIngreso, List<Factura> facturas) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.email = email;
        FechaIngreso = fechaIngreso;
        this.facturas = facturas;
    }
    public Cliente(){}
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

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getFechaIngreso() {
        return FechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        FechaIngreso = fechaIngreso;
    }

    public List<Factura> getFacturas() {
        return facturas;
    }

    public void setFacturas(List<Factura> facturas) {
        this.facturas = facturas;
    }
}
