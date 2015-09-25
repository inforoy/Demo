package gob.pe.proc.validadores;

import gob.pe.proc.capadatos.Naturaleza;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class NaturalezaValidador implements Validator {

	public boolean supports(Class<?> clazz) {
		return Naturaleza.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "denominacion", "naturaleza.denominacion.obligatorio");

	}

}
