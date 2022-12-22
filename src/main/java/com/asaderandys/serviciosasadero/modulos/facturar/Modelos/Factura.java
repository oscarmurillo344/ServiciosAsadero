package com.asaderandys.serviciosasadero.modulos.facturar.Modelos;


import com.asaderandys.serviciosasadero.modulos.clientes.Modelos.Cliente;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String usuario;

    @NotNull
    private String formaPago;

    @NotNull
    @Temporal(TemporalType.DATE)
    private Date fechaIngreso;

    @NotNull
    @Temporal(TemporalType.TIME)
    private Date horaIngreso;

    @NotNull
    private String diaIngreso;

    @JsonIgnoreProperties(value={"cliente", "hibernateLazyInitializer", "handler"}, allowSetters=true)
    @ManyToOne(fetch = FetchType.LAZY)
    private Cliente cliente;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<FacturaItems> facturaItem;

    public Factura(){
    }

    public Factura(@NotNull String usuarioId, @NotNull String formaPago, @NotNull Date fechaIngreso, @NotNull Date horaIngreso, @NotNull String diaIngreso, List<FacturaItems> facturaItem) {
        this.usuario = usuarioId;
        this.formaPago = formaPago;
        this.fechaIngreso = fechaIngreso;
        this.horaIngreso = horaIngreso;
        this.diaIngreso = diaIngreso;
        this.facturaItem = facturaItem;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
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

    public Date getHoraIngreso() {
        return horaIngreso;
    }

    public void setHoraIngreso(Date horaIngreso) {
        this.horaIngreso = horaIngreso;
    }

    public String getDiaIngreso() {
        return diaIngreso;
    }

    public void setDiaIngreso(String diaIngreso) {
        this.diaIngreso = diaIngreso;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<FacturaItems> getFacturaItem() {
        return facturaItem;
    }

    public void setFacturaItem(List<FacturaItems> facturaItem) {
        this.facturaItem = facturaItem;
    }

    public Float getSubTotalImporte(){
        float Total = 0;
        for(FacturaItems item: facturaItem){
           Total = Total + item.getImporte();
        }
        return Total;
    }

    public Float getIVA(){
        return (float) (getSubTotalImporte().longValue() * 12) / 100;
    }

}