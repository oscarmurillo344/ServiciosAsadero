package com.asaderandys.serviciosasadero.modulos.inventarios.Repositorios;


import com.asaderandys.serviciosasadero.modulos.inventarios.Modelos.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    Optional<Producto> findByNombre(String nombre);
    boolean existsByNombre(String nombre);
}
