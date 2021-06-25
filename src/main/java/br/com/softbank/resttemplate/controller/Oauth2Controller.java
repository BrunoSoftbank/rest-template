package br.com.softbank.resttemplate.controller;

import static br.com.softbank.resttemplate.utils.ExternalUrls.INTERNAL_OAUTH2_PATH;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.softbank.resttemplate.response.UsuarioResponse;
import br.com.softbank.resttemplate.service.Oauht2Service;

@RequestMapping(INTERNAL_OAUTH2_PATH)
@RestController
public class Oauth2Controller {
	
	@Autowired
	private Oauht2Service oauth2Service;
	
	@GetMapping
	public ResponseEntity<UsuarioResponse> getUserClaims(@RequestHeader String Authorization) {
		return ResponseEntity.ok(oauth2Service.getUserClaims(Authorization));
	}
}
