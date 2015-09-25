package gob.pe.proc.capaweb;

import gob.pe.proc.capadatos.Estado;
import gob.pe.proc.capadatos.Instancia;
import gob.pe.proc.capadatos.Naturaleza;
import gob.pe.proc.capadatos.Organojudicial;
import gob.pe.proc.capadatos.Proceso;
import gob.pe.proc.capadatos.Ubigeo;
import gob.pe.proc.capaservicio.InstanciaService;
import gob.pe.proc.capaservicio.ProcesoService;
import gob.pe.proc.validadores.ReporteInstanciaValidador;
import gob.pe.proc.validadores.ReporteValidador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;








import javax.validation.Valid;

import net.sf.jasperreports.engine.JRDataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ReportesController {
	@Autowired
	ProcesoService procesoService;
	@Autowired
	InstanciaService instanciaService;
	
	@InitBinder("fechasProceso")
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(new ReporteValidador());
	}
	
	@InitBinder("materiaProceso")
	public void reportePorMateria(WebDataBinder binder) {
		binder.setValidator(new ReporteValidador());
	}
	
	@InitBinder("ubigeoInstancia")
	public void reportePorUbigeo(WebDataBinder binder) {
		binder.setValidator(new ReporteInstanciaValidador());
	}
	
	@InitBinder("dependenciaInstancia")
	public void reportePorDependencia(WebDataBinder binder) {
		binder.setValidator(new ReporteInstanciaValidador());
	}
	
	private static final Logger logger=Logger.getLogger(ReportesController.class);
	
	@RequestMapping(value = "/reporteEstadoProceso", method = RequestMethod.GET)
	public void enviarProcesoEstado(ModelAndView modelAndView,@ModelAttribute("fechasProceso")Proceso proceso){
		new Proceso();
	}
    @RequestMapping(value = "/reporteEstadoProceso", method = RequestMethod.POST)
    public ModelAndView reporteEstadoProceso(ModelAndView modelAndView,@ModelAttribute("fechasProceso")@Valid Proceso proceso,BindingResult result) {
    	if(result.hasErrors()){
			return new ModelAndView("reporteEstadoProceso");
		}
    	JRDataSource datasource=procesoService.reporteEstadoProceso(proceso);
    	if(datasource!=null){
    		Map<String,Object> parameterMap = new HashMap<String,Object>();
    		parameterMap.put("datasource", datasource);
    		modelAndView = new ModelAndView("pdfReportEstado", parameterMap);
    	}else{
    		modelAndView = new ModelAndView("reporteEstadoProceso");
    		modelAndView.addObject("error", "No se encontraron Resultados");
    	}
    	
		return modelAndView;
	}
    @RequestMapping(value = "/reporteMateriaProceso", method = RequestMethod.GET)
	public void enviarProcesoMateria(ModelAndView modelAndView,@ModelAttribute("materiaProceso")Proceso proceso){
		new Proceso();
	}
    @RequestMapping(value = "/reporteMateriaProceso", method = RequestMethod.POST)
    public ModelAndView reporteMateriaProceso(ModelAndView modelAndView,@ModelAttribute("materiaProceso")@Valid Proceso proceso,BindingResult result) {
    	if(result.hasErrors()){
			return new ModelAndView("reporteMateriaProceso");
		}
    	JRDataSource datasource=procesoService.reporteMateriaProceso(proceso);
    	if(datasource!=null){
    		Map<String,Object> parameterMap = new HashMap<String,Object>();
    		parameterMap.put("datasource", datasource);
    		modelAndView = new ModelAndView("pdfReportMateria", parameterMap);
    	}else{
    		modelAndView = new ModelAndView("reporteMateriaProceso");
    		modelAndView.addObject("error", "No se encontraron Resultados");    		
    	}

		return modelAndView;
	}
    @RequestMapping(value = "/reporteUbigeoProceso", method = RequestMethod.GET)
	public void enviarInstancia(ModelAndView modelAndView,@ModelAttribute("ubigeoInstancia")Instancia instancia){
		new Instancia();
	}
    @RequestMapping(value = "/reporteUbigeoProceso", method = RequestMethod.POST)
    public ModelAndView reporteInstancia(ModelAndView modelAndView,@ModelAttribute("ubigeoInstancia")@Valid Instancia instancia,BindingResult result) {
    	if(result.hasErrors()){
			return new ModelAndView("reporteUbigeoProceso");
		}
    	JRDataSource datasource=instanciaService.reporteInstanciaUbigeo(instancia);
    	if(datasource!=null){
    		Map<String,Object> parameterMap = new HashMap<String,Object>();
    		parameterMap.put("datasource", datasource);
    		modelAndView = new ModelAndView("pdfReportUbigeo", parameterMap);
    	}else{
    		modelAndView = new ModelAndView("reporteUbigeoProceso");
    		modelAndView.addObject("error", "No se encontraron Resultados");
    	}
    	
		return modelAndView;
	}
    
    @RequestMapping(value = "/reporteDependenciaProceso", method = RequestMethod.GET)
   	public void enviarDependencia(ModelAndView modelAndView,@ModelAttribute("dependenciaInstancia")Instancia instancia){
   		new Instancia();
   	}
    
   @RequestMapping(value = "/reporteDependenciaProceso", method = RequestMethod.POST)
   public ModelAndView reporteDependencia(ModelAndView modelAndView,@ModelAttribute("dependenciaInstancia")@Valid Instancia instancia,BindingResult result) {
		if(result.hasErrors()){
			return new ModelAndView("reporteDependenciaProceso");
		}
		JRDataSource datasource=instanciaService.reporteDependenciaInstancia(instancia);
		if(datasource!=null){
			Map<String,Object> parameterMap = new HashMap<String,Object>();
			parameterMap.put("datasource", datasource);
			modelAndView = new ModelAndView("pdfReportDependencia", parameterMap);
		}else{
			modelAndView = new ModelAndView("reporteDependenciaProceso");
    		modelAndView.addObject("error", "No se encontraron Resultados");
		}
	   	
		return modelAndView;
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
	@ModelAttribute("tipoUbigeo")
	public List<Ubigeo>listarUbigeo(){
		List<Ubigeo> tipoUbigeo=procesoService.listarUbigeo();
		return tipoUbigeo;
	}
	@ModelAttribute("organosJudiciales")
	public List<Organojudicial> listarOrganosJudiciales(){
		List<Organojudicial> organoJudicial=instanciaService.listarOrganosJudiciales();
		return organoJudicial;
	}
}
