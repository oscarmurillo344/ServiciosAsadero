package com.asaderandys.serviciosasadero.modulos.usuarios.Servicios;

import com.asaderandys.serviciosasadero.modulos.usuarios.Modelos.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceDetail implements  UserDetailsService{
	
	private Logger logger = LoggerFactory.getLogger(UsuarioService.class);

	@Autowired
	private UsuarioService usuarioDao;
	
	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Usuario usuario = usuarioDao.ObtenerPorNombreUsuario(username);
		
		if(usuario == null) {
			logger.error("Error en el login: no existe el usuario '"+username+"' en el sistema!");
			throw new UsernameNotFoundException("Error en el login: no existe el usuario '"+username+"' en el sistema!");
		}
		
		List<GrantedAuthority> authorities = usuario.getRoles()
				.stream()
				.map(role -> new SimpleGrantedAuthority(role.getRolNombre().name()))
				.peek(authority -> logger.info("Role: " + authority.getAuthority()))
				.collect(Collectors.toList());

		
		return new User(usuario.getNombreUsuario(), usuario.getPassword(), true,
				true, true, true, authorities);
	}
}
