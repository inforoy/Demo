package gob.pe.proc.capaweb;

import java.util.List;

import javax.validation.Valid;

import gob.pe.proc.capadatos.Organojudicial;
import gob.pe.proc.capaservicio.OrganoJudicialService;
import gob.pe.proc.validadores.OrganoJudicialValidador;

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
public class OrganoJudicialController {
	@Autowired
	private OrganoJudicialService organoJudicialService;
	
	private static final Logger logger=Logger.getLogger(OrganoJudicialController.class);
	
	@InitBinder("organoJudicialGuardar")
	public void initBinder(WebDataBinder dataBinder){
		dataBinder.setValidator(new OrganoJudicialValidador());
	}
	
	@RequestMapping("/registrarOrganoJudicial.htm")
	public void organoJudicialList(Model model,@ModelAttribute("organoJudicialBuscar") Organojudicial organojudicial) {
		List<Organojudicial> listaOrganosJudiciales = organoJudicialService.obtenerListaOrganoJudicial(organojudicial);
		model.addAttribute("listaOrganosJudiciales", listaOrganosJudiciales);
	}
	
	@RequestMapping(value = "/addOrganoJudicial.htm", method = RequestMethod.GET)
	public @ModelAttribute("organoJudicialGuardar") Organojudicial organojudicial(@RequestParam(value="idOrganoJudicial",required=false)Integer idOrganoJudicial) {
		if(idOrganoJudicial!=null){
			Organojudicial obtenerOrganoJudicial=organoJudicialService.obtenerOrganoJudicialporId(idOrganoJudicial);
			return obtenerOrganoJudicial;
		}
		return new Organojudicial();
		
	}

	@RequestMapping(value = "/addOrganoJudicial.htm", method = RequestMethod.POST)
	public String guardarOrganoJudicial(@ModelAttribute("organoJudicialGuardar") @Valid Organojudicial organojudicial,BindingResult result) {
		if(result.hasErrors()){
			return "addOrganoJudicial";
		}
		organoJudicialService.guardarOrganoJudicial(organojudicial);
		return "redirect:registrarOrganoJudicial.htm";
	}
	
	@RequestMapping(value="/borrarOrganoJudicial.htm")
	public String borrarOrganoJudicial(Model model,@RequestParam(value="idOrganoJudicial",required=false)Integer idOrganoJudicial){
		organoJudicialService.borrarOrganoJudicial(idOrganoJudicial);
		return "redirect:registrarOrganoJudicial.htm";
	}
}
