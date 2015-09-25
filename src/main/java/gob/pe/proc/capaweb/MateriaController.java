package gob.pe.proc.capaweb;

import java.util.List;

import javax.validation.Valid;

import gob.pe.proc.capadatos.Materia;
import gob.pe.proc.capadatos.Naturaleza;
import gob.pe.proc.capaservicio.MateriaService;
import gob.pe.proc.validadores.MateriaValidador;

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
public class MateriaController {
	@Autowired
	private MateriaService materiaService;
	
	private static final Logger logger=Logger.getLogger(MateriaController.class);
	
	@InitBinder("materiaGuardar")
	public void initBinder(WebDataBinder binder){
		binder.setValidator(new MateriaValidador());
	}
	
	@RequestMapping("/materiaList.htm")
	public void materiaList(Model model,@ModelAttribute("materiaBuscar")Materia materiaBuscar){
		List<Materia> listaMateria=materiaService.obtenerListaMateria(materiaBuscar);	
		model.addAttribute("listamateria",listaMateria);
			
	}
	@RequestMapping(value="/addMateria.htm",method=RequestMethod.GET)
	public @ModelAttribute("materiaGuardar")Materia materianuevo(@RequestParam(value="idMateria",required=false)Integer idMateria){
		if(idMateria!=null){
			Materia materia=materiaService.obtenerMateriaporId(idMateria);
			return materia;
		}
		return new Materia();
	}
	
	@RequestMapping(value="/addMateria.htm",method=RequestMethod.POST)
	public String guardarMateria(@ModelAttribute("materiaGuardar")@Valid Materia materiaguardar,BindingResult result){
		if(result.hasErrors()){
			return "addMateria";
		}
		materiaService.guardarMateria(materiaguardar);
		return "redirect:/materiaList.htm";
	}
	
	@RequestMapping(value="/borrarMateria.htm")
	public String eliminarMateria(@RequestParam(value="idMateria",required=false)Integer idMateria){
		materiaService.borrarMateria(idMateria);
		return "redirect:/materiaList.htm";
	}
	
	@ModelAttribute("tipoNaturaleza")
	public List<Naturaleza> listarNaturaleza(){
		List<Naturaleza> tipoNaturaleza=materiaService.obtenerListaNaturaleza();
		return tipoNaturaleza;
	}
}
