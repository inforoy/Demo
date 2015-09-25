package gob.pe.proc.capaservicio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gob.pe.proc.capaacccesodatos.EscritoDAO;
import gob.pe.proc.capadatos.Escrito;
import gob.pe.proc.capaservicio.EscritoService;
@Service
public class EscritoServiceImpl implements EscritoService {
	@Autowired
	private EscritoDAO escritoDAO;
	@Override
	public List<Escrito> obtenerListaEscrito(Escrito escrito) {
		return escritoDAO.obtenerListaEscrito(escrito);
	}

	@Override
	public Escrito obtenerEscritoporId(Integer idEscrito) {
		return escritoDAO.obtenerEscritoporID(idEscrito);
	}

	@Override
	public void guardarEscrito(Escrito escrito) {
		escritoDAO.guardarEscrito(escrito);

	}

	@Override
	public void borrarEscrito(Integer idEscrito) {
		escritoDAO.borrarEscrito(idEscrito);

	}

}
