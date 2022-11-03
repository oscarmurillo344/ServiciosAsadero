package com.asaderandys.serviciosasadero.modulos.usuarios.Repositorio;

import com.asaderandys.serviciosasadero.modulos.usuarios.Dto.RolNombre;
import com.asaderandys.serviciosasadero.modulos.usuarios.Modelos.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {
    Optional<Rol> findByRolNombre(RolNombre rolNombre);
}
