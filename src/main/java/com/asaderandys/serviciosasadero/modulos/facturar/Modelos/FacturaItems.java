package com.asaderandys.serviciosasadero.modulos.facturar.Modelos;

import com.asaderandys.serviciosasadero.modulos.inventarios.Modelos.Producto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    private Producto producto;

    @NotNull
    private Integer descuento;

    @NotNull
    private BigDecimal montoPago;

    public FacturaItems() { }

    public FacturaItems(@NotNull Integer cantidad, String extras, Producto producto, @NotNull Integer descuento, @NotNull BigDecimal montoPago) {
        this.cantidad = cantidad;
        this.extras = extras;
        this.producto = producto;
        this.descuento = descuento;
        this.montoPago = montoPago;
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
        return descuento;
    }

    public void setDescuento(Integer descuento) {
        descuento = descuento;
    }

    public BigDecimal getMontoPago() {
        return montoPago;
    }

    public void setMontoPago(BigDecimal montoPago) {
        montoPago = montoPago;
    }

    public Float getImporte(){
        return this.cantidad * this.producto.getPrecio().floatValue();
    }

    public Float getAplyDescuento(){
        if(this.descuento > 0){
            return (float)((getImporte() * getMontoPago().floatValue())/100);
        }
        return (float) 0;
    }
}
