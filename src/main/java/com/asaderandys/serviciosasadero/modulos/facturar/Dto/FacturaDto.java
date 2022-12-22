package com.asaderandys.serviciosasadero.modulos.facturar.Dto;


import java.util.Date;
import java.util.List;

public class FacturaDto {

    private String usuario;
    private String formaPago;
    private Date fechaIngreso;
    private String diaIngreso;
    private List<FacturaItemsDto> facturaItem;

    public FacturaDto() {

    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuarioId) {
        this.usuario = usuarioId;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }
    public String getDiaIngreso() {
        return diaIngreso;
    }

    public void setDiaIngreso(String diaIngreso) {
        this.diaIngreso = diaIngreso;
    }

    public List<FacturaItemsDto> getFacturaItem() {
        return facturaItem;
    }

    public void setFacturaItem(List<FacturaItemsDto> facturaItem) {
        this.facturaItem = facturaItem;
    }
}