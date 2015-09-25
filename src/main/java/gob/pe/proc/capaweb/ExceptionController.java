package gob.pe.proc.capaweb;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionController {
	private static final Logger logger=Logger.getLogger(ExceptionController.class);
	
	@ExceptionHandler(Exception.class)
	public ModelAndView manejadorExcepcion(HttpServletRequest request,Exception ex){
		logger.info("Se capturo la siguiente excepcion: "+ex.getMessage());
		ModelAndView vistaExcepcion=new ModelAndView("error/error");
		vistaExcepcion.addObject("nombre", ex.getClass().getSimpleName());
		vistaExcepcion.addObject("mensaje",ex.getMessage());
		
		return vistaExcepcion;
	}
}
