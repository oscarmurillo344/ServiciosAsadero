package com.asaderandys.serviciosasadero.modulos.inventarios.Repositorios;


import com.asaderandys.serviciosasadero.modulos.inventarios.Modelos.Inventario;
import com.asaderandys.serviciosasadero.modulos.inventarios.Modelos.Producto;
import org.springframework.data.jpa.repository.JpaRepository;


public interface inventarioRepository extends JpaRepository<Inventario, Long> {

    Inventario findByProducto(Producto p);
}
