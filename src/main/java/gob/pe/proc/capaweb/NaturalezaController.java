package gob.pe.proc.capaweb;

import java.util.List;

import javax.validation.Valid;

import gob.pe.proc.capadatos.Naturaleza;
import gob.pe.proc.capaservicio.NaturalezaService;
import gob.pe.proc.validadores.NaturalezaValidador;

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
public class NaturalezaController {
	@Autowired
	private NaturalezaService naturalezaService;
	
	private static final Logger logger=Logger.getLogger(NaturalezaController.class);
	
	@InitBinder("naturalezaGuardar")
	public void initBinder(WebDataBinder binder){
		binder.setValidator(new NaturalezaValidador());
	}
	
	
	@RequestMapping("/naturalezaList.htm")
	public void naturalezaList(Model model,@ModelAttribute("naturalezaBuscar")Naturaleza naturalezaBuscar){
		List<Naturaleza> listaNaturaleza=naturalezaService.obtenerListaNaturaleza(naturalezaBuscar);	
		model.addAttribute("listaNaturaleza",listaNaturaleza);
			
	}
	@RequestMapping(value="/addNaturaleza.htm",method=RequestMethod.GET)
	public @ModelAttribute("naturalezaGuardar")Naturaleza naturalezanuevo(@RequestParam(value="idNaturaleza",required=false)Integer idNaturaleza){
		if(idNaturaleza!=null){
			Naturaleza naturaleza=naturalezaService.obtenerNaturalezaporID(idNaturaleza);
			return naturaleza;
		}
		return new Naturaleza();
	}
	
	@RequestMapping(value="/addNaturaleza.htm",method=RequestMethod.POST)
	public String guardarNaturaleza(@ModelAttribute("naturalezaGuardar")@Valid Naturaleza naturalezaGuardar,BindingResult result){
		if(result.hasErrors()){
			return "addNaturaleza";
		}
		naturalezaService.guardarNaturaleza(naturalezaGuardar);;
		return "redirect:/naturalezaList.htm";
	}

	/** eliminarEstado **/
	@RequestMapping(value="/borrarNaturaleza.htm")
	public String  borrarNaturaleza(@RequestParam(value="idNaturaleza",required=false)Integer idNaturaleza){
		naturalezaService.borrarNaturaleza(idNaturaleza);
		 return "redirect:/naturalezaList.htm";
	}
}
