package gob.pe.proc.capaweb;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import gob.pe.proc.capadatos.Demandado;
import gob.pe.proc.capadatos.Demandante;
import gob.pe.proc.capadatos.Proceso;
import gob.pe.proc.capaservicio.DemandadoService;
import gob.pe.proc.validadores.DemandadoValidadores;

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
public class DemandadoController {
	@Autowired
	private DemandadoService demandadoService;
	
	private static final Logger logger=Logger.getLogger(DemandadoController.class);
			
	@InitBinder("demandadoGuardar")
	public void initBinder(WebDataBinder binder){
		binder.setValidator(new DemandadoValidadores());
	}
	String nroExpedienteActualEnviadoProceso="";
	@RequestMapping("/demandadoList.htm")
	public void demandadoList(Model model,@ModelAttribute("demandadoBuscar") Demandado demandado,@RequestParam(value="nroExpedienteActual",required=false)String nroExpedienteActual) {
		nroExpedienteActual=(nroExpedienteActual==null || nroExpedienteActual.equalsIgnoreCase(""))?nroExpedienteActualEnviadoProceso:nroExpedienteActual;
		List<Demandado> listaDemandados = demandadoService.obtenerListaDemandadoPorNumeroExpediente(nroExpedienteActual);
		String ultimo=demandadoService.obtenerUltimoProceso();
		model.addAttribute("listaDemandados", listaDemandados);
		model.addAttribute("demandado", demandado);
		model.addAttribute("ultimo", ultimo);
	}
	@RequestMapping("/busquedaDemandado.htm")
	public void busquedaDemandado(Model model,@ModelAttribute("demandadoBuscar")Demandado buscarDemandado){
		Set<Demandado> listaDemandados=demandadoService.obtenerListaDemandadoPorNombre(buscarDemandado);
		Set<Demandante> listaDemandantes=new HashSet<Demandante>();
		//lista de demandados por proceso
		for (Demandado demandado : listaDemandados) {
			listaDemandantes.addAll(demandado.getProceso().getDemandante());
		}
		model.addAttribute("listaProcesoDemandante",listaDemandantes);
		model.addAttribute("listaProcesoDemandado", listaDemandados);
	}
	@RequestMapping(value = "/addDemandado.htm", method = RequestMethod.GET)
	public @ModelAttribute("demandadoGuardar") Demandado demandado(@RequestParam(value="idDemandado",required=false)Integer idDemandado,@RequestParam(value="nroExpedienteActual",required=false)String nroExpedienteActual) {
		if(idDemandado!=null){
			Demandado demandado=demandadoService.obtenerDemandadoporId(idDemandado);
			nroExpedienteActualEnviadoProceso=demandado.getProceso().getNroExpediente();
			return demandado;
		}else if(nroExpedienteActual!=null){
			Demandado demandado=new Demandado();
			Proceso proceso=new Proceso();
			proceso.setNroExpediente(nroExpedienteActual);
			demandado.setProceso(proceso);
			return demandado;
		}else if(!nroExpedienteActualEnviadoProceso.equals("")){
			Demandado demandado=new Demandado();
			Proceso proceso=new Proceso();
			proceso.setNroExpediente(nroExpedienteActualEnviadoProceso);
			demandado.setProceso(proceso);
			return demandado;
		}
		return new Demandado();
		
	}

	@RequestMapping(value = "/addDemandado.htm", method = RequestMethod.POST)
	public String guardarDemandado(@ModelAttribute("demandadoGuardar") @Valid Demandado demandado,BindingResult result) {
		if(result.hasErrors()){
			return "addDemandado";
		}
			demandadoService.guardarDemandado(demandado);
			if(!nroExpedienteActualEnviadoProceso.equals("")){
				return "redirect:demandadoList.htm?nroExpedienteActual="+nroExpedienteActualEnviadoProceso;
			}
			return "redirect:demandadoList.htm?nroExpedienteActual="+demandado.getProceso().getNroExpediente();
	}
	
	@RequestMapping(value="/borrarDemandado.htm")
	public String borrarDemandado(Model model,@RequestParam(value="idDemandado",required=false)Integer idDemandado){
		demandadoService.eliminarDemandado(idDemandado);
		return "redirect:busquedaDemandado.htm";
	}

}
