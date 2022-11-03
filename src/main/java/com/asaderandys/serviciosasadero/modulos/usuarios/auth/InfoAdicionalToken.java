package com.asaderandys.serviciosasadero.modulos.usuarios.auth;

import com.asaderandys.serviciosasadero.modulos.usuarios.Modelos.Usuario;
import com.asaderandys.serviciosasadero.modulos.usuarios.Servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class InfoAdicionalToken implements TokenEnhancer{
	
	@Autowired
	private UsuarioService usuarioService;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		
		Usuario usuario = usuarioService.ObtenerPorNombreUsuario(authentication.getName());
		Map<String, Object> info = new HashMap<>();
		info.put("nombre", usuario.getNombre());
		info.put("apellido", usuario.getApellido());
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
		return accessToken;
	}

}
