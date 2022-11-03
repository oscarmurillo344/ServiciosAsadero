package com.asaderandys.serviciosasadero.modulos.clientes.Repositorios;

import com.asaderandys.serviciosasadero.modulos.clientes.Modelos.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface ClienteRepository extends CrudRepository<Cliente,Long> {
}
