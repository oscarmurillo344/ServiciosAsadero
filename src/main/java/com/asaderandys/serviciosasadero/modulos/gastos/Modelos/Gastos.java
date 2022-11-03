package com.asaderandys.serviciosasadero.modulos.gastos.Modelos;

import com.asaderandys.serviciosasadero.modulos.usuarios.Modelos.Usuario;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Calendar;

@Entity
public class Gastos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String tipo;

    private String usuario;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar fecha;
    @NotNull
    private BigDecimal valor;
    @NotNull
    private String descripcion;

    public Gastos(){}

    public Gastos(@NotNull String tipo, String usuario, @NotNull Calendar fecha, @NotNull BigDecimal valor, @NotNull String descripcion) {
        this.tipo = tipo;
        this.usuario = usuario;
        this.fecha = fecha;
        this.valor = valor;
        this.descripcion = descripcion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Calendar getFecha() {
        return fecha;
    }

    public void setFecha(Calendar fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
