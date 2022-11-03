package com.asaderandys.serviciosasadero.modulos.clientes.Servicios;

import com.asaderandys.serviciosasadero.modulos.clientes.Dto.ClienteDto;
import com.asaderandys.serviciosasadero.modulos.clientes.Modelos.Cliente;
import com.asaderandys.serviciosasadero.modulos.clientes.Repositorios.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    public Cliente Guardar(ClienteDto cliente){
        Cliente nuevo = null;
        if(cliente.getId() == 0){
            nuevo = new Cliente(cliente.getNombre(),cliente.getApellido(),
                    cliente.getCedula(),cliente.getEmail(),cliente.getFechaIngreso(),cliente.getFacturas());
        }else{
            nuevo = clienteRepository.findById(cliente.getId()).get();
            nuevo.setNombre(cliente.getNombre());
            nuevo.setApellido(cliente.getApellido());
            nuevo.setCedula(cliente.getCedula());
            nuevo.setEmail(cliente.getEmail());
            nuevo.setFechaIngreso(cliente.getFechaIngreso());
        }
        return nuevo;
    }
}
