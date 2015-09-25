package gob.pe.proc.capaweb;

import java.util.List;

import javax.validation.Valid;

import gob.pe.proc.capadatos.Estado;
import gob.pe.proc.capaservicio.EstadoService;
import gob.pe.proc.validadores.EstadoValidador;

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
public class EstadoController {
	@Autowired
	private EstadoService estadoService;
	
	private static final Logger logger=Logger.getLogger(EstadoController.class);
		
	@InitBinder("usuarioGuardar")
	public void initBinder(WebDataBinder binder){
		binder.setValidator(new EstadoValidador());
	}
	
	
	@RequestMapping("/estadoList.htm")
	public void estadoList(Model model,@ModelAttribute("estadoBuscar")Estado estadoBuscar){
		List<Estado> listaEstado=estadoService.obtenerListaEstado(estadoBuscar);	
		model.addAttribute("listaestado",listaEstado);
			
	}
	@RequestMapping(value="/addEstado.htm",method=RequestMethod.GET)
	public @ModelAttribute("usuarioGuardar")Estado estadonuevo(@RequestParam(value="idEstado",required=false)Integer idEstado){
		if(idEstado!=null){
			Estado estado=estadoService.obtenerEstado(idEstado);
			return estado;
		}
		return new Estado();
	}
	
	@RequestMapping(value="/addEstado.htm",method=RequestMethod.POST)
	public String guardarEstado(@ModelAttribute("usuarioGuardar")@Valid Estado estadoguardar,BindingResult result){
		if(result.hasErrors()){
			return "addEstado";
		}
		estadoService.guardarEstado(estadoguardar);
		return "redirect:/estadoList.htm";
	}

	/** eliminarEstado **/
	@RequestMapping(value="/borrarEstado.htm",method=RequestMethod.GET)
	public String  borrarEstado(@RequestParam(value="idEstado",required=false)Integer idEstado){
		 estadoService.eliminarEstado(idEstado);
		 return "redirect:/estadoList.htm";
	}
	
}
