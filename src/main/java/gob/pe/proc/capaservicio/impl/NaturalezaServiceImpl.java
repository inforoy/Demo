package gob.pe.proc.capaservicio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gob.pe.proc.capaacccesodatos.NaturalezaDAO;
import gob.pe.proc.capadatos.Naturaleza;
import gob.pe.proc.capaservicio.NaturalezaService;
@Service
public class NaturalezaServiceImpl implements NaturalezaService {
	@Autowired
	private NaturalezaDAO naturalezaDAO;
	
	public List<Naturaleza> obtenerListaNaturaleza(Naturaleza naturaleza) {
		return naturalezaDAO.obtenerListaNaturaleza(naturaleza);
	}

	public Naturaleza obtenerNaturalezaporID(Integer idNaturaleza) {
		return naturalezaDAO.obtenerNaturalezaporID(idNaturaleza);
	}

	public void guardarNaturaleza(Naturaleza naturaleza) {
		naturalezaDAO.guardarNaturaleza(naturaleza);
	}

	public void borrarNaturaleza(Integer idNaturaleza) {
		naturalezaDAO.borrarNaturaleza(idNaturaleza);
	}

}
