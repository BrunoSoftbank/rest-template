package br.com.softbank.resttemplate.exception;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.google.gson.Gson;

import br.com.softbank.resttemplate.response.ErrorDefault;
import br.com.softbank.resttemplate.response.ResponseDefault;


@RestControllerAdvice
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {
	
	private static final Logger LOG = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);
	
	@ExceptionHandler(HttpClientErrorException.class)
	public ResponseEntity<ResponseDefault> httpClientErrorException(HttpClientErrorException ex, HttpServletRequest request) {	
		LOG.error(this.getClass().getSimpleName() + ".httpClientErrorException(HttpClientErrorException ex, HttpServletRequest request) " + ex.getMessage());	
		
		if(ex.getRawStatusCode() == 401 || ex.getRawStatusCode() == 403) {
			return new ResponseEntity<>(new ResponseDefault("Usuário não autorizado", null), HttpStatus.valueOf(ex.getRawStatusCode()));
		}
		return new ResponseEntity<>(new Gson().fromJson(ex.getResponseBodyAsString(), ResponseDefault.class), HttpStatus.valueOf(ex.getRawStatusCode()));
	}	
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<ErrorDefault> errors = ex.getBindingResult().getFieldErrors()
        		.stream().map(error -> new ErrorDefault(error.getDefaultMessage()))
                .collect(Collectors.toList());
        
        return new ResponseEntity<>(new ResponseDefault("Invalid fields", errors), status);
	}
}
