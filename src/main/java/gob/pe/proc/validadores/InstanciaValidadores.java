package gob.pe.proc.validadores;

import gob.pe.proc.capadatos.Instancia;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class InstanciaValidadores implements Validator {

	public boolean supports(Class<?> clazz) {
		return Instancia.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		Instancia instancia=(Instancia)target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fechaCambio", "instancia.fechaCambio.obligatorio");
		if(instancia.getDependencia().getIdDependencia()==0){
			errors.rejectValue("id.dependencia", "instancia.dependencia.obligatorio");
		}
	}

}
