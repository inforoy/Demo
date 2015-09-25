package gob.pe.proc.capaweb;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gob.pe.proc.capadatos.Demandado;
import gob.pe.proc.capadatos.Demandante;
import gob.pe.proc.capadatos.Dependencia;
import gob.pe.proc.capadatos.Materia;
import gob.pe.proc.capaservicio.DemandadoService;
import gob.pe.proc.capaservicio.DemandanteService;
import gob.pe.proc.capaservicio.InstanciaService;
import gob.pe.proc.capaservicio.ProcesoService;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class JSONController {
	@Autowired
	private ProcesoService procesoService;
	
	@Autowired
	private InstanciaService instanciaService;
	
	@Autowired
	private DemandadoService demandadoService;
	
	@Autowired
	private DemandanteService demandanteService;
	
	private static final Logger logger=Logger.getLogger(JSONController.class);
	
	@RequestMapping(value="/addFolder.htm",method=RequestMethod.POST)
	public @ResponseBody String asignarNumeroFolder(@RequestParam Integer varanio){
		String nroFolder=procesoService.contarFolderPorAnio(varanio);
		//String result="El número de folder que le corresponde es: "+nroFolder;
		return nroFolder;
	}
	
	@RequestMapping(value="/jsonMateria.htm", method = RequestMethod.GET)
	public @ResponseBody void llenarComboMateria(@RequestParam String val,HttpServletRequest request, HttpServletResponse response) throws Exception {
		Integer id=Integer.parseInt(val);
		List<Materia> listaMateria=procesoService.listarMateria(id);
		
		JSONArray materiasJson = new JSONArray();
		JSONObject materiaJsonTodos = new JSONObject();
		materiaJsonTodos.put("When", "");
		materiaJsonTodos.put("Value", "");
		materiaJsonTodos.put("Text", "");
		materiasJson.put(materiaJsonTodos);
		
		for (Materia materia : listaMateria) {
			JSONObject materiaJson = new JSONObject();
			materiaJson.put("When", materia.getNaturaleza().getIdNaturaleza());
			materiaJson.put("Value", materia.getIdMateria());
			materiaJson.put("Text", materia.getDenominacion());
			materiasJson.put(materiaJson);
		}
		materiasJson.write(response.getWriter());
	}
	
	@RequestMapping(value="/jsonDependencia.htm",method = RequestMethod.GET)
	public @ResponseBody void llenarComboDependencia(@RequestParam String val,HttpServletRequest request, HttpServletResponse response)throws Exception{
		Integer id=Integer.parseInt(val);
		List<Dependencia> listaDependencia=instanciaService.listarDependencia(id);
		
		JSONArray dependenciasJson = new JSONArray();
		JSONObject dependenciaJsonTodos = new JSONObject();
		dependenciaJsonTodos.put("When", "");
		dependenciaJsonTodos.put("Value", "");
		dependenciaJsonTodos.put("Text", "");
		dependenciasJson.put(dependenciaJsonTodos);
		
		for (Dependencia dependencia : listaDependencia) {
			JSONObject dependenciaJson = new JSONObject();
			dependenciaJson.put("When", dependencia.getOrganojudicial().getIdOrganojudicial());
			dependenciaJson.put("Value", dependencia.getIdDependencia());
			dependenciaJson.put("Text", dependencia.getDenominacion());
			dependenciasJson.put(dependenciaJson);
		}
		dependenciasJson.write(response.getWriter());
	}
	/*para el autocomplete jquery*/
	@RequestMapping(value = "/obtener_lista_demandado.htm",method = RequestMethod.GET,headers="Accept=*/*")
	public @ResponseBody Set<String> obtenerDemandadoLista(@RequestParam("term") String query) {
		Set<String> listaDemandado = getDemandadoList(query);
		
		return listaDemandado;
	}
	
	@RequestMapping(value = "/obtener_lista_demandante.htm",method = RequestMethod.GET,headers="Accept=*/*")
	public @ResponseBody Set<String> obtenerDemandanteLista(@RequestParam("term") String query) {
		Set<String> listaDemandante = getDemandanteList(query);
		
		return listaDemandante;
	}
	
	
	 public Set<String> getDemandadoList(String query) {
	    	List<Demandado> listaDemandado=demandadoService.obtenerListaDemandado(null);
	    	
	    	String nombreDemandado = null;
	        query = query.toLowerCase();
	        Set<String> matched = new LinkedHashSet<String>();
	        for(int i=0; i < listaDemandado.size(); i++) {
	        	nombreDemandado = listaDemandado.get(i).getDenominacion().toLowerCase();
	            if(nombreDemandado.startsWith(query)) {
	                matched.add(listaDemandado.get(i).getDenominacion());
	            }
	        }
	        return matched;
	    }
	    
	    public Set<String> getDemandanteList(String query) {
	    	List<Demandante> listaDemandante=demandanteService.obtenerListaDemandante(null);
	    	
	    	String nombreDemandado = null;
	        query = query.toLowerCase();
	        Set<String> matched = new LinkedHashSet<String>();
	        for(int i=0; i < listaDemandante.size(); i++) {
	        	nombreDemandado = listaDemandante.get(i).getDenominacion().toLowerCase();
	            if(nombreDemandado.startsWith(query)) {
	                matched.add(listaDemandante.get(i).getDenominacion());
	            }
	        }
	        return matched;
	    }
}