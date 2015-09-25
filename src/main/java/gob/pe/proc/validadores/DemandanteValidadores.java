package gob.pe.proc.validadores;

import gob.pe.proc.capadatos.Demandante;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class DemandanteValidadores implements Validator {

	public boolean supports(Class<?> clazz) {
		return Demandante.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "proceso", "demandante.proceso.obligatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "denominacion", "demandante.denominacion.obligatorio");
	}

}
