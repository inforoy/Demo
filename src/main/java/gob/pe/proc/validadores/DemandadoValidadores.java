package gob.pe.proc.validadores;

import gob.pe.proc.capadatos.Demandado;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class DemandadoValidadores implements Validator {

	public boolean supports(Class<?> clazz) {
		return Demandado.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "proceso", "demandado.proceso.obligatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "denominacion", "demandado.denominacion.obligatorio");
	}

}
