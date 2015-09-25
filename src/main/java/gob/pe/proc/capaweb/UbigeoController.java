package gob.pe.proc.capaweb;

import gob.pe.proc.capaservicio.UbigeoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UbigeoController {
	@Autowired
	private UbigeoService ubigeoService;
}
