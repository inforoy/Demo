package gob.pe.proc.validadores;

import gob.pe.proc.capadatos.Dependencia;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class DistritoJudicialValidador implements Validator {

	public boolean supports(Class<?> clazz) {
		return Dependencia.class.isAssignableFrom(clazz);
	}

	
	public void validate(Object target, Errors errors) {
		Dependencia dependencia=(Dependencia)target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "denominacion", "distritoJudicial.denominacion.obligatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "magistrado", "distritoJudicial.magistrado.obligatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "secretario", "distritoJudicial.secretario.obligatorio");
		
		if(dependencia.getUbigeo().getIdUbigeo().equalsIgnoreCase("0")){
			errors.rejectValue( "ubigeo", "distritoJudicial.ubigeo.obligatorio");
		}
		
		if(dependencia.getOrganojudicial().getIdOrganojudicial()==0){
			errors.rejectValue("organojudicial", "distritoJudicial.organojudicial.obligatorio");
		}
		
	}

}
