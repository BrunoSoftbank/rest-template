package br.com.softbank.resttemplate.integration;

import static br.com.softbank.resttemplate.utils.ExternalUrls.EXTERNAL_OAUTH2_USUARIO_CLAIMS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import br.com.softbank.resttemplate.response.UsuarioResponse;

@Component
public class Oauht2Integration {
	
	@Autowired
	private RestTemplate restTemplate;

	public UsuarioResponse getUserClaims(String Authorization) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", Authorization);		
		HttpEntity<UsuarioResponse> entity = new HttpEntity<UsuarioResponse>(headers);
		ResponseEntity<UsuarioResponse> response = restTemplate.exchange(EXTERNAL_OAUTH2_USUARIO_CLAIMS, HttpMethod.GET, entity, UsuarioResponse.class);	
		return response.getBody();
	}

}
