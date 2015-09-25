package gob.pe.proc.validadores;

import gob.pe.proc.capadatos.Proceso;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
@Component
public class ReporteValidador implements Validator {

	@SuppressWarnings("rawtypes")
	public boolean supports(Class clase) {
		return Proceso.class.isAssignableFrom(clase);
	}

	
	public void validate(Object object, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fechaInicio", "proceso.fechainicio.obligatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fechaFin", "proceso.fechafin.obligatorio");
	}

}
