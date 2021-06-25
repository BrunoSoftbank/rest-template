package br.com.softbank.resttemplate.controller;

import static br.com.softbank.resttemplate.utils.ExternalUrls.INTERNAL_USUARIOS_PATH;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.softbank.resttemplate.request.UsuarioRequest;
import br.com.softbank.resttemplate.response.UsuarioResponse;
import br.com.softbank.resttemplate.service.UsuarioService;

@RequestMapping(INTERNAL_USUARIOS_PATH)
@RestController
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping
	public ResponseEntity<UsuarioResponse> save(@Valid @RequestBody UsuarioRequest request) {
		return new ResponseEntity<UsuarioResponse>(usuarioService.save(request), HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<Void> activeUser(@RequestParam String token) {
		usuarioService.activeUser(token);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioResponse> findById(@RequestHeader String Authorization, @PathVariable Long id) {
		return ResponseEntity.ok(usuarioService.findById(Authorization, id));
	}
	
	@GetMapping
	public ResponseEntity<List<UsuarioResponse>> findAll(@RequestHeader String Authorization,
			@RequestParam(name = "page", required = false, defaultValue = "0") int page,
			@RequestParam(name = "limit", required = false, defaultValue = "6") int limit) {
		return new ResponseEntity<List<UsuarioResponse>>(usuarioService.findAll(Authorization, page, limit), HttpStatus.OK);
	}

}
