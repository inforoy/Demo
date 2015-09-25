package gob.pe.proc.capaweb;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import gob.pe.proc.capadatos.Demandado;
import gob.pe.proc.capadatos.Demandante;
import gob.pe.proc.capadatos.Notificacion;
import gob.pe.proc.capadatos.Proceso;
import gob.pe.proc.capaservicio.NotificacionService;
import gob.pe.proc.capaservicio.ProcesoService;
import gob.pe.proc.validadores.NotificacionValidadores;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NotificacionController {
	@Autowired
	private NotificacionService notificacionService;
	@Autowired
	private ProcesoService procesoService;
	
	private static final Logger logger=Logger.getLogger(NotificacionController.class);
			
	@InitBinder("notificacionGuardar")
	public void initBinder(WebDataBinder binder){
		binder.setValidator(new NotificacionValidadores());
	}
	
	@RequestMapping(value="/busquedaNotificacion.htm")
	public void buscarNotificacion(Model model,@RequestParam(value="numExpediente",required=false)String nroExpedienteActual,@ModelAttribute("notificacionBuscar")Notificacion notificacionBuscar){
		Set<Notificacion>listaNotificaciones=new HashSet<Notificacion>();
		if(nroExpedienteActual!=null){
			Proceso proceso=new Proceso();
			proceso.setNroExpediente(nroExpedienteActual);
			notificacionBuscar.setProceso(proceso);
			listaNotificaciones=notificacionService.obtenerListaNotificacionPorNumeroExpediente(notificacionBuscar);
		}else{
			listaNotificaciones=notificacionService.obtenerListaNotificacion(notificacionBuscar);
		}

		Set<Demandado> listaDemandados=new HashSet<Demandado>();
		Set<Demandante>listaDemandantes=new HashSet<Demandante>();
		for (Notificacion notificacion : listaNotificaciones) {
			listaDemandados.addAll(notificacion.getProceso().getDemandado());
			listaDemandantes.addAll(notificacion.getProceso().getDemandante());
		}
		model.addAttribute("listaNotificaciones",listaNotificaciones);
		model.addAttribute("listaDemandados",listaDemandados);
		model.addAttribute("listaDemandantes",listaDemandantes);
	}
	@RequestMapping(value = "/addNotificacion.htm", method = RequestMethod.GET)
	public @ModelAttribute("notificacionGuardar")Notificacion notificacion(@RequestParam(value="numNotificacion",required=false)String numNotificacion,@RequestParam(value="numExpediente",required=false)String numExpediente){
		if(numNotificacion!=null){
			Notificacion notificacion=notificacionService.obtenerNotificacionporId(numNotificacion);
			String[] componentesNotificacion=notificacion.getNroNotificacion().split("-");
			notificacion.setComponenteUnoNroNotificacion(componentesNotificacion[0].trim());
			notificacion.setComponenteDosNroNotificacion(componentesNotificacion[1].trim());
			notificacion.setComponenteTresNroNotificacion(componentesNotificacion[2].trim());
			notificacion.setComponenteCuatroNroNotificacion(componentesNotificacion[3].trim());
			return notificacion;
		}
		Proceso proceso=procesoService.obtenerProcesoporId(numExpediente);
		Notificacion notificacionCargarExpediente=new Notificacion();
		notificacionCargarExpediente.setProceso(proceso);
		return notificacionCargarExpediente;	
	}


	@RequestMapping(value = "/addNotificacion.htm", method = RequestMethod.POST)
	public String guardarNotificacion(@ModelAttribute("notificacionGuardar")@Valid Notificacion notificacion,BindingResult result) {
		if(result.hasErrors()){
			return "addNotificacion";
		}
		notificacion.setNroNotificacion(notificacion.getComponenteUnoNroNotificacion()+"-"+notificacion.getComponenteDosNroNotificacion()+"-"+notificacion.getComponenteTresNroNotificacion()+"-"+notificacion.getComponenteCuatroNroNotificacion());
		notificacionService.guardarNotificacion(notificacion);
		return "redirect:busquedaNotificacion.htm?numExpediente="+notificacion.getProceso().getNroExpediente();
	}
	@RequestMapping(value="/borrarNotificacion.htm")
	public String borrarNotificacion(Model model,@RequestParam(value="numNotificacion",required=false)String numNotificacion){
		Notificacion buscarNotificacion=notificacionService.obtenerNotificacionporId(numNotificacion);
		notificacionService.desactivarNotificacion(numNotificacion);
		return "redirect:busquedaNotificacion.htm?numExpediente="+buscarNotificacion.getProceso().getNroExpediente();
	}
	
	
	
	// REGISTRAR ESCRITO
	@RequestMapping(value="/listarNotificacionEscrito.htm")
	public void buscarNotificacionAddEscrito(Model model,@ModelAttribute("notificacionBuscar")Notificacion notificacionBuscar){
		Set<Notificacion>listaNotificaciones=notificacionService.obtenerListaNotificacion(notificacionBuscar);
		Set<Demandado> listaDemandados=new HashSet<Demandado>();
		Set<Demandante>listaDemandantes=new HashSet<Demandante>();
		for (Notificacion notificacion : listaNotificaciones) {
			listaDemandados.addAll(notificacion.getProceso().getDemandado());
			listaDemandantes.addAll(notificacion.getProceso().getDemandante());
		}
		model.addAttribute("listaNotificaciones",listaNotificaciones);
		model.addAttribute("listaDemandados",listaDemandados);
		model.addAttribute("listaDemandantes",listaDemandantes);
	}
	
	@RequestMapping(value = "/listarNotificacionEstrito.htm", method = RequestMethod.POST)
	public String guardarNotificacionAdd(@ModelAttribute("notificacionGuardar") Notificacion notificacion) {
			notificacionService.guardarNotificacion(notificacion);
			return "redirect:busquedaNotificacion.htm";
	}
	
	
	// REGISTRAR ESCRITO
	@RequestMapping(value="/listarNotificacionAudiencia.htm")
	public void buscarNotificacionAddAudiencia(Model model,@ModelAttribute("notificacionBuscar")Notificacion notificacionBuscar){
		Set<Notificacion>listaNotificaciones=notificacionService.obtenerListaNotificacion(notificacionBuscar);
		Set<Demandado> listaDemandados=new HashSet<Demandado>();
		Set<Demandante>listaDemandantes=new HashSet<Demandante>();
		for (Notificacion notificacion : listaNotificaciones) {
			listaDemandados.addAll(notificacion.getProceso().getDemandado());
			listaDemandantes.addAll(notificacion.getProceso().getDemandante());
		}
		model.addAttribute("listaNotificaciones",listaNotificaciones);
		model.addAttribute("listaDemandados",listaDemandados);
		model.addAttribute("listaDemandantes",listaDemandantes);
	}
	@RequestMapping(value = "/listarNotificacionAudiencia.htm", method = RequestMethod.POST)
	public String guardarNotificacionAddAu(@ModelAttribute("notificacionGuardar") Notificacion notificacion) {
			notificacionService.guardarNotificacion(notificacion);
			return "redirect:busquedaNotificacion.htm";
	}
	
	/***** CAMBIOS *****/
	/********************************************************************************************************/
	@RequestMapping(value="/listadoNotificaciones.htm",  method = RequestMethod.GET)
	public void listadoNotificaciones_get(Model model, @RequestParam(value="doc",required=false) int documento, @ModelAttribute("notificacionBuscar")Notificacion notificacion){
		notificacion.setTipoDoc(documento);
		Set<Notificacion> listaNotificaciones = notificacionService.obtenerListaNotificacion(notificacion);		
		model.addAttribute("listaNotificaciones",listaNotificaciones);
		model.addAttribute("tipoDocumento", documento);	
	}
	
	@RequestMapping(value="/listadoNotificaciones.htm",  method = RequestMethod.POST)
	public void listadoNotificaciones_post(Model model, @ModelAttribute("notificacionBuscar")Notificacion notificacion){		
		Set<Notificacion>listaNotificaciones = notificacionService.obtenerListaNotificacion(notificacion);		
		model.addAttribute("listaNotificaciones",listaNotificaciones);
		model.addAttribute("tipoDocumento", notificacion.getTipoDoc());
	}
		
	/********************************************************************************************************/
		
}
