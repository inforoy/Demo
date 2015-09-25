package gob.pe.proc.validadores;

import gob.pe.proc.capadatos.Asignado;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class AsignacionValidadores implements Validator {

	public boolean supports(Class<?> clazz) {
		return Asignado.class.isAssignableFrom(clazz);
	}

	
	public void validate(Object target, Errors errors) {
		Asignado asignado = (Asignado)target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id.proceso.nroExpediente", "asignacion.nroExpediente.obligatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id.usuario.dni", "asignacion.usuario.obligatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fechaAsignado", "asignacion.fechaAsignado.obligatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "observacion", "asignacion.observacion.obligatorio");
		
		if(asignado.getId().getUsuario().getDni().equals("0")){
			errors.rejectValue("id.usuario.dni", "asignacion.usuario.obligatorio");
		}
	}

}
