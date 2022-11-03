package com.asaderandys.serviciosasadero.modulos.clientes.Dto;

import com.asaderandys.serviciosasadero.modulos.facturar.Modelos.Factura;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ClienteDto implements Serializable {

    private Long id;
    private String nombre;
    private String apellido;
    private String cedula;
    private String email;
    private Date FechaIngreso;
    private List<Factura> facturas;

    public ClienteDto() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Factura> getFacturas() {
        return facturas;
    }

    public void setFacturas(List<Factura> facturas) {
        this.facturas = facturas;
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
}
