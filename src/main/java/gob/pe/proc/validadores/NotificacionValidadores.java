package gob.pe.proc.validadores;

import gob.pe.proc.capadatos.Notificacion;
import gob.pe.proc.util.Util;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class NotificacionValidadores implements Validator {

	public boolean supports(Class<?> clazz) {
		return Notificacion.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		Notificacion notificacion=(Notificacion)target;
		
		if(StringUtils.isEmpty(notificacion.getComponenteUnoNroNotificacion())|| 
		   StringUtils.isEmpty(notificacion.getComponenteDosNroNotificacion())||
		   StringUtils.isEmpty(notificacion.getComponenteTresNroNotificacion())||
		   StringUtils.isEmpty(notificacion.getComponenteCuatroNroNotificacion())){
					errors.rejectValue("nroNotificacion", "notificacion.nroNotificacion.obligatorio");
		}else{
			if(!Util.isInteger(notificacion.getComponenteUnoNroNotificacion())||
				!Util.isInteger(notificacion.getComponenteDosNroNotificacion())){
				errors.rejectValue("nroNotificacion", "notificacion.nroExpediente.numero");
			}
			if(!Util.isCadena(notificacion.getComponenteTresNroNotificacion())||
			   !Util.isCadena(notificacion.getComponenteCuatroNroNotificacion())){
				errors.rejectValue("nroNotificacion", "notificacion.nroExpediente.cadena");
			}
				
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "proceso.nroExpediente", "notificacion.nroExpediente.obligatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fechaIngreso", "notificacion.fechaIngreso.obligatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nroResolucion", "notificacion.nroResolucion.obligatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fechaResolucion", "notificacion.fechaResolucion.obligatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nroFojas", "notificacion.nroFojas.obligatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "anexo", "notificacion.anexo.obligatorio");
		
		if(!Util.isInteger(notificacion.getNroResolucion())){
			errors.rejectValue("nroResolucion", "notificacion.nroResolucion.tipo.entero");
		}
		
		if(!Util.isInteger(notificacion.getNroFojas())){
			errors.rejectValue("nroFojas", "notificacion.nroFojas.tipo.entero");
		}
	}

}
