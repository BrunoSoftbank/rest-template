package br.com.softbank.resttemplate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.softbank.resttemplate.integration.UsuarioIntegration;
import br.com.softbank.resttemplate.request.UsuarioRequest;
import br.com.softbank.resttemplate.response.UsuarioResponse;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioIntegration usuarioIntegration;

	public UsuarioResponse save(UsuarioRequest request) {
		return usuarioIntegration.save(request);
	}

	public void activeUser(String token) {
		usuarioIntegration.activeUser(token);	
	}

	public UsuarioResponse findById(String Authorization, Long id) {
		return usuarioIntegration.findById(Authorization, id);
	}

	public List<UsuarioResponse> findAll(String Authorization, int page, int limit) {
		return usuarioIntegration.findAll(Authorization, page, limit);
	}
}
