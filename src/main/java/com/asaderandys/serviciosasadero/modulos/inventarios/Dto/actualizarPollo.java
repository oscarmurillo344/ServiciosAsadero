package com.asaderandys.serviciosasadero.modulos.inventarios.Dto;

public class actualizarPollo {

    Integer pollo;
    Integer presa;

    public actualizarPollo(){}
    public actualizarPollo(Integer pollo, Integer presa) {
        this.pollo = pollo;
        this.presa = presa;
    }

    public Integer getPollo() {
        return pollo;
    }

    public void setPollo(Integer pollo) {
        this.pollo = pollo;
    }

    public Integer getPresa() {
        return presa;
    }

    public void setPresa(Integer presa) {
        this.presa = presa;
    }
}
