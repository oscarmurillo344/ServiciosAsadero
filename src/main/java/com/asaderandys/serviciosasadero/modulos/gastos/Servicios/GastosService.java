package com.asaderandys.serviciosasadero.modulos.gastos.Servicios;


import com.asaderandys.serviciosasadero.modulos.gastos.Modelos.Gastos;
import com.asaderandys.serviciosasadero.modulos.gastos.Repositorios.GastosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;


@Service
@Transactional
public class GastosService {

    @Autowired
    GastosRepository gastosrepo;

    public List<Gastos> Listar(){return gastosrepo.findAll();}

    public void Guardar(Gastos gasto){gastosrepo.save(gasto);}

    public void eliminar(Long id){gastosrepo.deleteById(id);}

    public List<Gastos> ListarTipo(Calendar desde, Calendar hasta,String tipo){
        return  gastosrepo.BuscarxFechaxTipo(desde, hasta,tipo);}

    public List<Gastos> ListarxFechaxUserxTipo(Calendar desde,Calendar hasta,String user,String tipo){
        return  gastosrepo.BuscarxFechaxUserxTipo(desde,hasta,user,tipo);}

    public List<Gastos> ListarxFechaxUser(Calendar desde,Calendar hasta,String user){
        return  gastosrepo.BuscarxFechaxUser(desde,hasta,user);}

    public List<Gastos> ListarxFecha(Calendar desde, Calendar hasta){
        return  gastosrepo.BuscarxFecha(desde,hasta);}

    public boolean existsById(Long id){return gastosrepo.existsById(id);}
}
