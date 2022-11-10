package com.asaderandys.serviciosasadero.modulos.inventarios.Modelos;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String nombre;

    @NotNull
    private String tipo;

    @NotNull
    private BigDecimal precio;

    @NotNull
    private Integer presa;


    public Producto() {
    }

    public Producto(@NotNull String nombre, @NotNull String tipo, @NotNull BigDecimal precio, @NotNull Integer presa) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.precio = precio;
        this.presa = presa;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Integer getPresa() {
        return presa;
    }

    public void setPresa(Integer presa) {
        this.presa = presa;
    }
}
