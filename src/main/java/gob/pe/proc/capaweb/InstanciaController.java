package gob.pe.proc.capaweb;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import gob.pe.proc.capadatos.Dependencia;
import gob.pe.proc.capadatos.Instancia;
import gob.pe.proc.capadatos.InstanciaId;
import gob.pe.proc.capadatos.Organojudicial;
import gob.pe.proc.capadatos.Proceso;
import gob.pe.proc.capaservicio.DemandadoService;
import gob.pe.proc.capaservicio.DependenciaService;
import gob.pe.proc.capaservicio.InstanciaService;
import gob.pe.proc.capaservicio.ProcesoService;
import gob.pe.proc.validadores.InstanciaValidadores;

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
public class InstanciaController {
	@Autowired
	private InstanciaService instanciaService;
	@Autowired
	private ProcesoService procesoService;
	@Autowired
	private DependenciaService dependenciaService;
	
	private static final Logger logger=Logger.getLogger(InstanciaController.class);
			
	@InitBinder("instanciaGuardar")
	public void initBinder(WebDataBinder binder){
		binder.setValidator(new InstanciaValidadores());
	}
	
	String nroExpedienteActualEnviadoProceso="";
	@RequestMapping(value = "/addInstancia.htm", method = RequestMethod.GET)
	public @ModelAttribute("instanciaGuardar")Instancia instanciaGuardar(@RequestParam(value="idDependecia",required=false)Integer idDependecia,HttpSession session) {
		Proceso procesoSesion=(Proceso)session.getAttribute("datosProceso");
		InstanciaId idBuscar=new InstanciaId();
		idBuscar.setProceso(procesoSesion);
		Instancia obtenerInstancia=new Instancia();
		if(idDependecia>0){
			Dependencia dependenciaId=dependenciaService.buscarDependeciaID(idDependecia);	
			idBuscar.setDependencia(dependenciaId);
			obtenerInstancia=instanciaService.obtenerInstanciaporID(idBuscar);
		}else{
			obtenerInstancia.setId(idBuscar);
		}
		return obtenerInstancia;
	}
	@RequestMapping(value="/addInstancia.htm",method=RequestMethod.POST)
	public String guardarInstancia(@ModelAttribute("instanciaGuardar")@Valid Instancia instanciaGuardar,BindingResult result,HttpSession session){
		if(result.hasErrors()){
			return "addInstancia";
		}
		Proceso procesoSesion=(Proceso)session.getAttribute("datosProceso");
		Dependencia dependencia=dependenciaService.obtenerDependenciaporId(instanciaGuardar.getId().getDependencia().getIdDependencia());
		InstanciaId id=new InstanciaId();
		id.setProceso(procesoSesion);
		id.setDependencia(dependencia);
		instanciaGuardar.setId(id);
		Set<Instancia>guardarListaInstancia=new HashSet<Instancia>();
		guardarListaInstancia.add(instanciaGuardar);
		procesoSesion.setInstancias(guardarListaInstancia);
		procesoService.guardarProceso(procesoSesion);
		return "redirect:/reporteExpedienteGuardado.htm?nroExpedienteActual="+nroExpedienteActualEnviadoProceso;
	
	}
	
	@ModelAttribute("organosJudiciales")
	public List<Organojudicial> listarOrganosJudiciales(){
		List<Organojudicial> organoJudicial=instanciaService.listarOrganosJudiciales();
		return organoJudicial;
	}
}
