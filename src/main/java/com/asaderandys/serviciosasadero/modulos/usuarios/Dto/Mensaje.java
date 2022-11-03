package com.asaderandys.serviciosasadero.modulos.usuarios.Dto;

public class Mensaje {

    private String mensaje;
    private Object Cuerpo;

    public Mensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    public Mensaje(String mensaje, Object cuerpo) {
        this.mensaje = mensaje;
        Cuerpo = cuerpo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Object getCuerpo() {
        return Cuerpo;
    }

    public void setCuerpo(Object cuerpo) {
        Cuerpo = cuerpo;
    }
}
