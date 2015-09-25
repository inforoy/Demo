package gob.pe.proc.validadores;

import gob.pe.proc.capadatos.Escrito;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class EscritoValidador implements Validator {

	public boolean supports(Class<?> clazz) {
		return Escrito.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "denominacion", "escrito.denominacion.obligatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sumilla", "escrito.sumilla.obligatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nroFolios", "escrito.nroFolios.obligatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fechaEmision", "escrito.fechaEmision.obligatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "observacion", "escrito.observacion.obligatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "notificacion", "escrito.notificacion.obligatorio");
	}

}
