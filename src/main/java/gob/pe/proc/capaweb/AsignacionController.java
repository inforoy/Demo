package gob.pe.proc.capaweb;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import gob.pe.proc.capadatos.Asignado;
import gob.pe.proc.capadatos.AsignadoId;
import gob.pe.proc.capadatos.Proceso;
import gob.pe.proc.capadatos.Usuario;
import gob.pe.proc.capaservicio.AsignacionService;
import gob.pe.proc.validadores.AsignacionValidadores;

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
public class AsignacionController {
	@Autowired
	private AsignacionService asignacionService;
	
	private static final Logger logger=Logger.getLogger(AsignacionController.class);
	
	@InitBinder("asignadoGuardar")
	public void initBinder(WebDataBinder binder){
		binder.setValidator(new AsignacionValidadores());
	}
	
	@RequestMapping(value="/busquedaCargaLaboral.htm")
	public void buscarCargaAbogado(Model model,@ModelAttribute("abogadoBuscar")Asignado abogadoBuscar){
		try {
			List<Asignado>listaAbogadoProceso=asignacionService.listaAbogadosProceso(abogadoBuscar);
			model.addAttribute("listaAbogadoProceso",listaAbogadoProceso);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value="/busquedaAsignacion.htm")
	public void buscarAsignacion(Model model,@ModelAttribute("asignadoBuscar")Asignado asignadoBuscar){
		Set<Proceso>listaProcesoNoAsignados=asignacionService.obtenerListaProcesosNoAsignados(asignadoBuscar);
		model.addAttribute("listaNoAsignados",listaProcesoNoAsignados);
	}
	@RequestMapping(value = "/addAsignacion.htm", method = RequestMethod.GET)
	public void enviarAsignacion(@RequestParam(value="numExpediente",required=false)String numExpediente,
			@ModelAttribute("asignadoGuardar") Asignado asignadoGuardar) {
		
		Proceso proceso=new Proceso();
		proceso.setNroExpediente(numExpediente);
		AsignadoId id=new AsignadoId();
		id.setProceso(proceso);
		asignadoGuardar.setId(id);
	}
	@RequestMapping(value="/addAsignacion.htm",method=RequestMethod.POST)
	public String guardarAudiencia(@ModelAttribute("asignadoGuardar")@Valid Asignado asignadoGuardar,BindingResult result){
		if(result.hasErrors()){
			return "addAsignacion";
		}
		asignacionService.guardarAsignado(asignadoGuardar);
		return "redirect:/busquedaAsignacion.htm";
	}
	@ModelAttribute("abogados")
	public List<Usuario> listarAbogados(){
		List<Usuario> abogados=asignacionService.listaAbogados();
		return abogados;
	}
}
