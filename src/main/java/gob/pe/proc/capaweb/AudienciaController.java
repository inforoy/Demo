package gob.pe.proc.capaweb;

import javax.validation.Valid;

import gob.pe.proc.capadatos.Audiencia;
import gob.pe.proc.capadatos.Notificacion;
import gob.pe.proc.capaservicio.AudienciaService;
import gob.pe.proc.validadores.AudienciaValidador;

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
public class AudienciaController {
	@Autowired
	private AudienciaService audienciaService;
	
	private static final Logger logger=Logger.getLogger(AudienciaController.class);
	
	@InitBinder("audienciaGuardar")
	public void initBinder(WebDataBinder binder){
		binder.setValidator(new AudienciaValidador());
	}
	
	@RequestMapping(value = "/addAudiencia.htm", method = RequestMethod.GET)
	public void enviarAudiencia(@RequestParam(value="numNotificacion",required=false)String numNotificacion,@ModelAttribute("audienciaGuardar") Audiencia audienciaGuardar) {
		Notificacion notificacion=new Notificacion();
		notificacion.setNroNotificacion(numNotificacion);
		audienciaGuardar.setNotificacion(notificacion);	
	
	}
	@RequestMapping(value="/addAudiencia.htm",method=RequestMethod.POST)
	public String guardarAudiencia(@ModelAttribute("audienciaGuardar")@Valid Audiencia audienciaGuardar,BindingResult result){
		if(result.hasErrors()){
			return "addAudiencia";
		}
		audienciaService.guardarAudiencia(audienciaGuardar);
		return "redirect:/busquedaNotificacion.htm";
	
	}
}
