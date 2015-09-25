package gob.pe.proc.validadores;

import gob.pe.proc.capadatos.Proceso;
import gob.pe.proc.util.Util;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
@Component
public class ProcesoValidador implements Validator {

	
	@SuppressWarnings("rawtypes")
	public boolean supports(Class clase) {
		return Proceso.class.isAssignableFrom(clase);
	}

	public void validate(Object object, Errors errors) {
		Proceso proceso=(Proceso) object;
		if(StringUtils.isEmpty(proceso.getComponenteUnoNroExpediente())|| 
		   StringUtils.isEmpty(proceso.getComponenteDosNroExpediente())||
		   StringUtils.isEmpty(proceso.getComponenteTresNroExpediente())||
		   StringUtils.isEmpty(proceso.getComponenteCuatroNroExpediente())||
		   StringUtils.isEmpty(proceso.getComponenteCincoNroExpediente())||
		   StringUtils.isEmpty(proceso.getComponenteSeisNroExpediente())||
		   StringUtils.isEmpty(proceso.getComponenteSieteNroExpediente())){
			errors.rejectValue("nroExpediente", "proceso.nroexpediente.obligatorio");
		}else{
			if(!Util.isInteger(proceso.getComponenteUnoNroExpediente())||
			   !Util.isInteger(proceso.getComponenteDosNroExpediente())||
			   !Util.isInteger(proceso.getComponenteTresNroExpediente())||
			   !Util.isInteger(proceso.getComponenteCuatroNroExpediente())||
			   !Util.isInteger(proceso.getComponenteSieteNroExpediente())){
				errors.rejectValue("nroExpediente", "proceso.nroexpediente.validado.entero");
			}
			if(!Util.isCadena(proceso.getComponenteCincoNroExpediente().toString())||!Util.isCadena(proceso.getComponenteSeisNroExpediente().toString())){
				errors.rejectValue("nroExpediente", "proceso.nroexpediente.validado.cadena");
			}
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fechaInicio", "proceso.fechainicio.obligatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nroFolder", "proceso.nroFolder.obligatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "anio", "proceso.anio.obligatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cuaderno", "proceso.cuaderno.obligatorio");
		
		if(proceso.getMateria().getNaturaleza().getIdNaturaleza()==0){
			errors.rejectValue("materia.naturaleza", "proceso.naturaleza.obligatorio");
		}
		
		
		if(proceso.getAnio()!=null){
			if(proceso.getAnio()==0 || !Util.isCantidadDigitos(proceso.getAnio().toString(),4) || !Util.isInteger(proceso.getAnio().toString())){
				errors.rejectValue("anio", "proceso.anio.valido");
			}			
		}
		
		if(proceso.getCuaderno()!=null){
			if(!Util.isCuaderno(proceso.getCuaderno())){
				errors.rejectValue("cuaderno", "proceso.cuaderno.valido");
			}
		}
		
		if(proceso.getMateria()==null || proceso.getMateria().getIdMateria()==0){
			errors.rejectValue("materia", "proceso.materia.obligatorio");
		}
		
		if(proceso.getEstado()==null || proceso.getEstado().getIdEstado()==0){
			errors.rejectValue("estado", "proceso.estado.obligatorio");
		}
		
		if(proceso.getNuevoDemandado().size()==0){
			errors.rejectValue("demandado", "proceso.demandado.obligatorio");
		}
		if(proceso.getNuevoDemandante().size()==0){
			errors.rejectValue("demandante", "proceso.demandante.obligatorio");
		}

		if(proceso.getSentencia()!=null && !proceso.getSentencia().equalsIgnoreCase("")){
			if(proceso.getSentencia().length()>50){
				errors.rejectValue("sentencia", "proceso.sentencia.tamanio");
			}
			
			if(proceso.getFechaFin()==null){
				errors.rejectValue("fechaFin", "proceso.fechafin.obligatorio");
			}else if(proceso.getFechaFin().before(proceso.getFechaInicio())){
				errors.rejectValue("fechaFin", "proceso.fechafin.valido");
			}
		}
		
		if(proceso.getFechaFin()!=null){
			if(proceso.getSentencia().equalsIgnoreCase("")){
				errors.rejectValue("sentencia", "proceso.sentencia.valido");
			}
		}
	}

}
