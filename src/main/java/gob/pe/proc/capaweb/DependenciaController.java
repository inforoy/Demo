package gob.pe.proc.capaweb;

import gob.pe.proc.capadatos.Dependencia;
import gob.pe.proc.capadatos.Organojudicial;
import gob.pe.proc.capadatos.Ubigeo;
import gob.pe.proc.capaservicio.DependenciaService;
import gob.pe.proc.capaservicio.InstanciaService;
import gob.pe.proc.capaservicio.UbigeoService;
import gob.pe.proc.validadores.DistritoJudicialValidador;

import java.util.List;

import javax.validation.Valid;

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
public class DependenciaController {
	@Autowired
	private DependenciaService dependenciaService;
	@Autowired
	private InstanciaService instanciaService;
	@Autowired
	private UbigeoService ubigeoService;
	
	private static final Logger logger=Logger.getLogger(DependenciaController.class);
	
	@InitBinder("distritoJudicialGuardar")
	public void initBinder(WebDataBinder binder){
		binder.setValidator(new DistritoJudicialValidador());
	}
	
	@RequestMapping("/registrarDistritoJudicial.htm")
	public void organoJudicialList(Model model,@ModelAttribute("distritoJudicialBuscar") Dependencia dependencia) {
		List<Dependencia> listaDistritosJudiciales = dependenciaService.obtenerListaDistritosJudicial(dependencia);
		model.addAttribute("listaDistritosJudiciales", listaDistritosJudiciales);
	}
	
	@RequestMapping(value = "/addDistritoJudicial.htm", method = RequestMethod.GET)
	public @ModelAttribute("distritoJudicialGuardar") Dependencia dependencia(@RequestParam(value="idDistritoJudicial",required=false)Integer idDistritoJudicial) {
		if(idDistritoJudicial!=null){
			Dependencia obtenerDistritoJudicial=dependenciaService.buscarDependeciaID(idDistritoJudicial);
			return obtenerDistritoJudicial;
		}
		return new Dependencia();
		
	}

	@RequestMapping(value = "/addDistritoJudicial.htm", method = RequestMethod.POST)
	public String guardarDistritoJudicial(@ModelAttribute("distritoJudicialGuardar") @Valid Dependencia dependencia,BindingResult result) {
		if(result.hasErrors()){
			return "addDistritoJudicial";
		}
		dependenciaService.guardarDistritoJudicial(dependencia);
		return "redirect:registrarDistritoJudicial.htm";
	}
	
	@RequestMapping(value="/borrarDistritoJudicial.htm")
	public String borrarDistritoJudicial(Model model,@RequestParam(value="idDistritoJudicial",required=false)Integer idDistritoJudicial){
		dependenciaService.borrarDistritoJudicial(idDistritoJudicial);
		return "redirect:registrarDistritoJudicial.htm";
	}
	
	@ModelAttribute("organosJudiciales")
	public List<Organojudicial> listarOrganosJudiciales(){
		List<Organojudicial> organoJudicial=instanciaService.listarOrganosJudiciales();
		return organoJudicial;
	}
	@ModelAttribute("ubigeos")
	public List<Ubigeo> listarUbigeos(){
		List<Ubigeo> ubigeo=ubigeoService.listarUbigeo();
		return ubigeo;
	}
}
