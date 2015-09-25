package gob.pe.proc.validadores;

import gob.pe.proc.capadatos.Audiencia;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class AudienciaValidador implements Validator{

	public boolean supports(Class<?> clazz) {
		return Audiencia.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "diligencia", "audiencia.diligencia.obligatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "hora", "audiencia.hora.obligatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fechaAudiencia", "audiencia.fechaAudiencia.obligatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fechaRegistro", "audiencia.fechaRegistro.obligatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "observacion", "audiencia.observacion.obligatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "notificacion", "audiencia.notificacion.obligatorio");
	}

}
