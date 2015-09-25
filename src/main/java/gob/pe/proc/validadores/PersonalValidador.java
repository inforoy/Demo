package gob.pe.proc.validadores;

import gob.pe.proc.capadatos.Usuario;
import gob.pe.proc.util.Util;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class PersonalValidador implements Validator {

	public boolean supports(Class<?> arg0) {
		return Usuario.class.isAssignableFrom(arg0);
	}

	public void validate(Object object, Errors errors) {
		Usuario usuario=(Usuario) object;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dni", "personal.dni.obligatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombres", "personal.nombres.obligatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "apaterno", "personal.apaterno.obligatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "amaterno", "personal.amaterno.obligatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "correo", "personal.correo.obligatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "direccion", "personal.direccion.obligatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "telefono", "personal.telefono.obligatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "personal.username.obligatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passname", "personal.passname.obligatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fechaNacimiento", "proceso.fechaNacimiento.obligatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fechaIngreso", "proceso.fechaIngreso.obligatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmarPassname", "proceso.confirmarPassname.obligatorio");
		
		if(usuario.getRol().getIdRol()==0){
			errors.rejectValue("rol", "personal.rol.valido");
		}
		if(!Util.isInteger(usuario.getTelefono().toString())){
			errors.rejectValue("telefono", "personal.telefono.valido");
		}
		if(!Util.isInteger(usuario.getDni())){
			errors.rejectValue("telefono", "personal.dni.valido");
		}
		
		if(!usuario.getPassname().equals(usuario.getConfirmarPassname())){
			errors.rejectValue("confirmarPassname", "personal.confirmarPassword.valido");
		}
		
		if(!Util.validateEmail(usuario.getCorreo())){
			errors.rejectValue("correo", "personal.correo.valido");
		}
	}

}
