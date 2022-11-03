package com.asaderandys.serviciosasadero.modulos.usuarios.Servicios;

import com.asaderandys.serviciosasadero.modulos.usuarios.Dto.RolNombre;
import com.asaderandys.serviciosasadero.modulos.usuarios.Modelos.Rol;
import com.asaderandys.serviciosasadero.modulos.usuarios.Repositorio.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class RolService {

    @Autowired
    RolRepository rolRepository;

    public Optional<Rol> ObtenerRol(RolNombre rolNombre){ return rolRepository.findByRolNombre(rolNombre); }
    public void NuevoRol(Rol rol){ rolRepository.save(rol); }
    public void EliminarRol(long id){rolRepository.deleteById(id);}
    public boolean ExisteRol(long id){return rolRepository.existsById(id);}
}

