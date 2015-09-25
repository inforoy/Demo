package gob.pe.proc.capaweb;

import javax.validation.Valid;

import gob.pe.proc.capadatos.Escrito;
import gob.pe.proc.capadatos.Notificacion;
import gob.pe.proc.capaservicio.EscritoService;
import gob.pe.proc.validadores.EscritoValidador;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EscritoController {
	@Autowired
	private EscritoService escritoService;
	
	private static final Logger logger=Logger.getLogger(EscritoController.class);
	
	@InitBinder("escritoGuardar")
	public void initBinder(WebDataBinder binder){
		binder.setValidator(new EscritoValidador());
	}
	
	
	@RequestMapping(value = "/addEscrito.htm", method = RequestMethod.GET)
	public void enviarEscrito(@RequestParam(value="numNotificacion",required=false)String numNotificacion,@ModelAttribute("escritoGuardar") Escrito escrito) {
		Notificacion notificacion=new Notificacion();
		notificacion.setNroNotificacion(numNotificacion);
		escrito.setNotificacion(notificacion);	
	
	}
	
	@RequestMapping(value="/addEscrito.htm",method=RequestMethod.POST)
	public String guardarEscrito(@ModelAttribute("escritoGuardar")@Valid Escrito escritoGuardar,BindingResult result){
		if(result.hasErrors()){
			return "addEscrito";
		}
		escritoService.guardarEscrito(escritoGuardar);
		return "redirect:/busquedaNotificacion.htm";
	
	}
}
