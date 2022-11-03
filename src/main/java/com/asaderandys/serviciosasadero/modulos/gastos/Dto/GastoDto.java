package com.asaderandys.serviciosasadero.modulos.gastos.Dto;

import com.asaderandys.serviciosasadero.modulos.usuarios.Modelos.Usuario;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class GastoDto {

    @NotBlank
    private String tipo;

    @NotBlank
    private String usuario;

    @NotNull
    private BigDecimal valor;
    @NotNull
    private String descripcion;

    public GastoDto(){}

    public GastoDto(@NotBlank String tipo, @NotBlank String usuario, @NotNull BigDecimal valor, @NotNull String descripcion) {
        this.tipo = tipo;
        this.usuario = usuario;
        this.valor = valor;
        this.descripcion = descripcion;
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
