package com.asaderandys.serviciosasadero.modulos.inventarios.Modelos;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Calendar;

@Entity
@Table(name = "inventarios")
public class Inventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar fecha;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(fetch = FetchType.LAZY)
    private Producto producto;

    private String extras;

    @NotNull
    private Integer cantidad;

    @NotNull
    private Integer cantidadExiste;

    @NotNull
    private Integer cantidadTotal;

    public Inventario(){}

    public Inventario(@NotNull Calendar fecha, Producto producto, String extras, @NotNull Integer cantidad, @NotNull Integer cantidadExiste, @NotNull Integer cantidadTotal) {
        this.fecha = fecha;
        this.producto = producto;
        this.extras = extras;
        this.cantidad = cantidad;
        this.cantidadExiste = cantidadExiste;
        this.cantidadTotal = cantidadTotal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getFecha() {
        return fecha;
    }

    public void setFecha(Calendar fecha) {
        this.fecha = fecha;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public String getExtras() {
        return extras;
    }

    public void setExtras(String extras) {
        this.extras = extras;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getCantidadExiste() {
        return cantidadExiste;
    }

    public void setCantidadExiste(Integer cantidadExist) {
        this.cantidadExiste = cantidadExist;
    }

    public Integer getCantidadTotal() {
        return cantidadTotal;
    }

    public void setCantidadTotal(Integer cantidadTotal) {
        this.cantidadTotal = cantidadTotal;
    }
}
