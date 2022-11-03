package com.asaderandys.serviciosasadero.modulos.facturar.Modelos;


import com.asaderandys.serviciosasadero.modulos.clientes.Modelos.Cliente;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Integer numeroFactura;

    @NotNull
    private String usuarioId;

    @NotNull
    private String FormaPago;

    @NotNull
    @Temporal(TemporalType.DATE)
    private Date FechaIngreso;

    @NotNull
    @Temporal(TemporalType.TIME)
    private Date HoraIngreso;

    @NotNull
    private String DiaIngreso;

    @JsonIgnoreProperties(value={"facturas", "hibernateLazyInitializer", "handler"}, allowSetters=true)
    @ManyToOne(fetch = FetchType.LAZY)
    private Cliente cliente;

    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<FacturaItems> facturaItem;

    public Factura(){
    }

    public Factura(@NotNull Integer numeroFactura, @NotNull String usuarioId, @NotNull String formaPago, @NotNull Date fechaIngreso, @NotNull Date horaIngreso, @NotNull String diaIngreso, List<FacturaItems> facturaItem) {
        this.numeroFactura = numeroFactura;
        this.usuarioId = usuarioId;
        FormaPago = formaPago;
        FechaIngreso = fechaIngreso;
        HoraIngreso = horaIngreso;
        DiaIngreso = diaIngreso;
        this.facturaItem = facturaItem;
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

    public Long getSubTotalImporte(){
        Long Total = 0L;
        for(FacturaItems item: facturaItem){
           Total =+ item.getImporte();
        }
        return Total;
    }

    public Long getIVA(){
        return (getSubTotalImporte().longValue()*12)/100;
    }

    public Long getSubDescuento(Integer valor){
        if(valor > 0){
            return (getSubTotalImporte().longValue()*valor)/100;
        }
        return 0L;
    }


}