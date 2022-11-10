package com.asaderandys.serviciosasadero.modulos.inventarios.Modelos;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "diapollos")
public class DiaPollo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Integer pollo;

    @NotNull
    private Integer presa;

    public DiaPollo(){}

    public DiaPollo(@NotNull Integer pollo, @NotNull Integer presa) {
        this.pollo = pollo;
        this.presa = presa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
