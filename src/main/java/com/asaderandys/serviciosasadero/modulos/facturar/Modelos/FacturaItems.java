package com.asaderandys.serviciosasadero.modulos.facturar.Modelos;

import com.asaderandys.serviciosasadero.modulos.inventarios.Modelos.Producto;
import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class FacturaItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Integer cantidad;

    private String extras;

    @ManyToOne(fetch = FetchType.LAZY)
    private Producto producto;

    @NotNull
    private Integer Descuento;

    @NotNull
    private BigDecimal MontoPago;

    public FacturaItems() { }

    public FacturaItems(@NotNull Integer cantidad, String extras, Producto producto, @NotNull Integer descuento, @NotNull BigDecimal montoPago) {
        this.cantidad = cantidad;
        this.extras = extras;
        this.producto = producto;
        Descuento = descuento;
        MontoPago = montoPago;
    }

    public String getExtras() {
        return extras;
    }

    public void setExtras(String extras) {
        this.extras = extras;
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

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Integer getDescuento() {
        return Descuento;
    }

    public void setDescuento(Integer descuento) {
        Descuento = descuento;
    }

    public BigDecimal getMontoPago() {
        return MontoPago;
    }

    public void setMontoPago(BigDecimal montoPago) {
        MontoPago = montoPago;
    }

    public Long getImporte(){
        return this.cantidad * this.producto.getPrecio().longValue();
    }
}
