package gob.pe.proc.capaweb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import gob.pe.proc.capadatos.Demandado;
import gob.pe.proc.capadatos.Demandante;
import gob.pe.proc.capadatos.Estado;
import gob.pe.proc.capadatos.Instancia;
import gob.pe.proc.capadatos.Naturaleza;
import gob.pe.proc.capadatos.Proceso;
import gob.pe.proc.capaservicio.ProcesoService;
import gob.pe.proc.validadores.ProcesoValidador;

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
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("datosProceso")
public class ProcesoController {
	@Autowired
	private ProcesoService procesoService;

	
	private static final Logger logger=Logger.getLogger(ProcesoController.class);
	
	@InitBinder("procesoGuardar")
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(new ProcesoValidador());
	}
	
	String nroExpedienteActual="";
	int idDependecia;
	List<String>  listaNuevosDemandados = new ArrayList<String>();
	List<String> listaNuevosDemandantes=new ArrayList<String>();
	@RequestMapping("/procesoList.htm")
	public void procesoList(Model model,@ModelAttribute("procesoBuscar")Proceso procesolista){
		logger.info("SE ENTRO AL PRIMER METODO");
		Set<Proceso> listaProceso=procesoService.obtenerListaProceso(procesolista);
		Set<Demandante> listaDemandantes = new HashSet<Demandante>();
		Set<Demandado> listaDemandados=new HashSet<Demandado>();
		//lista de demandantes por proceso
		for (Proceso proceso : listaProceso) {
			listaDemandantes.addAll(proceso.getDemandante());
		}
		//lista de demandados por proceso
		for (Proceso proceso : listaProceso) {
			listaDemandados.addAll(proceso.getDemandado());
		}
		model.addAttribute("listaProcesoDemandante",listaDemandantes);
		model.addAttribute("listaProcesoDemandado", listaDemandados);
		model.addAttribute("listaProcesoLitigantes",listaProceso);
			
	}
	@RequestMapping("/busquedaFolder.htm")
	public void busquedaFolder(Model model,@ModelAttribute("folderBuscar")Proceso procesolista){
		Set<Proceso> listaProceso=procesoService.obtenerListaProcesoporFolder(procesolista);
		Set<Demandante> listaDemandantes = new HashSet<Demandante>();
		Set<Demandado> listaDemandados=new HashSet<Demandado>();
		//lista de demandantes por proceso
		for (Proceso proceso : listaProceso) {
			listaDemandantes.addAll(proceso.getDemandante());
		}
		//lista de demandados por proceso
		for (Proceso proceso : listaProceso) {
			listaDemandados.addAll(proceso.getDemandado());
		}
		model.addAttribute("listaProcesoDemandante",listaDemandantes);
		model.addAttribute("listaProcesoDemandado", listaDemandados);
		model.addAttribute("listaProcesoLitigantes",listaProceso);
	}
	
	@RequestMapping("/reporteExpedienteGuardado.htm")
	public @ModelAttribute("ultimoProceso")Proceso procesolista(Model model,@RequestParam(value="nroExpedienteActual",required=false)String nroExpedienteActual,@ModelAttribute("datosProceso")Proceso procesoGuardado){
		Proceso ultimoProcesoCreado=procesoService.obtenerProcesoporId(procesoGuardado.getNroExpediente());
		Set<Demandante> listaDemandantes = new HashSet<Demandante>();
		Set<Demandado> listaDemandados=new HashSet<Demandado>();
		Set<Instancia> listaInstancias=new HashSet<Instancia>();

		listaDemandantes.addAll(ultimoProcesoCreado.getDemandante());
		listaDemandados.addAll(ultimoProcesoCreado.getDemandado());
		listaInstancias.addAll(ultimoProcesoCreado.getInstancias());
		
		model.addAttribute("listaProcesoDemandante",listaDemandantes);
		model.addAttribute("listaProcesoDemandado", listaDemandados);
		model.addAttribute("instancias", listaInstancias);
		return ultimoProcesoCreado;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value="/addProceso.htm",method=RequestMethod.GET)
	public @ModelAttribute("procesoGuardar")Proceso procesonuevo(Model model,@RequestParam(value="numExpediente",required=false)String nroExpediente){
		
			nroExpedienteActual=nroExpediente;
			List<Demandado> demandadosProceso = new ArrayList<Demandado>();
			List<Demandante> demandantesProceso = new ArrayList<Demandante>();
			List<Instancia> instanciaProceso=new ArrayList<Instancia>();
			if(nroExpediente!=null){
				Proceso proceso=procesoService.obtenerProcesoporId(nroExpediente);
				String[]numExpediente=proceso.getNroExpediente().split("-");
				proceso.setComponenteUnoNroExpediente(numExpediente[0].trim());
				proceso.setComponenteDosNroExpediente(numExpediente[1].trim());
				proceso.setComponenteTresNroExpediente(numExpediente[2].trim());
				proceso.setComponenteCuatroNroExpediente(numExpediente[3].trim());
				proceso.setComponenteCincoNroExpediente(numExpediente[4].trim());
				proceso.setComponenteSeisNroExpediente(numExpediente[5].trim());
				proceso.setComponenteSieteNroExpediente(numExpediente[6].trim());
				demandadosProceso.addAll(proceso.getDemandado());
				demandantesProceso.addAll(proceso.getDemandante());
				if(!proceso.getInstancias().isEmpty()){
					instanciaProceso.addAll(proceso.getInstancias());
					Collections.sort(instanciaProceso,new Instancia());
					idDependecia=instanciaProceso.get(instanciaProceso.size()-1).getId().getDependencia().getIdDependencia();
				}
				
				proceso.setNuevoDemandado(demandadosProceso);
				proceso.setNuevoDemandante(demandantesProceso);
				
				return proceso;
			}
			
		return new Proceso();
	}
	
	@RequestMapping(value="/addProceso.htm",method=RequestMethod.POST)
	public String guardarProceso(@ModelAttribute("procesoGuardar")@Valid Proceso procesoguardar,BindingResult result,Model model){
			if(result.hasErrors()){
				model.addAttribute("datosProceso", procesoguardar);
				return "addProceso";
			}
			Set<Demandado>guardarListaDemandado=new HashSet<Demandado>();
			Set<Demandante>guardarListaDemandante=new HashSet<Demandante>();
			procesoguardar.setNroExpediente(procesoguardar.getComponenteUnoNroExpediente().toString()+"-"+
					procesoguardar.getComponenteDosNroExpediente().trim()+"-"+
					procesoguardar.getComponenteTresNroExpediente().trim()+"-"+
					procesoguardar.getComponenteCuatroNroExpediente().trim()+"-"+
					procesoguardar.getComponenteCincoNroExpediente().trim()+"-"+
					procesoguardar.getComponenteSeisNroExpediente().trim()+"-"+
					procesoguardar.getComponenteSieteNroExpediente().trim());
			guardarListaDemandado.addAll(procesoguardar.getNuevoDemandado());
			guardarListaDemandante.addAll(procesoguardar.getNuevoDemandante());
			
			for (Demandado demandado : guardarListaDemandado) {
				demandado.setProceso(procesoguardar);
			}
			for (Demandante demandante : guardarListaDemandante) {
				demandante.setProceso(procesoguardar);
			}
			
			procesoguardar.setDemandado(guardarListaDemandado);
			procesoguardar.setDemandante(guardarListaDemandante);
			model.addAttribute("datosProceso", procesoguardar);
			return "redirect:/addInstancia.htm?idDependecia="+idDependecia;
		
	}
	
	@RequestMapping(value="/borrarProceso.htm")
	public String borrarProceso(Model model,@RequestParam(value="numExpediente",required=false)String numExpediente){
		procesoService.desactivarProceso(numExpediente);
		return "redirect:procesoList.htm";
	}

	
	@ModelAttribute("tipoEstado")
	public List<Estado> listarEstado(){
		List<Estado> tipoEstado=procesoService.listarEstado();
		return tipoEstado;
	}
	@ModelAttribute("tipoNaturaleza")
	public List<Naturaleza>listarNaturaleza(){
		List<Naturaleza> tipoNaturaleza=procesoService.listarNaturaleza();
		return tipoNaturaleza;
	}
	
	public List<Demandado> demandadosAGuardar(Proceso proceso){
		Proceso obtenerProceso=procesoService.obtenerProcesoporId(proceso.getNroExpediente());
		List<Demandado> nuevosDemandados = new ArrayList<Demandado>(proceso.getNuevoDemandado());
		List<Demandado> list=new ArrayList<Demandado>();
		list.addAll(obtenerProceso.getDemandado());
		nuevosDemandados.removeAll(list);
		return nuevosDemandados;
	}

}
