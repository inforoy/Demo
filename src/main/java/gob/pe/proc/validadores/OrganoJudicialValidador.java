package gob.pe.proc.validadores;

import gob.pe.proc.capadatos.Organojudicial;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class OrganoJudicialValidador implements Validator {

	
	public boolean supports(Class<?> clazz) {
		return Organojudicial.class.isAssignableFrom(clazz);
	}

	
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "denominacion", "organoJudicial.denominacion.obligatorio");

	}

}
