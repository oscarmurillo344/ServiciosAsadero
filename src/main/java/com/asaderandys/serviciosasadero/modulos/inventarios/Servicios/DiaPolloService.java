package com.asaderandys.serviciosasadero.modulos.inventarios.Servicios;


import com.asaderandys.serviciosasadero.modulos.inventarios.Dto.actualizarPollo;
import com.asaderandys.serviciosasadero.modulos.inventarios.Modelos.DiaPollo;

import com.asaderandys.serviciosasadero.modulos.inventarios.Repositorios.diaPolloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DiaPolloService {

    @Autowired
    diaPolloRepository diaRepo;


    public List<DiaPollo> Listar(){return diaRepo.findAll();}

    public DiaPollo Ingresar(DiaPollo pollo){return diaRepo.save(pollo);}

    public void ActualizarTablaPollo(actualizarPollo update){
        DiaPollo dia;
        if(diaRepo.findAll().size() <= 0){
            dia=new DiaPollo(0,0);
        }else {
            dia=diaRepo.getById(1L);
            dia.setPollo(update.getPollo());
            dia.setPresa(update.getPresa());
        }
        diaRepo.save(dia);
    }
}
