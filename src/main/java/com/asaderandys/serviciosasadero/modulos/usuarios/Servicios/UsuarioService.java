package com.asaderandys.serviciosasadero.modulos.usuarios.Servicios;

import com.asaderandys.serviciosasadero.modulos.usuarios.Dto.ListaUsuario;
import com.asaderandys.serviciosasadero.modulos.usuarios.Dto.RolNombre;
import com.asaderandys.serviciosasadero.modulos.usuarios.Dto.UsuarioResponse;
import com.asaderandys.serviciosasadero.modulos.usuarios.Modelos.Rol;
import com.asaderandys.serviciosasadero.modulos.usuarios.Modelos.Usuario;
import com.asaderandys.serviciosasadero.modulos.usuarios.Repositorio.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    RolService rolService;

    public Usuario ObtenerPorNombreUsuario(String nombreUsuario)throws DataAccessException{
        return usuarioRepository.findByNombreUsuario(nombreUsuario);
    }

    public boolean ExistePorNombreUsuario(String nombreUsuario)throws DataAccessException{
        return usuarioRepository.existsByNombreUsuario(nombreUsuario);
    }
    public void NuevoUsuario(Usuario usuario) throws DataAccessException {
        usuarioRepository.save(usuario);
    }

    public boolean ExisteUsuario(Long id)throws DataAccessException
    {return usuarioRepository.existsById(id);}

    public void EliminarUsuario(Long id)throws DataAccessException
    {usuarioRepository.deleteById(id);}

    public List<ListaUsuario> ListarUsuario(){
        List<Usuario> lista = usuarioRepository.findAll();
        List<ListaUsuario> lista2 = new ArrayList<>();
        for ( Usuario usuario: lista ) {
                lista2.add(new ListaUsuario(
                        usuario.getId(),
                        usuario.getNombre(),
                        usuario.getApellido(),
                        usuario.getNombreUsuario(),
                        usuario.getRoles()
                ));

        }
        return lista2;
    }
}
