package br.com.softbank.resttemplate.utils;

public class ExternalUrls {
	
	public static final String INTERNAL_USUARIOS_PATH = "/usuarios";
	public static final String INTERNAL_OAUTH2_PATH = "/oauth";

	public static final String EXTERNAL_BASE_PATH = "http://localhost:8765/api";
	public static final String EXTERNAL_USUARIOS_PATH = EXTERNAL_BASE_PATH + "/usuarios/v1/usuarios";
	
	public static final String EXTERNAL_OAUTH2_USUARIO_CLAIMS = EXTERNAL_BASE_PATH + "/oauth2/v1/oauth/usuario";
}
