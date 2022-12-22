package com.asaderandys.serviciosasadero.modulos.facturar.Dto;

import com.asaderandys.serviciosasadero.modulos.inventarios.Modelos.Producto;
import com.sun.istack.NotNull;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

public class FacturaItemsDto {

    private Long id;
    private Integer cantidad;
    private String extras;
    private Producto producto;
    private Integer descuento;
    private BigDecimal montoPago;

    public FacturaItemsDto(Long id, Integer cantidad, String extras, Producto producto, Integer descuento, BigDecimal montoPago) {
        this.id = id;
        this.cantidad = cantidad;
        this.extras = extras;
        this.producto = producto;
        this.descuento = descuento;
        this.montoPago = montoPago;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getExtras() {
        return extras;
    }

    public void setExtras(String extras) {
        this.extras = extras;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Integer getDescuento() {
        return descuento;
    }

    public void setDescuento(Integer descuento) {
        this.descuento = descuento;
    }

    public BigDecimal getMontoPago() {
        return montoPago;
    }

    public void setMontoPago(BigDecimal montoPago) {
        this.montoPago = montoPago;
    }
}
