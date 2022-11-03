package com.asaderandys.serviciosasadero.modulos.inventarios.Dto;


import com.asaderandys.serviciosasadero.modulos.inventarios.Modelos.Producto;

public class inventarioDto {

    private Long id;
    private Producto producto;
    private String extras;
    private Integer cantidad;
    private Integer cantidadExiste;

    public inventarioDto(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setCantidadExiste(Integer cantidadExiste) {
        this.cantidadExiste = cantidadExiste;
    }
}
