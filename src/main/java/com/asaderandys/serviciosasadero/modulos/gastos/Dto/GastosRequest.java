package com.asaderandys.serviciosasadero.modulos.gastos.Dto;

import java.util.Calendar;

public class GastosRequest {

    String usuario;
    String tipo;
    Calendar inicial;
    Calendar fin;

    public GastosRequest(){}

    public GastosRequest(String usuario, String tipo, Calendar inicial, Calendar fin) {
        this.usuario = usuario;
        this.tipo = tipo;
        this.inicial = inicial;
        this.fin = fin;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Calendar getInicial() {
        return inicial;
    }

    public void setInicial(Calendar inicial) {
        this.inicial = inicial;
    }

    public Calendar getFin() {
        return fin;
    }

    public void setFin(Calendar fin) {
        this.fin = fin;
    }
}
