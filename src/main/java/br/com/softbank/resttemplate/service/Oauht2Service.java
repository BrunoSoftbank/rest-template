package br.com.softbank.resttemplate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.softbank.resttemplate.integration.Oauht2Integration;
import br.com.softbank.resttemplate.response.UsuarioResponse;

@Service
public class Oauht2Service {
	
	@Autowired
	private Oauht2Integration oauht2Integration;

	public UsuarioResponse getUserClaims(String Authorization) {
		return oauht2Integration.getUserClaims(Authorization);
	}
}
