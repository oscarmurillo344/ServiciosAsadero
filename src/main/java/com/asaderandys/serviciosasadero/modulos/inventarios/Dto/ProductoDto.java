package com.asaderandys.serviciosasadero.modulos.inventarios.Dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public class ProductoDto {

    @NotBlank
    private Long id;
    @NotBlank
    private String nombre;
    @NotBlank
    private String tipo;
    @Min(0)
    private BigDecimal precio;
    @Min(0)
    private Integer presa;

    public ProductoDto() {
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
