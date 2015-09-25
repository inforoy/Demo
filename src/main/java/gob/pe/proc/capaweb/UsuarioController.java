package gob.pe.proc.capaweb;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import gob.pe.proc.capadatos.Demandado;
import gob.pe.proc.capadatos.Demandante;
import gob.pe.proc.capadatos.Notificacion;
import gob.pe.proc.capadatos.Proceso;
import gob.pe.proc.capadatos.Rol;
import gob.pe.proc.capadatos.Usuario;
import gob.pe.proc.capaservicio.DemandadoService;
import gob.pe.proc.capaservicio.DemandanteService;
import gob.pe.proc.capaservicio.NotificacionService;
import gob.pe.proc.capaservicio.PerfilService;
import gob.pe.proc.capaservicio.PersonalService;
import gob.pe.proc.capaservicio.ProcesoService;
import gob.pe.proc.validadores.PersonalValidador;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes({"datosUsuario"})
public class UsuarioController {
	
	private static final Logger logger=Logger.getLogger(UsuarioController.class);

	@Autowired
	private ProcesoService procesoService;
	
	@Autowired
	private DemandanteService demandanteService;
	
	@Autowired
	private DemandadoService demandadoService;
	
	@Autowired
	private NotificacionService notificacionService;
	
	@Autowired
	private PersonalService personalService;
	
	@Autowired
	private PerfilService perfilService;
	
	@InitBinder("guardarPersonal")
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(new PersonalValidador());
	}
	
	@RequestMapping(value="/registrarUsuario.htm")
	public void listarPersonal(@ModelAttribute("buscarPersonal")Usuario usuario, Model model){
		List<Usuario> listaPersonal=personalService.obtenerListaPersonal(usuario);
		model.addAttribute("listaPersonal", listaPersonal);
	}
	@RequestMapping(value="/addPersonal.htm",method=RequestMethod.GET)
	public @ModelAttribute("guardarPersonal")Usuario usuario(@RequestParam(value="idUsuario",required=false)String idUsuario){
		if(idUsuario!=null){
			Usuario usuario=personalService.obtenerPersonalporId(idUsuario);
			return usuario;
		}
		return new Usuario();
	}
	
	@RequestMapping(value="/addPersonal.htm",method=RequestMethod.POST)
	public String guardarPersonal( @ModelAttribute("guardarPersonal")@Valid Usuario usuario,BindingResult result){
		if(result.hasErrors()){
			return "addPersonal";
		}
		Rol rol=perfilService.obtenerPerfilporId(usuario.getRol().getIdRol());
		usuario.setRol(rol);
		personalService.guardarPersonal(usuario);
		return "redirect:registrarUsuario.htm";
	}
	@RequestMapping(value="/borrarPersonal.htm")
	public String borrarPersonal(@RequestParam(value="idUsuario",required=false)String idUsuario){
		Usuario usuario=personalService.obtenerPersonalporId(idUsuario);
		usuario.setEstadoLaboral(false);
		personalService.guardarPersonal(usuario);
		return "redirect:registrarUsuario.htm";
	}
	@ModelAttribute("tipoPerfil")
	public List<Rol> listarPerfil(){
		List<Rol> tipoPerfil=perfilService.listarPerfiles();
		return tipoPerfil;
	}
	@RequestMapping(value="/login.htm")
	public void loginUsuario() {
		logger.info("Se ingreso al controlador del Login");
	}
	@RequestMapping(value="/accesoDenegado.htm")
	public void accesoDenegadoUsuario() {

	}
	@RequestMapping("/inicio.htm")
	public @ModelAttribute("buscarProceso")Proceso inicio(Model model){
		logger.debug("Entro al Inicio");
		Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();		
		model.addAttribute("datosUsuario", usuario);
		Proceso proceso = new Proceso();		
		Set<Proceso> listaProceso = procesoService.obtenerListaProceso(proceso);
		model.addAttribute("listaProcesoLitigantes",listaProceso);
		model.addAttribute("opcionSeleccionada",1);
		return proceso;
	}
	
	/********************* CAMBIOS ************************/
	@RequestMapping(value="/inicio.htm", method=RequestMethod.POST)
	public void buscarExpediente(Model model, @ModelAttribute("buscarProceso")Proceso procesolista){
		
		int opcionSeleccionada = (Integer.parseInt(procesolista.getOpcion()));
		String txtBusqueda = procesolista.getTxtBuscqueda();
		
		switch (opcionSeleccionada) {
		case 1:		
				procesolista.setNroExpediente(txtBusqueda);
				Set<Proceso> listaProceso = procesoService.obtenerListaProceso(procesolista);
				model.addAttribute("listaProcesoLitigantes",listaProceso);
				model.addAttribute("opcionSeleccionada",opcionSeleccionada);
				break;
				
		case 2:	
				Demandante buscarDemandante = new Demandante();
				buscarDemandante.setDenominacion(txtBusqueda);
				Set<Demandante> listaDemandante = demandanteService.obtenerListaDemandantePorNombre(buscarDemandante);				
				model.addAttribute("listaProcesoDemandante",listaDemandante);
				model.addAttribute("opcionSeleccionada",opcionSeleccionada);
				break;
				
		case 3:	
				Demandado buscarDemandado = new Demandado();
				buscarDemandado.setDenominacion(txtBusqueda);				
				Set<Demandado> listaDemandados=demandadoService.obtenerListaDemandadoPorNombre(buscarDemandado);
				model.addAttribute("listaProcesoDemandado",listaDemandados);
				model.addAttribute("opcionSeleccionada",opcionSeleccionada);

				break;
				
		case 4:	
				Proceso procesoFolder = new Proceso();
				procesoFolder.setNroFolder(txtBusqueda);
				Set<Proceso> listaProceso_NroFolder = procesoService.obtenerListaProcesoporFolder(procesoFolder);			
				model.addAttribute("listaProcesoLitigantes",listaProceso_NroFolder);
				model.addAttribute("opcionSeleccionada",opcionSeleccionada);
				break;
				
		case 5:	
				Notificacion notificacion = new Notificacion();
				notificacion.setNroNotificacion(txtBusqueda);
				Set<Notificacion>listaNotificaciones=notificacionService.obtenerListaNotificacion(notificacion);
				model.addAttribute("listaNotificaciones",listaNotificaciones);
				model.addAttribute("opcionSeleccionada",opcionSeleccionada);
				break;

		default:	System.out.println("Error");
			break;
		}			
		
		
		
		
	}
	/*********************************************/
	
	@RequestMapping("/logout.htm")
	public String logoutUsuario(Model model){
		logger.info("Se termino la session de SysProc");
		return "redirect:j_spring_security_logout";
	}
	
	@RequestMapping("/construccion.htm")
	public ModelAndView enConstruccion(){
		return new ModelAndView("error/construccion");
	}
}
