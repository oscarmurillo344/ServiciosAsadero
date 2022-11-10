package com.asaderandys.serviciosasadero.modulos.facturar.Dto;


import com.asaderandys.serviciosasadero.modulos.facturar.Modelos.FacturaItems;

import java.util.Date;
import java.util.List;

public class FacturaDto {

    private Long id;
    private Integer numeroFactura;
    private String usuarioId;
    private String FormaPago;
    private Date FechaIngreso;
    private Date HoraIngreso;
    private String DiaIngreso;
    private List<FacturaItems> facturaItem;

     public FacturaDto(){

     }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(Integer numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getFormaPago() {
        return FormaPago;
    }

    public void setFormaPago(String formaPago) {
        FormaPago = formaPago;
    }

    public Date getFechaIngreso() {
        return FechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        FechaIngreso = fechaIngreso;
    }

    public Date getHoraIngreso() {
        return HoraIngreso;
    }

    public void setHoraIngreso(Date horaIngreso) {
        HoraIngreso = horaIngreso;
    }

    public String getDiaIngreso() {
        return DiaIngreso;
    }

    public void setDiaIngreso(String diaIngreso) {
        DiaIngreso = diaIngreso;
    }

    public List<FacturaItems> getFacturaItem() {
        return facturaItem;
    }

    public void setFacturaItem(List<FacturaItems> facturaItem) {
        this.facturaItem = facturaItem;
    }
}
