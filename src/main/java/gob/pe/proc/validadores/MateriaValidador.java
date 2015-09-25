package gob.pe.proc.validadores;

import gob.pe.proc.capadatos.Materia;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
@Component
public class MateriaValidador implements Validator {
	
	@SuppressWarnings("rawtypes")
	public boolean supports(Class clase) {
		return Materia.class.isAssignableFrom(clase);
	}

	
	public void validate(Object objeto, Errors errors) {
		Materia materia=(Materia)objeto;
		
		if(materia.getNaturaleza().getIdNaturaleza()==0){
			errors.rejectValue("naturaleza", "materia.naturaleza.obligatorio");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "denominacion", "materia.denominacion.obligatorio");

	}

}
