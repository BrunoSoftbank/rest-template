package br.com.softbank.resttemplate.integration;

import static br.com.softbank.resttemplate.utils.ExternalUrls.EXTERNAL_USUARIOS_PATH;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import br.com.softbank.resttemplate.request.UsuarioRequest;
import br.com.softbank.resttemplate.response.UsuarioResponse;

@Component
public class UsuarioIntegration {
	
	@Autowired
	private RestTemplate restTemplate;

	public UsuarioResponse save(UsuarioRequest request) {		
		HttpEntity<UsuarioRequest> entity = new HttpEntity<UsuarioRequest>(request, new HttpHeaders());
		ResponseEntity<UsuarioResponse> response = restTemplate.exchange(EXTERNAL_USUARIOS_PATH, HttpMethod.POST, entity, UsuarioResponse.class);
		return response.getBody();
	}

	public void activeUser(String token) {
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("token", token);
		
		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(map, new HttpHeaders());
		restTemplate.exchange(EXTERNAL_USUARIOS_PATH, HttpMethod.PUT, entity, Void.class);	
	}

	public UsuarioResponse findById(String Authorization, Long id) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", Authorization);		
		HttpEntity<?> entity = new HttpEntity<>(headers);
		ResponseEntity<UsuarioResponse> response = restTemplate.exchange(EXTERNAL_USUARIOS_PATH.concat("/").concat(String.valueOf(id)), HttpMethod.GET, entity, UsuarioResponse.class);	
		return response.getBody();
	}

	public List<UsuarioResponse> findAll(String Authorization, int page, int limit) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", Authorization);
		
		MultiValueMap<String, Integer> map = new LinkedMultiValueMap<String, Integer>();
		map.add("page", page);
		map.add("limit", limit);
		
		HttpEntity<MultiValueMap<String, Integer>> entity = new HttpEntity<MultiValueMap<String, Integer>>(map, headers);
		ResponseEntity<List<UsuarioResponse>> response = restTemplate.exchange(EXTERNAL_USUARIOS_PATH, HttpMethod.GET, entity, new ParameterizedTypeReference<List<UsuarioResponse>>(){});
		
		return response.getBody();
	}

}
