package gob.pe.proc.validadores;

import gob.pe.proc.capadatos.Estado;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class EstadoValidador implements Validator {

	public boolean supports(Class<?> clazz) {
		return Estado.class.isAssignableFrom(clazz);
	}
	
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "denominacion", "estado.denominacion.obligatorio");

	}

}
