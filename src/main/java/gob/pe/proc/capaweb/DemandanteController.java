package gob.pe.proc.capaweb;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import gob.pe.proc.capadatos.Demandado;
import gob.pe.proc.capadatos.Demandante;
import gob.pe.proc.capaservicio.DemandanteService;
import gob.pe.proc.validadores.DemandanteValidadores;

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
public class DemandanteController {
	@Autowired
	private DemandanteService demandanteService;
	
	private static final Logger logger=Logger.getLogger(DemandanteController.class);
	
	@InitBinder("demandanteGuardar")
	public void initBinder(WebDataBinder binder){
		binder.setValidator(new DemandanteValidadores());
	}
	
	String nroExpedienteActualEnviadoProceso="";
	@RequestMapping("/demandanteList.htm")
	public void demandanteList(Model model,@ModelAttribute("demandanteBuscar") Demandante demandante,@RequestParam(value="nroExpedienteActual",required=false)String nroExpedienteActual) {
		nroExpedienteActual=(nroExpedienteActual.equalsIgnoreCase(""))?nroExpedienteActualEnviadoProceso:nroExpedienteActual;
		List<Demandante> listaDemandantes = demandanteService.obtenerListaDemandantePorNumeroExpediente(nroExpedienteActual);
		String ultimo=demandanteService.obtenerUltimoProceso();
		model.addAttribute("listaDemandantes", listaDemandantes);
		model.addAttribute("demandado", demandante);
		model.addAttribute("ultimo", ultimo);
	}
	@RequestMapping("/busquedaDemandante.htm")
	public void busquedaDemandante(Model model,@ModelAttribute("demandanteBuscar")Demandante buscarDemandante){
		Set<Demandante> listaDemandantes=demandanteService.obtenerListaDemandantePorNombre(buscarDemandante);
		Set<Demandado> listaDemandados=new HashSet<Demandado>();
		//lista de demandados por proceso
		for (Demandante demandante : listaDemandantes) {
			listaDemandados.addAll(demandante.getProceso().getDemandado());
		}
		model.addAttribute("listaProcesoDemandante",listaDemandantes);
		model.addAttribute("listaProcesoDemandado", listaDemandados);
	}
	@RequestMapping(value = "/addDemandante.htm", method = RequestMethod.GET)
	public @ModelAttribute("demandanteGuardar") Demandante demandante(@RequestParam(value="idDemandante",required=false)Integer idDemandante) {
		if(idDemandante!=null){
			Demandante demandante=demandanteService.obtenerDemandanteporId(idDemandante);
			nroExpedienteActualEnviadoProceso=demandante.getProceso().getNroExpediente();
			return demandante;
		}
		return new Demandante();
		
	}
	@RequestMapping(value = "/addDemandante.htm", method = RequestMethod.POST)
	public String guardarDemandante(@ModelAttribute("demandanteGuardar") @Valid Demandante demandante,BindingResult result) {
		if(result.hasErrors()){
			return "addDemandante";
		}
		demandanteService.guardarDemandante(demandante);
		return "redirect:/demandanteList.htm?nroExpedienteActual="+nroExpedienteActualEnviadoProceso;
	}
	@RequestMapping(value="/borrarDemandante.htm")
	public String borrarDemandante(Model model,@RequestParam(value="idDemandante",required=false)Integer idDemandante){
		demandanteService.eliminarDemandante(idDemandante);
		return "redirect:busquedaDemandante.htm";
	}
}
