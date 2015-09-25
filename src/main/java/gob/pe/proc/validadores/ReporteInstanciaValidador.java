package gob.pe.proc.validadores;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import gob.pe.proc.capadatos.Instancia;
@Component
public class ReporteInstanciaValidador implements Validator {

	@SuppressWarnings("rawtypes")
	public boolean supports(Class clase) {
		return Instancia.class.isAssignableFrom(clase);
	}

	
	public void validate(Object object, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id.proceso.fechaInicio", "proceso.fechainicio.obligatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id.proceso.fechaFin", "proceso.fechafin.obligatorio");

	}

}
