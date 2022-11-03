package com.asaderandys.serviciosasadero.modulos.usuarios.Repositorio;


import com.asaderandys.serviciosasadero.modulos.usuarios.Dto.UsuarioResponse;
import com.asaderandys.serviciosasadero.modulos.usuarios.Modelos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByNombreUsuario(String nombreUsuario);

    boolean existsByNombreUsuario(String nombreUsuario);

    @Query(value = "SELECT u.id         AS id, " +
                          "u.nombre     AS nombre, " +
                          "u.apellido   AS apellido, " +
                          "u.nombreUsuario AS nombreUsuario, " +
                          "r.rolNombre AS rolNombre " +
                    "FROM Usuario u " +
                    "INNER JOIN u.roles r")
    List<UsuarioResponse> ListarUsuarios();
}
